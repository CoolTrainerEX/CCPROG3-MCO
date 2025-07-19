package com.ccprog3;

import org.junit.Test;

import com.ccprog3.coffeeTruck.CoffeeTruck;
import com.ccprog3.coffeeTruck.StorageBin;

/**
 * Tests for User class
 * 
 * @author Justin Ryan Uy
 */
public class UserTest {
    /**
     * Singleton instance
     */
    UserSingleton user = UserSingleton.getInstance();

    /**
     * Default constructor
     */
    public UserTest() {
    }

    /**
     * Tests {@code addCoffeeTruck} with different locations
     */
    @Test
    public void testAddCoffeeTruckValid() {
        user.addCoffeeTruck(new CoffeeTruck("a", new StorageBin[8]));
        user.addCoffeeTruck(new CoffeeTruck("b", new StorageBin[8]));
    }

    /**
     * Tests {@code addCoffeeTruck} with the same locations
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddCoffeeTruckInvalidLocation() {
        user.addCoffeeTruck(new CoffeeTruck("c", new StorageBin[8]));
        user.addCoffeeTruck(new CoffeeTruck("c", new StorageBin[8]));
    }

    /**
     * Tests {@code addCoffeeTruck} with the same CoffeeTruck instance
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddCoffeeTruckInvalid() {
        CoffeeTruck coffeeTruck = new CoffeeTruck("d", new StorageBin[8]);

        user.addCoffeeTruck(coffeeTruck);
        user.addCoffeeTruck(coffeeTruck);
    }

    /**
     * Tests {@code setCoffeeTruckLocation} with different locations
     */
    @Test
    public void testSetCoffeeTruckLocationValid() {
        user.addCoffeeTruck(new CoffeeTruck("e", new StorageBin[8]));

        user.setCoffeeTruckLocation("f", 0);
    }

    /**
     * Tests {@code addCoffeeTruck} with the same locations
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCoffeeTruckLocationInvalid() {
        user.addCoffeeTruck(new CoffeeTruck("g", new StorageBin[8]));

        user.setCoffeeTruckLocation("g", 0);
    }

    /**
     * Tests {@code addCoffeeTruck} with invalid index
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetCoffeeTruckLocationInvalidIndex() {
        user.setCoffeeTruckLocation("h", 100);
    }
}
