// Todo app
// Create a new task by addin: id, date, task name, 
// task description, task state, priority
// Delete a task by id
// Mark a task as completed (true) or incompleted (false)
// Order the task by date asc or desc, and by priority
import java.util.Map;
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class TaskManager {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Map<String, Object>> tasks = new ArrayList<>();

    public static void main(String[] args) {
        int x = 1;
        while (x == 1) {
            System.out.println("\nSelect option:");
            System.out.println("1) Add task");
            System.out.println("2) Delete a task");
            System.out.println("3) Mark task as completed");
            System.out.println("4) See tasks");
            
            // Verificar si hay más entradas disponibles antes de intentar leer un entero
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        deleteTask();
                        break;
                    case 3:
                        markTaskCompleted();
                        break;
                    case 4:
                        seeTasks();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.next(); // Limpiar el buffer de entrada
            }
            
            System.out.println("\nAnother operation?");
            System.out.println("1) SÍ");
            System.out.println("2) NO");
            
            // Verificar si hay más entradas disponibles antes de intentar leer un entero
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.next(); // Limpiar el buffer de entrada
            }
        }
        scanner.close();
    }


    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task name:");
        String taskName = scanner.nextLine();
        System.out.println("Enter task details:");
        String taskDetails = scanner.nextLine();
        System.out.println("Enter task priority (H, I, L):");
        String taskPriority = scanner.nextLine();
        System.out.println("Enter the date (DD/MM/AAAA):");
        String creationDate = scanner.nextLine();
        int taskId = (int) (Math.random() * 1000);
        
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("id", taskId);
        taskMap.put("name", taskName);
        taskMap.put("date", creationDate);
        taskMap.put("details", taskDetails);
        taskMap.put("completed", false);
        taskMap.put("priority", taskPriority);
        tasks.add(taskMap);
        System.out.println("Task added successfully!!");
        scanner.close();
    }

    private static void deleteTask() {
        System.out.println("Enter the ID of the task to delete:");
        int taskIdToDelete = scanner.nextInt();
        for (Map<String, Object> task : tasks) {
            if ((int) task.get("id") == taskIdToDelete) {
                tasks.remove(task);
                System.out.println("Task deleted successfully!!");
                return;
            }
        }
        System.out.println("Task not found!!");
    }

    private static void markTaskCompleted() {
        System.out.println("Enter the ID of the task to mark as completed:");
        int taskIdToComplete = scanner.nextInt();
        for (Map<String, Object> task : tasks) {
            if ((int) task.get("id") == taskIdToComplete) {
                task.put("completed", true);
                System.out.println("Task marked as completed!!");
                return;
            }
        }
        System.out.println("Task not found!!");
    }

    private static void seeTasks() {
        System.out.println("Select order:");
        System.out.println("1) Ascending date");
        System.out.println("2) Descending date");
        System.out.println("3) High priority first");
        System.out.println("4) Low priority first");
        int orderOption = scanner.nextInt();
        ArrayList<Map<String, Object>> sortedTasks = new ArrayList<>(tasks);
        switch (orderOption) {
            case 1:
                Collections.sort(sortedTasks, Comparator.comparing(task -> (Date) task.get("date")));
                break;
            case 2:
                Collections.sort(sortedTasks, (task1, task2) -> ((Date) task2.get("date")).compareTo((Date) task1.get("date")));
                break;
            case 3:
                Collections.sort(sortedTasks, (task1, task2) -> task2.get("priority").toString().compareTo(task1.get("priority").toString()));
                break;
            case 4:
                Collections.sort(sortedTasks, Comparator.comparing(task -> task.get("priority").toString()));
                break;
            default:
                System.out.println("Invalid option");
                return;
        }
        System.out.println("Tasks:");
        for (Map<String, Object> task : sortedTasks) {
            System.out.println("ID: " + task.get("id") + ", Name: " + task.get("name") + ", Date: " + task.get("date") + ", Priority: " + task.get("priority") + ", Completed: " + task.get("completed"));
        }
    }
}
