/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package quickchatapp;

/**
 * @author [Lehlohonolo Malowadihlare]
 */
public class Login {

    // Captured user data needed for login and the PoE welcome message
    private String username;
    private String password;
    private String firstName = "John"; // Default for testing
    private String lastName = "Doe";   // Default for testing
    private String cellPhone;

    // 1. checkUserName: Must have underscore and be <= 5 chars
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // 2. checkPasswordComplexity: 8 chars, Capital, Number, Special Char
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) return false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasCapital = true;
            if (Character.isDigit(ch)) hasNumber = true;
            if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // 3. checkCellPhoneNumber: Must start with +27 and be 12 digits total
    public boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+27") && number.length() == 12;
    }

    // 4. registerUser: Returns the exact success/fail messages from the PoE
    public String registerUser(String username, String password, String number) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!checkCellPhoneNumber(number)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            this.username = username;
            this.password = password;
            this.cellPhone = number;
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
        }
    }

    // 5. loginUser: Verifies if credentials match stored data
    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    // 6. returnLoginStatus: Returns greeting with name as per PoE requirements
    public String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}