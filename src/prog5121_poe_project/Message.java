package prog5121_poe_project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;

    private static int totalMessages = 0;

    public Message(int messageNumber, String recipient, String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = (recipient == null) ? "" : recipient;
        this.messageText = (messageText == null) ? "" : messageText;

        generateMessageID();
    }

    private void generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + Math.abs(random.nextLong()) % 9000000000L;
        messageID = String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        return "Message exceeds 250 characters by "
                + (messageText.length() - 250)
                + "; please reduce the size.";
    }

    public String createMessageHash() {

        if (messageText.trim().isEmpty()) {
            return "INVALID:EMPTY";
        }

        String[] words = messageText.trim().split("\\s+");

        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + first
                + last;
    }

    public void storeMessage() {

    try (FileWriter writer =
            new FileWriter("storedMessages.json", true)) {

        writer.write("{\n");
        writer.write("\"messageID\":\"" + messageID + "\",\n");
        writer.write("\"recipient\":\"" + recipient + "\",\n");
        writer.write("\"message\":\"" + messageText + "\"\n");
        writer.write("}\n");

    } catch (IOException e) {
        System.out.println("Error storing message.");
    }
}

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