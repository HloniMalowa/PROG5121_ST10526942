package prog5121_poe_project;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testCheckUserNameCorrectFormat() {

        Login login = new Login();

        boolean result = login.checkUserName("kyl_1");

        assertTrue(result);
    }

    @Test
    public void testCheckUserNameIncorrectFormat() {

        Login login = new Login();

        boolean result = login.checkUserName("kyle!!!!!!!");

        assertFalse(result);
    }

    @Test
    public void testCheckPasswordComplexitySuccess() {

        Login login = new Login();

        boolean result =
                login.checkPasswordComplexity("Ch&&sec@ke99!");

        assertTrue(result);
    }

    @Test
    public void testCheckPasswordComplexityFailure() {

        Login login = new Login();

        boolean result =
                login.checkPasswordComplexity("password");

        assertFalse(result);
    }

    @Test
    public void testCheckCellPhoneNumberSuccess() {

        Login login = new Login();

        boolean result =
                login.checkCellPhoneNumber("+27838968976");

        assertTrue(result);
    }

    @Test
    public void testCheckCellPhoneNumberFailure() {

        Login login = new Login();

        boolean result =
                login.checkCellPhoneNumber("08966553");

        assertFalse(result);
    }

    @Test
    public void testRegisterUserSuccessMessage() {

        Login login = new Login();

        String result = login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "Kyle",
                "Smith",
                "+27838968976"
        );

        String expected =
                "Username successfully captured.\n" +
                "Password successfully captured.\n" +
                "Cell phone number successfully added.";

        assertEquals(expected, result);
    }

    @Test
    public void testLoginSuccess() {

        Login login = new Login();

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "Kyle",
                "Smith",
                "+27838968976"
        );

        boolean result =
                login.loginUser(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                );

        assertTrue(result);
    }

    @Test
    public void testLoginFailure() {

        Login login = new Login();

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "Kyle",
                "Smith",
                "+27838968976"
        );

        boolean result =
                login.loginUser(
                        "wrongUser",
                        "wrongPass"
                );

        assertFalse(result);
    }

    @Test
    public void testReturnLoginStatusSuccess() {

        Login login = new Login();

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "Kyle",
                "Smith",
                "+27838968976"
        );

        String result =
                login.returnLoginStatus(true);

        String expected =
                "Welcome Kyle, Smith it is great to see you again.";

        assertEquals(expected, result);
    }

    @Test
    public void testReturnLoginStatusFailure() {

        Login login = new Login();

        String result =
                login.returnLoginStatus(false);

        String expected =
                "Username or password incorrect, please try again.";

        assertEquals(expected, result);
    }
}