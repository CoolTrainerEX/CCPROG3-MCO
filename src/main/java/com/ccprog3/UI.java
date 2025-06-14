package com.ccprog3;

import java.util.Arrays;

/**
 * UI class for user display and input
 * @author Justin Ryan Uy
 */
public abstract class UI implements AutoCloseable {
    /**
     * The user to interact with
     * @author Justin Ryan Uy
     */
    protected final UserSingleton user = UserSingleton.getInstance();

    public void close() {
        user.close();
    }

    /**
     * Utility class for radio text description and execution
     * @author Justin Ryan Uy
     */
    protected static class Option {
        /**
         * Text to display
         * @author Justin Ryan Uy
         */
        private final String text;

        /**
         * Function to execute
         * @author Justin Ryan Uy
         */
        private final OptionFunc func;

        /**
         * Constructor for Option class
         * @param text Text to display
         * @param func Function to execute
         * @author Justin Ryan Uy
         */
        public Option(String text, OptionFunc func) {
            this.text = text;
            this.func = func;
        }

        /**
         * Gets the display text
         * @return The text
         * @author Justin Ryan Uy
         */
        public String getText() {
            return text;
        }

        /**
         * Executes the function
         * @return function return
         * @author Justin Ryan Uy
         */
        public boolean exec() {
            return func.exec();
        }
        
        /**
         * Interface for handling lambda expressions
         * @author Justin Ryan Uy
         */
        @FunctionalInterface
        protected static interface OptionFunc {
            public boolean exec();
        }
    }

    /**
     * Radio display function to be called by menu()
     * @param options Text options to display
     * @return Option number; 0 = back; -1 = exit
     * @author Justin Ryan Uy
     */
    protected abstract int radio(String... options);

    /**
     * Handles menu options
     * @param options Options to choose from
     * @return false = back; true = exit
     * @author Justin Ryan Uy
     */
    protected boolean menu(Option... options) {
        int choice;

        while ((choice = radio(Arrays.stream(options).map(Option::getText).toArray(String[]::new))) != 0)
            if (choice == -1 || options[choice - 1].exec())
                return true;

        return false;
    }

    // UI

    /**
     * Login screen
     * @author Justin Ryan Uy
     */
    public abstract void login();

    /**
     * Main menu for the program.
     * @author Justin Ryan Uy
     */
    public void mainMenu() {
        menu(
            new Option("Create a Coffee Truck", this::createCoffeeTruck),
            new Option("Perform Coffee Truck features", () -> true),
            new Option("Dashboard", () -> true));
    }

    protected abstract boolean createCoffeeTruck();
}
