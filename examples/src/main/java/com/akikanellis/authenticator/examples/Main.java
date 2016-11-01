package com.akikanellis.authenticator.examples;

import com.akikanellis.authenticator.Authenticator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@SuppressWarnings("PMD.SystemPrintln")
public final class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() { throw new AssertionError("No instances."); }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            startOptionLoop(scanner);
        } catch (Exception e) {
            LOGGER.log(SEVERE, "Something went wrong", e);
        } finally {
            exit();
        }
    }

    private static void startOptionLoop(Scanner scanner) {
        Authenticator authenticator = new Authenticator();

        boolean finished = false;
        while (!finished) {
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
                    finished = true;
                    break;
                default:
                    System.out.println("Unknown option was selected. Please try again.\n");
                    break;
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
                "%nUser id: %s" +
                "%nPassword: %d%n%n", userId, password);
    }

    private static void passwordAuthentication(Authenticator authenticator, Scanner scanner) {
        System.out.println("---Password authentication---");
        System.out.print("Enter a user id: ");
        String userId = scanner.nextLine();
        int password = extractPassword(scanner);

        boolean valid = authenticator.isPasswordValid(userId, password);

        System.out.printf("Verification %s%n%n", valid ? "succeeded" : "failed");
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

    private static void exit() {
        System.out.println("Exiting.");
        shutdownRemainingThreadsAndExit();
    }

    @SuppressFBWarnings("DM_EXIT") private static void shutdownRemainingThreadsAndExit() { System.exit(0); }
}
