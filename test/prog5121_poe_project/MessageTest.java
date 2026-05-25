package prog5121_poe_project;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        longMessage
                );

        assertTrue(
                message.checkMessageLength()
                        .contains("Message exceeds")
        );
    }

    @Test
    public void testRecipientSuccess() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientFailure() {

        Message message =
                new Message(
                        0,
                        "08575975889",
                        "Hello"
                );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageIDCreated() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertTrue(
                message.checkMessageID()
        );
    }

    @Test
    public void testMessageHashCreated() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        String hash =
                message.createMessageHash();

        assertNotNull(hash);
    }

    @Test
    public void testSendMessageOption() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertEquals(
                "Message successfully sent.",
                message.sentMessage(1)
        );
    }

    @Test
    public void testDisregardMessageOption() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertEquals(
                "Press 0 to delete the message.",
                message.sentMessage(2)
        );
    }

    @Test
    public void testStoreMessageOption() {

        Message message =
                new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertEquals(
                "Message successfully stored.",
                message.sentMessage(3)
        );
    }
}