package com.ccprog3;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // TODO: Write better header
        System.out.println( "Welcome to JavaJeeps!" );

        try (UserSingleton user = UserSingleton.getInstance()) {
            boolean close = false;
            while (!close) {
                close = user.mainMenu();
            }
        }

        // TODO: Write better closer
        System.out.println("Goodbye!");
    }
}
