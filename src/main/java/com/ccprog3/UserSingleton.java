package com.ccprog3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User class for the runtime instance
 * @author Justin Ryan Uy
 */
public class UserSingleton implements AutoCloseable {
    private static final UserSingleton instance = new UserSingleton();
    private final String username;
    private final List<CoffeeTruck> coffeeTrucks = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    private UserSingleton() {
        System.out.print("Login (A new user will be created if the current user is not found): ");
        login(username = sc.nextLine());
    }

    public static UserSingleton getInstance() {
        return instance;
    }

    public void close() {
        sc.close();

        // TODO: Write file
    }

    public void login(String username) {
        try (Scanner filesc = new Scanner(new File(username))) {
            // TODO: read file
        } catch (FileNotFoundException e) {
            System.out.println("User not found. Will save to new user upon close.");
        }
    }

    public boolean mainMenu() {
        return true;
    }
}
