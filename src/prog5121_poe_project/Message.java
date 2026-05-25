package prog5121_poe_project;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;

    private static int totalMessages = 0;

    // Constructor
    public Message(int messageNumber,
                   String recipient,
                   String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        generateMessageID();
    }

    // Generate random 10 digit ID
    private void generateMessageID() {

        Random random = new Random();

        long number =
                1000000000L +
                (long)(random.nextDouble() * 9000000000L);

        messageID = String.valueOf(number);
    }

    // Check Message ID length
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Check recipient number
    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Check message length
    public String checkMessageLength() {

        if (messageText.length() <= 250) {

            return "Message ready to send.";
        }

        int excess =
                messageText.length() - 250;

        return "Message exceeds 250 characters by "
                + excess
                + "; please reduce the size.";
    }

    // Create message hash
    public String createMessageHash() {

        String[] words =
                messageText.split(" ");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    // Store message in JSON file
    public void storeMessage() {

        try {

            FileWriter writer =
                    new FileWriter("storedMessages.json", true);

            writer.write("{\n");
            writer.write("\"messageID\":\"" + messageID + "\",\n");
            writer.write("\"recipient\":\"" + recipient + "\",\n");
            writer.write("\"message\":\"" + messageText + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error storing message.");
        }
    }

    // Send / Store / Disregard
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:

                totalMessages++;

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete the message.";

            case 3:

                storeMessage();

                return "Message successfully stored.";

            default:

                return "Invalid option.";
        }
    }

    // Print details
    public String printMessages() {

        return """
               Message ID: %s
               Message Hash: %s
               Recipient: %s
               Message: %s
               """.formatted(
                messageID,
                createMessageHash(),
                recipient,
                messageText
        );
    }

    // Total sent messages
    public static int returnTotalMessages() {

        return totalMessages;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }
}