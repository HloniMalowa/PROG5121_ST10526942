package prog5121_poe_project;

public class Login {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String cellPhoneNumber;

    public boolean checkUserName(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        return password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?\\\\|`~].*");
    }

    public boolean checkCellPhoneNumber(String cellNo) {
        return cellNo != null && cellNo.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password,
                               String firstName, String lastName,
                               String cellPhoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; ensure it contains an underscore and is no more than five characters.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; ensure at least 8 characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or missing international code.";
        }

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username != null
                && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}