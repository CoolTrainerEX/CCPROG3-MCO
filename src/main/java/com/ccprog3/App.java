package com.ccprog3;

/**
 * Driver class
 * @author Justin Ryan Uy
 */
public class App 
{
    public static void main( String[] args )
    {
        try (UI ui = CLISingleton.getInstance()) {
            ui.login();
            ui.mainMenu();
        }
    }
}
