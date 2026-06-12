package prog5121_poe_project;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class MessageManagerTest {

    @Test
    public void testLongestMessage() {

        MessageManager.storedMessages.clear();

        Message m1 = new Message(
                0,
                "+27838884567",
                "Hello"
        );

        Message m2 = new Message(
                1,
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."
        );

        MessageManager.addStoredMessage(m1);
        MessageManager.addStoredMessage(m2);

        String expected =
                "Where are you? You are late! I have asked you to be on time.";

        assertEquals(
                expected,
                MessageManager.getLongestMessage()
        );
    }

    @Test
    public void testSearchByRecipient() {

        MessageManager.storedMessages.clear();

        Message m1 = new Message(
                0,
                "+27838884567",
                "Did you get the cake?"
        );

        Message m2 = new Message(
                1,
                "+27838884567",
                "It is dinner time!"
        );

        MessageManager.addStoredMessage(m1);
        MessageManager.addStoredMessage(m2);

        ArrayList<Message> results =
                MessageManager.searchByRecipient(
                        "+27838884567"
                );

        assertEquals(2, results.size());
    }

    @Test
    public void testSearchByMessageID() {

        MessageManager.storedMessages.clear();

        Message message = new Message(
                0,
                "+27838884567",
                "It is dinner time!"
        );

        MessageManager.addStoredMessage(message);

        Message result =
                MessageManager.searchByMessageID(
                        message.getMessageID()
                );

        assertNotNull(result);

        assertEquals(
                "It is dinner time!",
                result.getMessageText()
        );
    }

    @Test
    public void testDeleteByHash() {

        MessageManager.storedMessages.clear();

        Message message = new Message(
                0,
                "+27838884567",
                "Did you get the cake?"
        );

        MessageManager.addStoredMessage(message);

        String hash =
                message.createMessageHash();

        boolean deleted =
                MessageManager.deleteByHash(hash);

        assertTrue(deleted);
    }

    @Test
    public void testGenerateReport() {

        MessageManager.storedMessages.clear();

        Message message = new Message(
                0,
                "+27838884567",
                "Did you get the cake?"
        );

        MessageManager.addStoredMessage(message);

        String report =
                MessageManager.generateReport();

        assertTrue(
                report.contains(
                        "Did you get the cake?"
                )
        );
    }
}