package prog5121_poe_project;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Login loginSystem = new Login();

        // =========================
        // USER REGISTRATION
        // =========================

        System.out.println("===== USER REGISTRATION =====");

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Cell Phone Number: ");
        String cellNo = input.nextLine();

        String registrationReport =
                loginSystem.registerUser(
                        username,
                        password,
                        firstName,
                        lastName,
                        cellNo
                );

        System.out.println("\n===== REGISTRATION STATUS =====");
        System.out.println(registrationReport);

        // =========================
        // LOGIN SECTION
        // =========================

        if (registrationReport.contains("successfully")) {

            System.out.println("\n===== USER LOGIN =====");

            System.out.print("Enter Username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            boolean loginSuccess =
                    loginSystem.loginUser(
                            loginUsername,
                            loginPassword
                    );

            String loginStatus =
                    loginSystem.returnLoginStatus(
                            loginSuccess
                    );

            System.out.println("\n===== LOGIN STATUS =====");
            System.out.println(loginStatus);

            // =========================
            // QUICKCHAT MENU
            // =========================

            if (loginSuccess) {

                System.out.println("\nWelcome to QuickChat.");

                boolean quit = false;

                int messageCounter = 0;

                while (!quit) {

                    System.out.println("\n===== QUICKCHAT MENU =====");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");

                    System.out.print("Choose an option: ");

                    String menuChoice =
                            input.nextLine();

                    switch (menuChoice) {

                        // =========================
                        // SEND MESSAGE
                        // =========================

                        case "1":

                            int numMessages = 0;

                            while (true) {

                                try {

                                    System.out.print(
                                            "How many messages would you like to send? ");

                                    numMessages =
                                            Integer.parseInt(
                                                    input.nextLine()
                                            );

                                    if (numMessages > 0) {
                                        break;
                                    }

                                    System.out.println(
                                            "Please enter a number greater than 0.");

                                } catch (NumberFormatException e) {

                                    System.out.println(
                                            "Invalid number entered.");
                                }
                            }

                            for (int i = 0;
                                 i < numMessages;
                                 i++) {

                                System.out.println(
                                        "\n===== MESSAGE "
                                                + (i + 1)
                                                + " =====");

                                System.out.print(
                                        "Enter recipient cell number: ");

                                String recipient =
                                        input.nextLine();

                                System.out.print(
                                        "Enter your message: ");

                                String messageText =
                                        input.nextLine();

                                Message message =
                                        new Message(
                                                messageCounter,
                                                recipient,
                                                messageText
                                        );

                                System.out.println(
                                        message.checkRecipientCell()
                                );

                                String lengthResult =
                                        message.checkMessageLength();

                                System.out.println(lengthResult);

                                if (lengthResult.equals(
                                        "Message ready to send.")) {

                                    System.out.println(
                                            "\n===== MESSAGE DETAILS =====");

                                    System.out.println(
                                            message.printMessages()
                                    );

                                    System.out.println(
                                            "Choose an option:");
                                    System.out.println(
                                            "1. Send Message");
                                    System.out.println(
                                            "2. Disregard Message");
                                    System.out.println(
                                            "3. Store Message");

                                    System.out.print(
                                            "Your choice: ");

                                    int action =
                                            Integer.parseInt(
                                                    input.nextLine()
                                            );

                                    System.out.println(
                                            message.sentMessage(action)
                                    );

                                    messageCounter++;
                                }
                            }

                            // PART 2 REQUIREMENT
                            System.out.println(
                                    "\nTotal messages sent: "
                                            + Message.returnTotalMessages()
                            );

                            break;

                        // =========================
                        // SHOW REPORT
                        // =========================

                        case "2":

                            System.out.println(
                                    "Coming Soon.");

                            break;

                        // =========================
                        // QUIT
                        // =========================

                        case "3":

                            System.out.println(
                                    "Exiting QuickChat...");

                            quit = true;

                            break;

                        default:

                            System.out.println(
                                    "Invalid menu option.");
                    }
                }
            }

        } else {

            System.out.println(
                    "\nRegistration failed.");
        }

        input.close();
    }
}