package prog5121_poe_project;

public class Task {
    // Instance variables for Task attributes
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskId;
    private String taskStatus;

    // Static variable to keep track of the total hours across ALL tasks
    private static int totalHours = 0;

    // Constructor
    public Task(String taskName, int taskNumber, String taskDescription, 
                String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskId = createTaskID(); // Automatically generate ID on creation
        
        // Accumulate hours if description is valid
        if (checkTaskDescription(taskDescription)) {
            totalHours += taskDuration;
        }
    }

    // 1. Validates that the task description is less than or equal to 50 characters
    public boolean checkTaskDescription(String description) {
        return description != null && description.length() <= 50;
    }

    // 2. Automatically generates a unique Task ID
    public String createTaskID() {
        // Get first two letters of task name (uppercase)
        String namePart = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        
        // Get last three letters of the developer's name (uppercase)
        String devTrimmed = developerDetails.trim();
        String devPart = "";
        if (devTrimmed.length() >= 3) {
            devPart = devTrimmed.substring(devTrimmed.length() - 3).toUpperCase();
        } else {
            devPart = devTrimmed.toUpperCase();
        }
        
        // Format layout: NA:0:SON
        return namePart + ":" + taskNumber + ":" + devPart;
    }

    // 3. Formats and returns the full display details of a task
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskId + "\n" +
               "Duration: " + taskDuration + " hours";
    }

    // 4. Returns the accumulated total hours of all tasks combined
    public static int returnTotalHours() {
        return totalHours;
    }

    // Getters and Setters (Required for testing framework compatibility)
    public String getTaskId() { return taskId; }
    public int getTaskDuration() { return taskDuration; }
    public String getTaskStatus() { return taskStatus; }
}