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


public class TaskManager {
    private static Scanner scanner = new Scanner(System.in);
    // Use a list or array to storage the info
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = 1;

        while (x == 1) {
            System.out.println("\nSelect option:");
            System.out.println("1) Add task");
            System.out.println("2) Delete a task");
            System.out.println("3) Mark task as completed");
            System.out.println("4) See tasks");
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
            
            System.out.println("\nAnother operation?");
            System.out.println("1) SÍ");
            System.out.println("2) NO");
            x = scanner.nextInt();
        }

        scanner.close();
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter task name:");
        String taskName = scanner.nextLine();
        System.out.println("Enter task details:");
        String taskDetails = scanner.nextLine();
        System.out.println("Enter task priority (H,I,L):");
        String taskPriority = scanner.nextLine();

        int taskId = (int) (Math.random() * 1000);

        Date creationDate = new Date();

        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("id", taskId);
        taskMap.put("name", taskName);
        taskMap.put("date", creationDate);
        taskMap.put("details", taskDetails);
        taskMap.put("completed", false);
        taskMap.put("priority", taskPriority);

        System.out.println("Task added successfully!!");
        scanner.close();
    }


    private static void deleteTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the task to delete:");
        int taskIdToDelete = scanner.nextInt();

            scanner.close();
        }
    }

    private static void markTaskCompleted() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the task to mark as completed:");
        int taskIdToComplete = scanner.nextInt();

    }

    private static void seeTasks() {
        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Select order:");
            System.out.println("1) Ascending date");
            System.out.println("2) Descending date");
            System.out.println("3) High priority first");
            System.out.println("4) Low priority first");
            int orderOption = scanner.nextInt();

            String sql = "";
            switch (orderOption) {
                case 1:
                    sql = "SELECT * FROM tasks ORDER BY date ASC";
                    break;
                case 2:
                    sql = "SELECT * FROM tasks ORDER BY date DESC";
                    break;
                case 3:
                    sql = "SELECT * FROM tasks ORDER BY CASE priority WHEN 'H' THEN 3 WHEN 'I' THEN 2 WHEN 'L' THEN 1 ELSE 0 END DESC";
                    break;
                case 4:
                    sql = "SELECT * FROM tasks ORDER BY CASE priority WHEN 'H' THEN 3 WHEN 'I' THEN 2 WHEN 'L' THEN 1 ELSE 0 END ASC";
                    break;
                default:
                    System.out.println("Invalid option");
                    return;
            }

            System.out.println("Tasks:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Date: "
                        + rs.getString("date") + ", Priority: " + rs.getString("priority"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
