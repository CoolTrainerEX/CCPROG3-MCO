package com.ccprog3;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffee.CoffeeType;
import com.ccprog3.coffee.CustomSpecialCoffee;
import com.ccprog3.coffee.Espresso;
import com.ccprog3.coffee.SpecialCoffee;
import com.ccprog3.coffeeTruck.CoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialCoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialStorageBin;
import com.ccprog3.coffeeTruck.StorageBin;
import com.ccprog3.gui.CustomButton;
import com.ccprog3.gui.CustomComboBox;
import com.ccprog3.gui.CustomDialog;
import com.ccprog3.gui.CustomLabel;
import com.ccprog3.gui.CustomTextField;
import com.ccprog3.gui.DropdownWithNumberInput;
import com.ccprog3.gui.ListAdder;
import com.ccprog3.gui.TextPanel;
import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;
import com.ccprog3.ingredients.Unit;

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
     * Previous panels for back button
     */
    private Stack<JPanel> previousPanels = new Stack<>();

    /**
     * Adds design to the frame
     */
    private GUISingleton() {
        int height = 720, width = height * 4 / 3;

        frame.setSize(width, height);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(new JLabel(new ImageIcon(new ImageIcon("src/main/resources/Weirdo.jpeg").getImage()
                .getScaledInstance(100, 200, Image.SCALE_SMOOTH))));
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
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        for (Map.Entry<String, Supplier<Boolean>> option : options.entrySet())
            panel.add(new CustomButton(option.getKey(), new ActionListener() {
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

        if (!previousPanels.empty()) {
            panel.add(new CustomButton("Back", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.remove(previousPanels.pop());
                    frame.add(previousPanels.peek());
                    frame.revalidate();
                    frame.repaint();
                }
            }));

            frame.remove(previousPanels.peek());
        }

        frame.add(previousPanels.push(panel));
        frame.revalidate();
        frame.repaint();

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
        List<DropdownWithNumberInput<Ingredient>> storageBinsInput = new ArrayList<>();
        List<DropdownWithNumberInput<SyrupIngredient>> specialStorageBinsInput = new ArrayList<>();

        panel.add(typeInput);
        panel.add(locationInput);
        panel.add(new CustomLabel("Storage Bins", Font.BOLD));

        DropdownWithNumberInput<Ingredient> tempStorageBinInput;

        for (int i = 0; i < 8; i++) {
            storageBinsInput.add(tempStorageBinInput = new DropdownWithNumberInput<>(Ingredient.values(), "Quantity"));
            tempStorageBinInput.getComboBox().setSelectedItem(Ingredient.NONE);
            panel.add(storageBinsInput.get(i));
        }

        CustomLabel specialStorageBinsLabel = new CustomLabel("Special Storage Bins", Font.BOLD);

        specialStorageBinsLabel.setVisible(false);
        panel.add(specialStorageBinsLabel);

        DropdownWithNumberInput<SyrupIngredient> tempSpecialStorageBinInput;

        for (int i = 0; i < 2; i++) {
            specialStorageBinsInput.add(
                    tempSpecialStorageBinInput = new DropdownWithNumberInput<>(SyrupIngredient.values(), "Quantity"));
            tempSpecialStorageBinInput.getComboBox().setSelectedItem(SyrupIngredient.NONE);
            tempSpecialStorageBinInput.setVisible(false);
            panel.add(tempSpecialStorageBinInput);
        }

        typeInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeInput.getSelectedIndex() == 0) {
                    specialStorageBinsLabel.setVisible(false);

                    for (DropdownWithNumberInput<SyrupIngredient> specialStorageBinInput : specialStorageBinsInput)
                        specialStorageBinInput.setVisible(false);
                } else {
                    specialStorageBinsLabel.setVisible(true);

                    for (DropdownWithNumberInput<SyrupIngredient> specialStorageBinInput : specialStorageBinsInput)
                        specialStorageBinInput.setVisible(true);
                }
            }
        });

        new CustomDialog(frame, "Create a Coffee Truck", panel);

        validateString(locationInput.getText());

        StorageBin ingredients[] = storageBinsInput
                .stream().map(
                        storageBinInput -> storageBinInput.getComboBox().getSelectedItem() == Ingredient.NONE
                                ? new StorageBin(Ingredient.NONE, 0)
                                : new StorageBin((Ingredient) storageBinInput.getComboBox().getSelectedItem(),
                                        parseNumber(storageBinInput.getTextField().getText())))
                .toArray(StorageBin[]::new);

        if (typeInput.getSelectedIndex() == 0)
            return new CoffeeTruck(locationInput.getText(), ingredients);

        return new SpecialCoffeeTruck(locationInput.getText(), ingredients,
                specialStorageBinsInput.stream()
                        .map(specialStorageBinInput -> specialStorageBinInput.getComboBox()
                                .getSelectedItem() == SyrupIngredient.NONE
                                        ? new SpecialStorageBin(SyrupIngredient.NONE, 0)
                                        : new SpecialStorageBin(
                                                (SyrupIngredient) specialStorageBinInput.getComboBox()
                                                        .getSelectedItem(),
                                                parseNumber(specialStorageBinInput.getTextField().getText())))
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
        CustomTextField ratioInput = new CustomTextField("Custom ratio");
        ListAdder<SyrupIngredient> syrupsInput = new ListAdder<>(Arrays.stream(SyrupIngredient.values())
                .filter(syrupIngredient -> syrupIngredient != SyrupIngredient.NONE).toArray(SyrupIngredient[]::new));
        ListAdder<Espresso> shotsInput = new ListAdder<>(Arrays.stream(Espresso.values())
                .filter(espresso -> espresso != Espresso.CUSTOM).toArray(Espresso[]::new));

        ratioInput.setVisible(false);
        panel.add(espressoInput);
        panel.add(ratioInput);
        panel.add(new CustomLabel("Add Syrups and Extra shots", Font.BOLD));
        panel.add(syrupsInput);
        panel.add(shotsInput);

        espressoInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ratioInput.setVisible(espressoInput.getSelectedItem() == Espresso.CUSTOM);
            };
        });

        new CustomDialog(frame, "Buy a Coffee", panel);

        if (espressoInput.getSelectedItem() == Espresso.CUSTOM) {
            double ratioDouble = parseNumber(ratioInput.getText());
            int ratio = (int) ratioDouble;

            if (ratioDouble != ratio)
                throw new InputMismatchException("Input is not an integer. Try again.");

            return new CustomSpecialCoffee((CoffeeType) typeInput.getSelectedItem(),
                    (Ingredient) cupInput.getSelectedItem(),
                    ratio, (SyrupIngredient[]) syrupsInput.getResultList().toArray(),
                    (Espresso[]) shotsInput.getResultList().toArray());
        } else
            return new SpecialCoffee((CoffeeType) typeInput.getSelectedItem(), (Ingredient) cupInput.getSelectedItem(),
                    (Espresso) espressoInput.getSelectedItem(),
                    (SyrupIngredient[]) syrupsInput.getResultList().toArray(),
                    (Espresso[]) shotsInput.getResultList().toArray());
    }

    @Override
    public void makeCoffee(Entry<Coffee, Money> sale) {
        JPanel panel = new JPanel();
        TextPanel textPanel = new TextPanel();

        textPanel.add(new CustomLabel("Making Espresso", Font.BOLD));

        for (Map.Entry<Ingredient, Double> ingredient : sale.getKey().getEspressoIngredients().entrySet())
            textPanel.add(new CustomLabel("\t" + ingredient.getKey() + " - "
                    + (ingredient.getKey().getUnit() == Unit.GRAMS ? Unit.flozToG(ingredient.getValue())
                            : ingredient.getValue())
                    + ingredient.getKey().getUnit(), Font.ITALIC));

        panel.add(textPanel);
        textPanel = new TextPanel();
        textPanel.add(new CustomLabel("Making Coffee", Font.BOLD));

        for (Map.Entry<Ingredient, Double> ingredient : sale.getKey().getIngredients().entrySet())
            textPanel.add(new CustomLabel("\t" + ingredient.getKey() + " - "
                    + (ingredient.getKey().getUnit() == Unit.GRAMS ? Unit.flozToG(ingredient.getValue())
                            : ingredient.getValue())
                    + ingredient.getKey().getUnit(), Font.ITALIC));

        panel.add(textPanel);

        if (!(sale.getKey() instanceof SpecialCoffee)) {
            CustomLabel priceLabel = new CustomLabel("Price is " + sale.getValue(), Font.BOLD);

            priceLabel.setForeground(Color.RED);
            panel.add(priceLabel);
            return;
        }

        // Special

        textPanel = new TextPanel();

        if (!((SpecialCoffee) sale.getKey()).getSyrupIngredients().isEmpty())
            textPanel.add(new CustomLabel("Adding Syrups", Font.BOLD));

        for (Map.Entry<SyrupIngredient, Double> ingredient : ((SpecialCoffee) sale.getKey()).getSyrupIngredients()
                .entrySet())
            textPanel.add(new CustomLabel("\t" + ingredient.getKey() + " - "
                    + (ingredient.getKey().getUnit() == Unit.GRAMS ? Unit.flozToG(ingredient.getValue())
                            : ingredient.getValue())
                    + ingredient.getKey().getUnit(), Font.ITALIC));

        panel.add(textPanel);
        textPanel = new TextPanel();

        if (!((SpecialCoffee) sale.getKey()).getShotIngredients().isEmpty())
            textPanel.add(new CustomLabel("Adding extra shots", Font.BOLD));

        for (Map.Entry<Ingredient, Double> ingredient : ((SpecialCoffee) sale.getKey()).getShotIngredients().entrySet())
            textPanel.add(new CustomLabel("\t" + ingredient.getKey() + " - "
                    + (ingredient.getKey().getUnit() == Unit.GRAMS ? Unit.flozToG(ingredient.getValue())
                            : ingredient.getValue())
                    + ingredient.getKey().getUnit(), Font.ITALIC));

        panel.add(textPanel);

        CustomLabel priceLabel = new CustomLabel("Price is " + sale.getValue(), Font.BOLD);

        priceLabel.setForeground(Color.RED);
        panel.add(priceLabel);

        new CustomDialog(frame, "Making Coffee", panel);
    }

    @Override
    public void coffeeTruckInfo(CoffeeTruck coffeeTruck, UserSingleton user) {
        JPanel panel = new JPanel();
        TextPanel textPanel;
        CustomLabel label;

        label = new CustomLabel(coffeeTruck.toString(), Font.BOLD);
        label.setBackground(Color.BLUE);
        panel.add(label);
        textPanel = new TextPanel();

        for (StorageBin storageBin : coffeeTruck.getStorageBins()) {
            label = new CustomLabel(storageBin.toString(), Font.PLAIN);
            label.setForeground(Color.GREEN);
            textPanel.add(label);
        }

        panel.add(textPanel);

        for (Map.Entry<Coffee, Money> sale : coffeeTruck.getSales().entrySet()) {
            textPanel = new TextPanel();
            label = new CustomLabel(sale.getKey() + ": " + sale.getValue(), Font.BOLD | Font.ITALIC);
            label.setForeground(Color.RED);
            textPanel.add(label);

            for (Map.Entry<Ingredient, Double> ingredient : sale.getKey().getAllIngredients().entrySet()) {
                label = new CustomLabel("\t" + ingredient.getKey() + ": "
                        + (ingredient.getKey().getUnit() == Unit.GRAMS ? Unit.flozToG(ingredient.getValue())
                                : ingredient.getValue())
                        + ingredient.getKey().getUnit(), Font.ITALIC);
                label.setForeground(Color.RED);
                textPanel.add(label);
            }

            if (sale instanceof SpecialCoffee)
                for (Map.Entry<SyrupIngredient, Double> syrupIngredient : ((SpecialCoffee) sale.getKey())
                        .getSyrupIngredients().entrySet()) {
                    label = new CustomLabel("\t" + syrupIngredient.getKey() + ": " + syrupIngredient.getValue()
                            + syrupIngredient.getKey().getUnit(), Font.ITALIC);
                    label.setForeground(Color.RED);
                    textPanel.add(label);
                }

            panel.add(textPanel);
        }

        boolean special = coffeeTruck instanceof SpecialCoffeeTruck;

        textPanel = new TextPanel();
        textPanel.add(new CustomLabel("Coffee Prices per " + Unit.FL_OZ, Font.BOLD));

        for (Map.Entry<CoffeeType, Money> coffeePrice : user.getCoffeePrices().entrySet())
            textPanel.add(new CustomLabel("\t" + coffeePrice.getKey() + ": "
                    + (special ? coffeePrice.getValue()
                            : new Money(coffeePrice.getValue().getAmount()
                                    + user.getEspressoPrices().get(Espresso.STANDARD).getAmount())),
                    Font.PLAIN));

        panel.add(textPanel);

        if (special) {
            textPanel = new TextPanel();
            textPanel.add(new CustomLabel("Espresso Prices per " + Unit.FL_OZ, Font.BOLD));

            for (Map.Entry<Espresso, Money> espressoPrice : user.getEspressoPrices().entrySet())
                textPanel.add(
                        new CustomLabel("\t" + espressoPrice.getKey() + ": " + espressoPrice.getValue(), Font.PLAIN));

            panel.add(textPanel);
            textPanel = new TextPanel();
            textPanel.add(new CustomLabel("Syrup Prices", Font.BOLD));

            for (Map.Entry<SyrupIngredient, Money> syrupPrice : user.getSyrupPrices().entrySet())
                textPanel.add(new CustomLabel("\t" + syrupPrice.getKey() + ": " + syrupPrice.getValue(), Font.PLAIN));

            panel.add(textPanel);
        }
        new CustomDialog(frame, "View truck information", panel);
    }

    @Override
    public int chooseCoffeeTruck(List<CoffeeTruck> coffeeTrucks) {
        JPanel panel = new JPanel();
        CustomComboBox<CoffeeTruck> coffeeTruckInput = new CustomComboBox<>(coffeeTrucks.toArray(CoffeeTruck[]::new));

        panel.add(coffeeTruckInput);

        new CustomDialog(frame, "Choose a Coffee Truck", panel);

        return coffeeTruckInput.getSelectedIndex();
    }

    @Override
    public int chooseStorageBin(List<StorageBin> storageBins) {
        JPanel panel = new JPanel();
        CustomComboBox<StorageBin> storageBinInput = new CustomComboBox<>(storageBins.toArray(StorageBin[]::new));

        panel.add(storageBinInput);

        new CustomDialog(frame, "Choose a Storage Bin", panel);

        return storageBinInput.getSelectedIndex();
    }

    @Override
    public double addStorageBinQuantity() {
        JPanel panel = new JPanel();
        CustomTextField quantityInput = new CustomTextField("Add quantity");

        panel.add(quantityInput);

        new CustomDialog(frame, "Replenish Storage Bin", panel);

        return parseNumber(quantityInput.getText());
    }

    @Override
    public StorageBin setStorageBin(boolean special) {
        JPanel panel = new JPanel();

        if (!special) {
            DropdownWithNumberInput<Ingredient> storageBinInput = new DropdownWithNumberInput<>(Ingredient.values(),
                    "Quantity");

            storageBinInput.getComboBox().setSelectedItem(Ingredient.NONE);
            panel.add(storageBinInput);

            new CustomDialog(frame, "Replace with a different Ingredient", panel);

            return new StorageBin((Ingredient) storageBinInput.getComboBox().getSelectedItem(),
                    storageBinInput.getComboBox().getSelectedItem() == Ingredient.NONE ? 0
                            : parseNumber(storageBinInput.getTextField().getText()));
        }

        // Special

        DropdownWithNumberInput<SyrupIngredient> specialStorageBinInput = new DropdownWithNumberInput<>(
                SyrupIngredient.values(), "Quantity");

        specialStorageBinInput.getComboBox().setSelectedItem(SyrupIngredient.NONE);
        panel.add(specialStorageBinInput);

        new CustomDialog(frame, "Replace with a different Ingredient", panel);

        return new SpecialStorageBin((SyrupIngredient) specialStorageBinInput.getComboBox().getSelectedItem(),
                specialStorageBinInput.getComboBox().getSelectedItem() == SyrupIngredient.NONE ? 0
                        : parseNumber(specialStorageBinInput.getTextField().getText()));

    }

    @Override
    public String setCoffeeTruckLocation() {
        JPanel panel = new JPanel();
        CustomTextField locationInput = new CustomTextField("Set new location");

        panel.add(locationInput);

        new CustomDialog(frame, "Change truck location", panel);

        validateString(locationInput.getText());

        return locationInput.getText();
    }

    @Override
    public void dashboard(List<CoffeeTruck> coffeeTrucks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dashboard'");
    }

    @Override
    public <E extends Enum<E>> Map<E, Money> setPrices(Class<E> priceClass) {
        JPanel panel = new JPanel();
        Map<E, CustomTextField> pricesInput = new HashMap<>();

        CustomTextField tempPriceInput;

        for (E entry : Arrays.stream(priceClass.getEnumConstants()).filter((entry) -> !entry.toString().equals("NONE"))
                .toList()) {
            pricesInput.put(entry, tempPriceInput = new CustomTextField("Price for " + entry + " per " + Unit.FL_OZ));
            panel.add(tempPriceInput);
        }

        new CustomDialog(frame, "Change product prices", panel);

        return Collections.unmodifiableMap(pricesInput.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                priceInput -> new Money((float) parseNumber(priceInput.getValue().getText())))));
    }
}
