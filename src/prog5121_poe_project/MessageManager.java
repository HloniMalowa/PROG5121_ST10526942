package prog5121_poe_project;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MessageManager {

    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<String>  messageIDs = new ArrayList<>();
    public static ArrayList<String>  messageHashes = new ArrayList<>();

    public static void loadStoredMessages() {

    try (BufferedReader br =
            new BufferedReader(
                    new FileReader("storedMessages.json"))) {

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    } catch (IOException e) {
        System.out.println("Unable to read JSON file.");
    }
}
    public static void addSentMessage(Message m) {
        sentMessages.add(m);
        messageIDs.add(m.getMessageID());
        messageHashes.add(m.createMessageHash());
    }

    public static void addStoredMessage(Message m) {
        storedMessages.add(m);
        messageIDs.add(m.getMessageID());
        messageHashes.add(m.createMessageHash());
    }

    public static void addDisregardedMessage(Message m) {
        disregardedMessages.add(m);
    }

    public static String getLongestMessage() {

    if (storedMessages.isEmpty()) {

        return "No stored messages available.";

    }

    String longest = "";

    for (Message m : storedMessages) {

        if (m.getMessageText().length() > longest.length()) {

            longest = m.getMessageText();

        }

    }

    return longest;

}

    public static Message searchByMessageID(String id) {

    for (Message m : storedMessages) {
        if (m.getMessageID().equals(id)) {
            return m;
        }
    }

    return null;
}
    public static void displayMessageByID(String id) {

        Message m = searchByMessageID(id);

        if (m != null) {

            System.out.println("Recipient: "
                    + m.getRecipient());

            System.out.println("Message: "
                    + m.getMessageText());

        } else {

            System.out.println("Message not found.");
        }
    }

    public static ArrayList<Message> searchByRecipient(String recipient) {

    ArrayList<Message> results = new ArrayList<>();

    for (Message m : storedMessages) {

        if (m.getRecipient().equals(recipient)) {

            results.add(m);

        }

    }

    return results;

}
    
    public static void displayMessagesForRecipient(
            String recipient) {

        ArrayList<Message> list =
            searchByRecipient(recipient);

        for (Message m : list) {
            System.out.println(
                m.getMessageText());
        }
    }

    public static boolean deleteByHash(String hash) {

    for (int i = 0; i < storedMessages.size(); i++) {

        Message m = storedMessages.get(i);

        if (m.createMessageHash().equals(hash)) {

            storedMessages.remove(i);

            return true;

        }

    }

    return false;

}

    public static String generateReport() {

        StringBuilder report = new StringBuilder("\n===== MESSAGE REPORT =====\n");

        for (Message m : storedMessages) {
    report.append(m.printMessages()).append("\n");
}

        return report.toString();
    }
}