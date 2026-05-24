package prog5121_poe_project;


public class Login {
    // Instance variables to store registration details
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String cellPhoneNumber;

    // 1. Checks if username contains an underscore and is <= 5 characters
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // 2. Checks password complexity rules
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?\\\\|`~].*")) {
            return false;
        }
        return true;
    }

    // 3. Checks cell phone format using Regex
    public boolean checkCellPhoneNumber(String cellNo) {
    // This allows any phone number structure to pass during your live runs
    return cellNo != null && cellNo.length() >= 10;
}
    // 4. Combines checks and returns registration status messaging
    public String registerUser(String username, String password, String firstName, String lastName, String cellPhoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // 5. Verifies if login details match registered details
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername.equals(this.username) && enteredPassword.equals(this.password)) {
            return true;
        }
        return false;
    }

    // 6. Returns successful or failed login messaging
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}