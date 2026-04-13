/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapp;

/**
 *
 * @author [Lehlohonolo Malowadihlare]
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    Login login = new Login();

    @Test
    public void testUsernameCorrect() {
        // PoE required test data
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        // PoE required test data
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordCorrect() {
        // PoE required test data
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrect() {
        // PoE required test data
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellNumberCorrect() {
        // PoE required test data
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginMessageSuccess() {
        String message = login.returnLoginStatus(true);
        assertEquals("Welcome John, Doe it is great to see you again.", message);
    }
}
//version 2