/**
 * Class: Object oriented programing
 * Poroject: Tasklist
 * Student: Joel Albert Araiza López
 * Languaje: Java
 */

// Todo app:
// Create a new task by addin: id, date, task name, 
// task description, task state, priority
// Delete a task by id
// Mark a task as completed (true) or incompleted (false)
// Order the task by date asc or desc, and by priority
// Libraries:
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Class task to manage the tasks
class Task {
    private int id; 
    private String date;
    private String taskName;
    private String taskDescription;
    private boolean taskState;
    private int priority;

    // Constructor to initialize the task
    public Task(int id, String date, String taskName, String taskDescription, boolean taskState, int priority) {
        this.id = id;
        this.date = date;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskState = taskState;
        this.priority = priority;
    }

    // Getter for the attribute id
    public int getId() {
        return id;
    }

    // Getter for the attribute date
    public String getDate() {
        return date;
    }

    // Getter for the attribute taskState
    public boolean isTaskState() {
        return taskState;
    }

    // Getter for the attribute priority
    public int getPriority() {
        return priority;
    }
    
    // Method to provide a string representation of the task
    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Task Name: " + taskName + ", Description: " + taskDescription +
                ", State: " + taskState + ", Priority: " + priority;
    }

    // Initialize the state of the task
    public void setTaskState(boolean taskState) {
        this.taskState = taskState;
    }
}

// Principal class
public class TaskManager {
    // Array to store the tasks
    private static ArrayList<Task> tasks = new ArrayList<>();
    // Initialize the counter for the id
    private static int taskIdCounter = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu for the user
        while (true) {
            System.out.println("\n--- Tasks Manager ---");
            System.out.println("1. Add task");
            System.out.println("2. Delete task by ID");
            System.out.println("3. Mark task as complete/incomplete");
            System.out.println("4. Order tasks");
            System.out.println("5. Show tasks");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            // Get the choice of the user from the CLI
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Check the choice and proced with the respective process
            switch (choice) {
                case 1:
                    addTask(scanner); // Add a task
                    break;
                case 2:
                    deleteTask(scanner); // Delete a task
                    break;
                case 3:
                    markTask(scanner); // Marks as complete or incomplete a task
                    break;
                case 4:
                    sortTasks(scanner); // Order the tasks
                    break;
                case 5:
                    showTasks(); // Show the tasks
                    break;
                case 6:
                    System.out.println("¡Bye bye!"); // Exit program
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Function to add a task
    private static void addTask(Scanner scanner) {
        System.out.print("Enter the date (yyyy/mm/dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter the task name: ");
        String taskName = scanner.nextLine();
        System.out.print("Enter task description: ");
        String taskDescription = scanner.nextLine();
        System.out.print("Enter the priority (1-3): ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        Task task = new Task(taskIdCounter++, date, taskName, taskDescription, false, priority);
        tasks.add(task);
        System.out.println("Task correctly added.");
    }
    // Function to delete a task
    private static void deleteTask(Scanner scanner) {
        System.out.print("Enter the id of the task to delete: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                tasks.remove(task);
                System.out.println("Task to delte correctly.");
                return;
            }
        }
        System.out.println("No task found with the given ID.");
    }
    // Function to mark a task as complete or incomplete
    private static void markTask(Scanner scanner) {
        System.out.print("Enter the ID of the task to mark: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setTaskState(!task.isTaskState());
                System.out.println("Task marked correctly.");
                return;
            }
        }
        System.out.println("No task found with the given ID");
    }
    // Order the task by the user choice
    private static void sortTasks(Scanner scanner) {
        System.out.println("How do you want to organize tasks?");
        System.out.println("1. By ascending date");
        System.out.println("2. By descending date");
        System.out.println("3. By ascending priority");
        System.out.println("4. By descending priority");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Collections.sort(tasks, Comparator.comparing(Task::getDate));
                break;
            case 2:
                Collections.sort(tasks, Comparator.comparing(Task::getDate).reversed());
                break;
            case 3:
                Collections.sort(tasks, Comparator.comparingInt(Task::getPriority));
                break;
            case 4:
                Collections.sort(tasks, Comparator.comparingInt(Task::getPriority).reversed());
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        System.out.println("Tasks ordered correctly.");
    }
    // Function to show the tasks
    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks to show.");
            return;
        }
        System.out.println("--- TASKS ---");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
