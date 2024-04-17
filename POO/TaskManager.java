// Todo app
// Create a new task by addin: id, date, task name, 
// task description, task state, priority
// Delete a task by id
// Mark a task as completed (true) or incompleted (false)
// Order the task by date asc or desc, and by priority
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Task {
    private int id;
    private String date;
    private String taskName;
    private String taskDescription;
    private boolean taskState;
    private int priority;

    public Task(int id, String date, String taskName, String taskDescription, boolean taskState, int priority) {
        this.id = id;
        this.date = date;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskState = taskState;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Task Name: " + taskName + ", Description: " + taskDescription +
                ", State: " + taskState + ", Priority: " + priority;
    }
}

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- TODO LIST ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Borrar tarea por ID");
            System.out.println("3. Marcar tarea como completada/incompleta");
            System.out.println("4. Ordenar tareas");
            System.out.println("5. Mostrar tareas");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    deleteTask(scanner);
                    break;
                case 3:
                    markTask(scanner);
                    break;
                case 4:
                    sortTasks(scanner);
                    break;
                case 5:
                    showTasks();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Ingrese la fecha (dd/mm/yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Ingrese el nombre de la tarea: ");
        String taskName = scanner.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String taskDescription = scanner.nextLine();
        System.out.print("Ingrese la prioridad (1-3): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Task task = new Task(taskIdCounter++, date, taskName, taskDescription, false, priority);
        tasks.add(task);
        System.out.println("Tarea agregada correctamente.");
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Ingrese el ID de la tarea a borrar: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                tasks.remove(task);
                System.out.println("Tarea borrada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ninguna tarea con el ID proporcionado.");
    }

    private static void markTask(Scanner scanner) {
        System.out.print("Ingrese el ID de la tarea a marcar: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setTaskState(!task.isTaskState());
                System.out.println("Tarea marcada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ninguna tarea con el ID proporcionado.");
    }

    private static void sortTasks(Scanner scanner) {
        System.out.println("¿Cómo desea ordenar las tareas?");
        System.out.println("1. Por fecha ascendente");
        System.out.println("2. Por fecha descendente");
        System.out.println("3. Por prioridad ascendente");
        System.out.println("4. Por prioridad descendente");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

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
                System.out.println("Opción no válida.");
                return;
        }
        System.out.println("Tareas ordenadas correctamente.");
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
            return;
        }
        System.out.println("--- TAREAS ---");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
