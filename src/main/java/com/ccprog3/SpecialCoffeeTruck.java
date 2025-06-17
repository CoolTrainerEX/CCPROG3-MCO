package com.ccprog3;

/**
 * Special Coffee Truck with special features
 * @author Justin Ryan Uy
 */
public class SpecialCoffeeTruck extends CoffeeTruck {
    
    /**
     * Special Storage Bins to contain Syrup Ingredients
     * @author Justin Ryan Uy
     */
    private final SpecialStorageBin[] specialStorageBins = new SpecialStorageBin[2];
    
     /**
      * Coffee Truck constructor
      * @param location Coffee Truck location
      * @param storageBins The Storage Bins to be placed in the Coffee Truck (Max 8)
      * @param specialStorageBins The Special Storage Bins to be placed in the Coffee Truck (Max 2)
      * @throws IndexOutOfBoundsException Too much Storage Bins set
      * @author Justin Ryan Uy
      */
    public SpecialCoffeeTruck(String location, StorageBin[] storageBins, SpecialStorageBin[] specialStorageBins) throws IndexOutOfBoundsException {
        super(location, storageBins);
        
        try {
            System.arraycopy(specialStorageBins, 0, this.specialStorageBins, 0, specialStorageBins.length);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Too much Special Storage Bins set");
        }
    }
    
    /**
     * Adds to the quantity of the Special Storage Bin. Negative numbers can be used to subtract.
     * @param quantity The quantity to add
     * @param index Index of the Special Storage Bin to add
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public void addSpecialStorageBinQuantity(int quantity, int index) throws ArrayIndexOutOfBoundsException, ArithmeticException {
        specialStorageBins[index].addQuantity(quantity);
    }

    /**
     * Sets a new Ingredient into a Special Storage Bin
     * @param storageBin new Special Storage Bin to be replaced with
     * @param index Index of the Special Storage Bin to replace
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @author Justin Ryan Uy
     */
    public void setSpecialStorageBin(SpecialStorageBin specialStorageBin, int index) throws ArrayIndexOutOfBoundsException {
        specialStorageBins[index] = specialStorageBin;
    }

    /**
     * Empties a Special Storage Bin
     * @param index Index of the Special Storage Bin to empty
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @author Justin Ryan Uy
     */
    public void emptyStorageBin(int index) throws ArrayIndexOutOfBoundsException {
        specialStorageBins[index] = new SpecialStorageBin(SyrupIngredient.NONE, 0);
    }
}
