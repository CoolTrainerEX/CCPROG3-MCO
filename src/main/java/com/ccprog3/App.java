package com.ccprog3;

/**
 * Driver class
 * @author Justin Ryan Uy
 */
public class App 
{
    public static void main( String[] args )
    {
        UI ui = new CLISingleton();

        ui.login();

        if (ui instanceof CLISingleton) ((CLISingleton) ui).close();
    }
}
