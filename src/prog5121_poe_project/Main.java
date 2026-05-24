package prog5121_poe_project;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("--- USER REGISTRATION ---");
        
        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();
        
        System.out.print("Enter Username: ");
        String username = input.nextLine();
        
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        
        System.out.print("Enter Cell Phone Number (e.g., +27123456789): ");
        String cellNo = input.nextLine();

        System.out.println("\n--- REGISTRATION STATUS ---");
        String registrationReport = loginSystem.registerUser(username, password, firstName, lastName, cellNo);
        System.out.println(registrationReport);

        // Only proceed if registration was fully successful
        if (registrationReport.contains("successfully captured")) {
            System.out.println("\n--- USER LOGIN ---");
            
            System.out.print("Enter Username to Login: ");
            String loginUser = input.nextLine();
            
            System.out.print("Enter Password to Login: ");
            String loginPass = input.nextLine();

            boolean isSuccess = loginSystem.loginUser(loginUser, loginPass);
            
            System.out.println("\n--- LOGIN STATUS ---");
            String loginReport = loginSystem.returnLoginStatus(isSuccess);
            System.out.println(loginReport);
            
            // IF LOGIN IS SUCCESSFUL -> ENTER APPLICATION LOOP
            if (isSuccess) {
                JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
                
                boolean exitApp = false;
                
                while (!exitApp) {
                    // Display Menu Options
                    String menuOptions = "Select an option:\n" +
                                         "1. Add task details\n" +
                                         "2. Show report (Coming Soon)\n" +
                                         "3. Quit";
                    
                    String choiceStr = JOptionPane.showInputDialog(menuOptions);
                    
                    if (choiceStr == null) {
                        break; 
                    }
                    
                    int choice = Integer.parseInt(choiceStr);
                    
                    switch (choice) {
                        case 1:
                            String numTasksStr = JOptionPane.showInputDialog("How many tasks would you like to enter?");
                            int numTasks = Integer.parseInt(numTasksStr);
                            
                            for (int i = 0; i < numTasks; i++) {
                                String taskName = JOptionPane.showInputDialog("Enter Task Name for task " + i + ":");
                                
                                String taskDesc = "";
                                boolean validDesc = false;
                                
                                while (!validDesc) {
                                    taskDesc = JOptionPane.showInputDialog("Enter Task Description (Max 50 chars):");
                                    Task dummy = new Task(taskName, i, taskDesc, "", 0, "");
                                    if (dummy.checkTaskDescription(taskDesc)) {
                                        JOptionPane.showMessageDialog(null, "Task successfully captured");
                                        validDesc = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                                    }
                                }
                                
                                String devDetails = JOptionPane.showInputDialog("Enter Developer First and Last Name:");
                                String durationStr = JOptionPane.showInputDialog("Enter Task Duration (in hours):");
                                int duration = Integer.parseInt(durationStr);
                                
                                String statusMenu = "Select Task Status:\n1. To Do\n2. Doing\n3. Done";
                                String statusChoice = JOptionPane.showInputDialog(statusMenu);
                                String status = "To Do";
                                if ("2".equals(statusChoice)) status = "Doing";
                                if ("3".equals(statusChoice)) status = "Done";
                                
                                Task newTask = new Task(taskName, i, taskDesc, devDetails, duration, status);
                                JOptionPane.showMessageDialog(null, newTask.printTaskDetails());
                            }
                            
                            JOptionPane.showMessageDialog(null, "Total hours combined across tasks: " + Task.returnTotalHours() + " hours");
                            break;
                            
                        case 2:
                            JOptionPane.showMessageDialog(null, "Coming Soon!");
                            break;
                            
                        case 3:
                            exitApp = true;
                            break;
                            
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                    }
                }
            }
        } else {
            System.out.println("\nRegistration failed. Please fix formatting issues and try again.");
        }
        
        input.close();
    }
}
//Final submission check