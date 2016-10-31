package com.akikanellis.authenticator.examples;

import com.akikanellis.authenticator.Authenticator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            startOptionLoop(scanner);
        } catch (Exception e) {
            System.err.println("Something went wrong: " + e);
        }
    }

    private static void startOptionLoop(Scanner scanner) {
        Authenticator authenticator = new Authenticator();

        while (true) {
            System.out.print("Available options:" +
                    "\n1 - Password generation" +
                    "\n2 - Password verification" +
                    "\n3 - Exit" +
                    "\nPlease select an option by typing its number: ");
            int option = extractOption(scanner);
            System.out.println();

            switch (option) {
                case 1:
                    passwordGeneration(authenticator, scanner);
                    break;
                case 2:
                    passwordAuthentication(authenticator, scanner);
                    break;
                case 3:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Unknown option was selected. Please try again.\n");
            }
        }
    }

    private static int extractOption(Scanner scanner) {
        try {
            String option = scanner.nextLine();
            return Integer.parseInt(option);
        } catch (InputMismatchException | NumberFormatException e) {
            return -1;
        }
    }

    private static void passwordGeneration(Authenticator authenticator, Scanner scanner) {
        System.out.println("---Password generation---");
        System.out.print("Enter a user id: ");
        String userId = scanner.nextLine();

        int password = authenticator.generatePassword(userId);

        System.out.printf("Password has been generated." +
                "\nUser id: %s" +
                "\nPassword: %d\n\n", userId, password);
    }

    private static void passwordAuthentication(Authenticator authenticator, Scanner scanner) {
        System.out.println("---Password authentication---");
        System.out.print("Enter a user id: ");
        String userId = scanner.nextLine();
        int password = extractPassword(scanner);

        boolean valid = authenticator.isPasswordValid(userId, password);

        System.out.printf("Verification %s\n\n", valid ? "succeeded" : "failed");
    }

    private static int extractPassword(Scanner scanner) {
        int password;
        do {
            System.out.print("Enter a password: ");
            try {
                String line = scanner.nextLine();
                password = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("This was not a valid password. Please try again.");
                password = -1;
            }
        } while (password == -1);

        return password;
    }
}
