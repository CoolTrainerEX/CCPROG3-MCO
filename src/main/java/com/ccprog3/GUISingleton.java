package com.ccprog3;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffee.CoffeeType;
import com.ccprog3.coffee.Espresso;
import com.ccprog3.coffeeTruck.CoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialCoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialStorageBin;
import com.ccprog3.coffeeTruck.StorageBin;
import com.ccprog3.gui.CustomButton;
import com.ccprog3.gui.CustomComboBox;
import com.ccprog3.gui.CustomDialog;
import com.ccprog3.gui.CustomTextField;
import com.ccprog3.gui.DropdownWithNumberInput;
import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * GUI user interface
 * 
 * @author Justin Ryan Uy
 */
public class GUISingleton implements UI {
    /**
     * Singleton instance of the GUI
     */
    private static final GUISingleton instance = new GUISingleton();

    /**
     * Main frame to be used
     */
    private final JFrame frame = new JFrame();

    /**
     * Previous panel for back button
     */
    private JPanel previousPanel;

    /**
     * Adds design to the frame
     */
    private GUISingleton() {
        int height = 720, width = height * 4 / 3;

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centers to screen
        frame.setVisible(true);
    }

    /**
     * Gets the {@code GUISingleton} instance
     * 
     * @return The instance
     */
    public static GUISingleton getInstance() {
        return instance;
    }

    /**
     * Throws an error if string input is invalid
     * 
     * @param string The string input to check
     * @throws InputMismatchException Invalid input
     */
    private void validateString(String string) throws InputMismatchException {
        if (string == null || string.length() == 0)
            throw new InputMismatchException("Cannot be an empty string");
    }

    /**
     * Parses a string input into a number
     * 
     * @param string The string input to check
     * @return The parsed number
     * @throws InputMismatchException Invalid input
     */
    private double parseNumber(String string) throws InputMismatchException {
        validateString(string);

        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Input is not a number. Try again.");
        }
    }

    @Override
    public boolean menu(Map<String, Supplier<Boolean>> options) {
        JPanel panel = new JPanel(), buttonPanel = new JPanel(new GridLayout(0, 1, 0, 10));

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.add(new JLabel(new ImageIcon(new ImageIcon("src/main/resources/Weirdo.jpeg").getImage()
                .getScaledInstance(100, 200, Image.SCALE_SMOOTH))));
        panel.add(buttonPanel);

        for (Map.Entry<String, Supplier<Boolean>> option : options.entrySet())
            buttonPanel.add(new CustomButton(option.getKey(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    while (true) {
                        try {
                            option.getValue().get();
                            break;
                        } catch (Exception e) {
                            displayErr(e);
                        }
                    }
                }
            }));

        buttonPanel.add(new CustomButton("Back", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(previousPanel);
            }
        }));

        frame.setContentPane(previousPanel = panel);

        return false;
    }

    @Override
    public void displayErr(Exception e) {
        JOptionPane.showMessageDialog(frame, e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public String login() {
        JPanel panel = new JPanel();
        CustomTextField input = new CustomTextField("Username");

        panel.add(input);

        while (true)
            try {
                new CustomDialog(frame, "Login", panel);

                validateString(input.getText());
                return input.getText();
            } catch (InputMismatchException e) {
                displayErr(e);
            }
    }

    @Override
    public CoffeeTruck addCoffeeTruck() {
        JPanel panel = new JPanel();
        CustomComboBox<String> typeInput = new CustomComboBox<>(new String[] { "Regular", "Special" });
        CustomTextField locationInput = new CustomTextField("Location");
        List<DropdownWithNumberInput<Ingredient>> ingredientsInput = new ArrayList<>();
        List<DropdownWithNumberInput<SyrupIngredient>> syrupIngredientsInput = new ArrayList<>();

        panel.add(typeInput);
        panel.add(locationInput);

        for (int i = 0; i < 8; i++) {
            ingredientsInput.add(new DropdownWithNumberInput<>(Ingredient.values(), "Quantity"));
            panel.add(ingredientsInput.get(i));
        }

        DropdownWithNumberInput<SyrupIngredient> tempSyrupIngredientInput;

        for (int i = 0; i < 2; i++) {
            syrupIngredientsInput.add(
                    tempSyrupIngredientInput = new DropdownWithNumberInput<>(SyrupIngredient.values(), "Quantity"));
            tempSyrupIngredientInput.setVisible(false);
            panel.add(tempSyrupIngredientInput);
        }

        typeInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeInput.getSelectedIndex() == 0)
                    for (DropdownWithNumberInput<SyrupIngredient> syrupIngredientInput : syrupIngredientsInput)
                        syrupIngredientInput.setVisible(false);
                else
                    for (DropdownWithNumberInput<SyrupIngredient> syrupIngredientInput : syrupIngredientsInput)
                        syrupIngredientInput.setVisible(true);
            }
        });

        new CustomDialog(frame, "Create a Coffee Truck", panel);

        validateString(locationInput.getText());

        StorageBin ingredients[] = ingredientsInput.stream()
                .map(ingredientInput -> new StorageBin((Ingredient) ingredientInput.getComboBox().getSelectedItem(),
                        parseNumber(ingredientInput.getTextField().getText())))
                .toArray(StorageBin[]::new);

        if (typeInput.getSelectedIndex() == 0)
            return new CoffeeTruck(locationInput.getText(), ingredients);

        return new SpecialCoffeeTruck(locationInput.getText(), ingredients,
                syrupIngredientsInput.stream()
                        .map(syrupIngredientInput -> new SpecialStorageBin(
                                (SyrupIngredient) syrupIngredientInput.getComboBox().getSelectedItem(),
                                parseNumber(syrupIngredientInput.getTextField().getText())))
                        .toArray(SpecialStorageBin[]::new));
    }

    @Override
    public Coffee buyCoffee(boolean special) {
        JPanel panel = new JPanel();
        CustomComboBox<CoffeeType> typeInput = new CustomComboBox<>(CoffeeType.values());
        CustomComboBox<Ingredient> cupInput = new CustomComboBox<>(
                Arrays.stream(Ingredient.values()).filter(ingredient -> {
                    try {
                        ingredient.getCupVolume();
                        return true;
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                }).toArray(Ingredient[]::new));

        panel.add(typeInput);
        panel.add(cupInput);

        if (!special) {
            new CustomDialog(frame, "Buy a Coffee", panel);
            return new Coffee((CoffeeType) typeInput.getSelectedItem(), (Ingredient) cupInput.getSelectedItem());
        }

        // Special

        CustomComboBox<Espresso> espressoInput = new CustomComboBox<>(Espresso.values());

        panel.add(espressoInput);
        throw new UnsupportedOperationException("Unimplemented method 'makeCoffee'");

    }

    @Override
    public void makeCoffee(Entry<Coffee, Money> sale) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeCoffee'");
    }

    @Override
    public void coffeeTruckInfo(CoffeeTruck coffeeTruck, UserSingleton user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'coffeeTruckInfo'");
    }

    @Override
    public int chooseCoffeeTruck(List<CoffeeTruck> coffeeTrucks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chooseCoffeeTruck'");
    }

    @Override
    public int chooseStorageBin(List<StorageBin> storageBins) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chooseStorageBin'");
    }

    @Override
    public double addStorageBinQuantity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStorageBinQuantity'");
    }

    @Override
    public StorageBin setStorageBin(boolean special) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStorageBin'");
    }

    @Override
    public String setCoffeeTruckLocation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCoffeeTruckLocation'");
    }

    @Override
    public void dashboard(List<CoffeeTruck> coffeeTrucks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dashboard'");
    }

    @Override
    public <E extends Enum<E>> Map<E, Money> setPrices(Class<E> priceClass) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrices'");
    }
}
