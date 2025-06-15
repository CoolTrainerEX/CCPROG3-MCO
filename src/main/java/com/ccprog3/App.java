package com.ccprog3;

/**
 * Driver class
 * @author Justin Ryan Uy
 */
public class App 
{
    public static void main( String[] args )
    {
        try (ControllerSingleton controller = ControllerSingleton.getInstance()) {
            controller.login();
            controller.mainMenu();
        }
    }
}
