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
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TaskManager {
    private static Connection conn;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        connectToDatabase();
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

    private static void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("tasks.db");
            System.out.println("Connected to SQLite database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addTask() {
        connectToDatabase();
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

        storeTaskInDatabase(taskMap);

        System.out.println("Task added successfully!!");
        scanner.close();
    }

    private static void storeTaskInDatabase(Map<String, Object> taskMap) {
        try {
            String sql = "INSERT INTO tasks (id, name, date, details, completed, priority) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (int) taskMap.get("id"));
            pstmt.setString(2, (String) taskMap.get("name"));
            pstmt.setString(3, taskMap.get("date").toString());
            pstmt.setString(4, (String) taskMap.get("details"));
            pstmt.setBoolean(5, (boolean) taskMap.get("completed"));
            pstmt.setString(6, (String) taskMap.get("priority"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

    private static void deleteTask() {
        Scanner scanner = new Scanner(System.in);

        try {
            connectToDatabase();

            String sql = "SELECT id, name FROM tasks";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Tasks:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

            System.out.println("Enter the ID of the task to delete:");
            int taskIdToDelete = scanner.nextInt();

            sql = "DELETE FROM tasks WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, taskIdToDelete);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task with ID " + taskIdToDelete + " has been deleted.");
            } else {
                System.out.println("Task with ID " + taskIdToDelete + " not found.");
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

    private static void markTaskCompleted() {
        Scanner scanner = new Scanner(System.in);

        try {
            connectToDatabase();

            String sql = "SELECT id, name, completed FROM tasks";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Tasks:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Completed: "
                        + rs.getBoolean("completed"));
            }

            System.out.println("Enter the ID of the task to mark as completed:");
            int taskIdToComplete = scanner.nextInt();

            sql = "UPDATE tasks SET completed = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, taskIdToComplete);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task with ID " + taskIdToComplete + " has been marked as completed.");
            } else {
                System.out.println("Task with ID " + taskIdToComplete + " not found.");
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

    private static void seeTasks() {
        Scanner scanner = new Scanner(System.in);

        try {
            connectToDatabase();

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

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

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
