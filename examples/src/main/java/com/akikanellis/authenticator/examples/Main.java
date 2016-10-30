package com.akikanellis.authenticator.examples;

import com.akikanellis.authenticator.Authenticator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Authenticator authenticator = new Authenticator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please select an option from the following:" +
                    "\n1 - Password generation" +
                    "\n2 - Password verification" +
                    "\n3 - Exit");
            int option;
            try {
                String line = scanner.nextLine();
                option = Integer.parseInt(line);
            } catch (InputMismatchException | NumberFormatException e) {
                option = -1;
            }

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
                    System.out.println("Unknown option was selected. Please try again.");
            }
        }
    }

    private static void passwordGeneration(Authenticator authenticator, Scanner scanner) {
        System.out.println("Password generation.");
        System.out.print("Enter a user id: ");
        String userId = scanner.nextLine();

        int password = authenticator.generatePassword(userId);

        System.out.printf("Password has been generated." +
                "\nUser id: %s" +
                "\nPassword: %d", userId, password);
    }

    private static void passwordAuthentication(Authenticator authenticator, Scanner scanner) {
        System.out.println("Password authentication.");
        System.out.print("Enter a user id: ");
        String userId = scanner.nextLine();

        int password;
        do {
            System.out.print("Enter a password: ");
            try {
                String line = scanner.nextLine();
                password = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                password = -1;
            }
        } while (password == -1);

        boolean valid = authenticator.isPasswordValid(userId, password);

        System.out.printf("Verification %s\n", valid ? "succeeded" : "failed");
    }
}
