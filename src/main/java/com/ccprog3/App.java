package com.ccprog3;

/**
 * Driver class
 * @author Justin Ryan Uy
 */
public class App 
{
    public static void main( String[] args )
    {
        // TODO: Write better header
        System.out.println( "\nWelcome to JavaJeeps!\n" );

        try (UserSingleton user = UserSingleton.getInstance()) {
            boolean close = false;
            while (!close) {
                close = user.mainMenu();
            }
        }

        // TODO: Write better closer
        System.out.println("\nGoodbye!\n");
    }
}
