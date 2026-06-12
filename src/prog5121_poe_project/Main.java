package prog5121_poe_project;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("===== USER REGISTRATION =====");

        System.out.print("First Name: ");
        String firstName = input.nextLine();

        System.out.print("Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        System.out.print("Cell Number: ");
        String cell = input.nextLine();

        String reg = loginSystem.registerUser(username, password, firstName, lastName, cell);

        System.out.println("\n" + reg);

        if (reg.startsWith("Username successfully captured")) {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Username: ");
            String u = input.nextLine();

            System.out.print("Password: ");
            String p = input.nextLine();

            boolean success = loginSystem.loginUser(u, p);

            System.out.println(loginSystem.returnLoginStatus(success));

            if (success) {

                boolean quit = false;
                int counter = 0;

                while (!quit) {

                    System.out.println("\n1. Send Messages");
                    System.out.println("2. Show Report");
                    System.out.println("3. Quit");
                    System.out.println("4. Stored Messages");

                    System.out.print("Choice: ");
                    String choice = input.nextLine();

                    switch (choice) {

                        case "1":

                            System.out.print("How many messages? ");
                            int num = Integer.parseInt(input.nextLine());

                            for (int i = 0; i < num; i++) {

                                System.out.print("Recipient: ");
                                String rec = input.nextLine();

                                System.out.print("Message: ");
                                String msg = input.nextLine();

                                Message m = new Message(counter, rec, msg);

                                System.out.println(m.checkRecipientCell());
                                System.out.println(m.checkMessageLength());

                                if (m.checkMessageLength().equals("Message ready to send.")) {

                                    System.out.println(m.printMessages());

                                    System.out.print("1 Send 2 Disregard 3 Store: ");
                                    int action = Integer.parseInt(input.nextLine());

                                    System.out.println(m.sentMessage(action));

                                    if (action == 1) MessageManager.addSentMessage(m);
                                    if (action == 2) MessageManager.addDisregardedMessage(m);
                                    if (action == 3) MessageManager.addStoredMessage(m);

                                    counter++;
                                }
                            }

                            System.out.println("Total sent: " + Message.returnTotalMessages());
                            break;

                        case "2":
                            System.out.println(MessageManager.generateReport());
                            break;

                        case "3":
                            quit = true;
                            break;

                        case "4":

                            System.out.println("\n===== STORED MESSAGES =====");

                            System.out.println("Stored Count: "
                                    + MessageManager.storedMessages.size());

                            System.out.println("Longest Message: "
                                    + MessageManager.getLongestMessage());

                            System.out.println("\nStored JSON Data:");

                            MessageManager.loadStoredMessages();
                            
                            System.out.print("\nEnter hash to delete: ");
                            String hash = input.nextLine();

                            if (MessageManager.deleteByHash(hash)) {
                                System.out.println("Message deleted.");
                            } else {
                                System.out.println("Hash not found.");
                            }
                            
                            System.out.print("\nEnter recipient to search: ");
                            String recipientSearch = input.nextLine();

                            MessageManager.displayMessagesForRecipient(recipientSearch);
                            
                            System.out.print("\nEnter Message ID to search: ");
                            String idSearch = input.nextLine();

                            MessageManager.displayMessageByID(idSearch);

                            break;
                            
                            

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }

        input.close();
    }
}