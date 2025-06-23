package com.ccprog3.coffeeTruck;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.ccprog3.ingredients.SyrupIngredient;

/**
 * Special Coffee Truck with special features
 * 
 * @author Justin Ryan Uy
 */
public class SpecialCoffeeTruck extends CoffeeTruck {

    /**
     * Special Storage Bins to contain Syrup Ingredients
     * 
     * @author Justin Ryan Uy
     */
    private final SpecialStorageBin[] specialStorageBins = new SpecialStorageBin[2];

    /**
     * Coffee Truck constructor
     * 
     * @param location           Coffee Truck location
     * @param storageBins        The Storage Bins to be placed in the Coffee Truck
     *                           (Size 8)
     * @param specialStorageBins The Special Storage Bins to be placed in the Coffee
     *                           Truck (Size 2)
     * @throws IndexOutOfBoundsException Too much Storage Bins set
     * @author Justin Ryan Uy
     */
    public SpecialCoffeeTruck(String location, StorageBin[] storageBins, SpecialStorageBin[] specialStorageBins)
            throws IndexOutOfBoundsException {
        super(location, storageBins);

        try {
            System.arraycopy(specialStorageBins, 0, this.specialStorageBins, 0, specialStorageBins.length);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Too much Special Storage Bins set");
        }
    }

    public String toString() {
        return "Special Coffee Truck" + ": " + super.getLocation();
    }

    public List<StorageBin> getStorageBins() {
        return Stream.concat(Arrays.stream(storageBins), Arrays.stream(specialStorageBins)).toList();
    }

    public void setStorageBin(StorageBin storageBin, int index) throws ArrayIndexOutOfBoundsException {
        if (!(storageBin instanceof SpecialStorageBin))
            super.setStorageBin(storageBin, index);
        else
            specialStorageBins[index - storageBins.length] = (SpecialStorageBin) storageBin;
    }

    public void emptyStorageBin(int index) throws ArrayIndexOutOfBoundsException {
        if (index < storageBins.length)
            super.emptyStorageBin(index);
        else
            specialStorageBins[index - storageBins.length] = new SpecialStorageBin(SyrupIngredient.NONE, 0);

    }
}
