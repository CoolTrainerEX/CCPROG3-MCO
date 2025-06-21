package com.ccprog3;

import java.util.List;

/**
 * GUI user interface
 * 
 * @author Justin Ryan Uy
 */
public class GUISingleton implements UI {
    /**
     * Singleton instance of the GUI
     * 
     * @author Justin Ryan Uy
     */
    private static final GUISingleton instance = new GUISingleton();

    /**
     * Gets the GUI singleton instance
     * 
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static GUISingleton getInstance() {
        return instance;
    }

    @Override
    public int menu(String... options) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menu'");
    }

    @Override
    public void displayErr(Exception e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayErr'");
    }

    @Override
    public String login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public CoffeeTruck addCoffeeTruck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCoffeeTruck'");
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
}
