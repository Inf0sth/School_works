// Todo app
// Create a new task by addin: id, date, task name, 
// task description, task state, priority
// Delete a task by id
// Mark a task as completed (true) or incompleted (false)
// Order the task by date asc or desc, and by priority
import java.io.File;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import org.json.simple.JSONArray;
import java.text.SimpleDateFormat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TaskManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = 1;

        while (x == 1) {
            System.out.println("\nSelect option:");
            System.out.println("1) Add task");
            System.out.println("2) Delete a task");
            System.out.println("3) Mark task as completed");
            System.out.println("4) See tasks");
            int option = scanner.nextInt(); // Solicitar una opción al usuario para proceder con el programa
            
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
            // Decidir si el programa continúa ejecutándose o no, según la decisión del usuario
            System.out.println("\nAnother operation?");
            System.out.println("1) SÍ");
            System.out.println("2) NO");
            x = scanner.nextInt();
        }

        scanner.close();
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);

        // Verificar si el archivo JSON existe
        File file = new File("Tasks_todo.json");
        if (!file.exists()) {
            createEmptyJSONFile(); // Si no existe, crear un archivo JSON vacío
        }

        // Solicitar datos para la nueva tarea
        System.out.println("Enter task name:");
        String taskName = scanner.nextLine();
        System.out.println("Enter task details:");
        String taskDetails = scanner.nextLine();
        System.out.println("Enter task priority (H,I,L):");
        String taskPriority = scanner.nextLine();

        // Generar ID único para la tarea
        int taskId = (int) (Math.random() * 1000);

        // Obtener la fecha actual
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date creationDate = new Date();

        // Crear un mapa para almacenar la información de la tarea
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("id", taskId);
        taskMap.put("name", taskName);
        taskMap.put("date", creationDate);
        taskMap.put("details", taskDetails);
        taskMap.put("completed", false);
        taskMap.put("priority", taskPriority);

        storeTaskInJSON(taskMap);

        System.out.println("Task added successfully!!");
        scanner.close();
    }

    private static void createEmptyJSONFile() {
        try {
            FileWriter writer = new FileWriter("Tasks_todo.json");
            writer.write("[]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void storeTaskInJSON(Map<String, Object> taskMap) {
        try {
            // Leer el contenido actual del archivo JSON
            String jsonContent = FileReader.readFile("Tasks_todo.json");

            // Convertir el contenido JSON a un JSONArray
            JSONArray jsonArray;
            if (jsonContent.isEmpty()) {
                jsonArray = new JSONArray(); // Si el archivo está vacío, crear un nuevo JSONArray
            } else {
                jsonArray = (JSONArray) JSONArray.parse(jsonContent);
            }

            // Convertir el mapa de la tarea a un JSONObject
            JSONObject jsonTask = new JSONObject(taskMap);

            // Agregar el JSONObject al JSONArray
            jsonArray.add(jsonTask);

            // Escribir el JSONArray actualizado en el archivo JSON
            FileWriter writer = new FileWriter("Tasks_todo.json");
            writer.write(jsonArray.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteTask() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Leer el contenido actual del archivo JSON
            String jsonContent = FileReader.readFile("Tasks_todo.json");

            // Convertir el contenido JSON a un JSONArray
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonContent);

            // Mostrar las tareas al usuario
            System.out.println("Tasks:");
            for (Object obj : jsonArray) {
                JSONObject task = (JSONObject) obj;
                System.out.println("ID: " + task.get("id") + ", Name: " + task.get("name"));
            }

            // Solicitar al usuario el ID de la tarea a eliminar
            System.out.println("Enter the ID of the task to delete:");
            int taskIdToDelete = scanner.nextInt();

            // Eliminar la tarea con el ID especificado
            JSONArray updatedArray = new JSONArray();
            boolean taskFound = false;
            for (Object obj : jsonArray) {
                JSONObject task = (JSONObject) obj;
                int taskId = ((Long) task.get("id")).intValue();
                if (taskId == taskIdToDelete) {
                    taskFound = true;
                } else {
                    updatedArray.add(task);
                }
            }

            // Escribir el JSONArray actualizado en el archivo JSON
            if (taskFound) {
                FileWriter writer = new FileWriter("Tasks_todo.json");
                writer.write(updatedArray.toJSONString());
                writer.close();
                System.out.println("Task with ID " + taskIdToDelete + " has been deleted.");
            } else {
                System.out.println("Task with ID " + taskIdToDelete + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void markTaskCompleted() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Leer el contenido actual del archivo JSON
            String jsonContent = FileReader.readFile("Tasks_todo.json");

            // Convertir el contenido JSON a un JSONArray
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonContent);

            // Mostrar las tareas al usuario
            System.out.println("Tasks:");
            for (Object obj : jsonArray) {
                JSONObject task = (JSONObject) obj;
                System.out.println("ID: " + task.get("id") + ", Name: " + task.get("name") + ", Completed: "
                        + task.get("completed"));
            }

            // Solicitar al usuario el ID de la tarea a marcar como completada
            System.out.println("Enter the ID of the task to mark as completed:");
            int taskIdToComplete = scanner.nextInt();

            // Marcar la tarea con el ID especificado como completada
            boolean taskFound = false;
            for (Object obj : jsonArray) {
                JSONObject task = (JSONObject) obj;
                int taskId = ((Long) task.get("id")).intValue();
                if (taskId == taskIdToComplete) {
                    task.put("completed", true);
                    taskFound = true;
                    break;
                }
            }

            // Escribir el JSONArray actualizado en el archivo JSON
            if (taskFound) {
                FileWriter writer = new FileWriter("Tasks_todo.json");
                writer.write(jsonArray.toJSONString());
                writer.close();
                System.out.println("Task with ID " + taskIdToComplete + " has been marked as completed.");
            } else {
                System.out.println("Task with ID " + taskIdToComplete + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void seeTasks() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Leer el contenido actual del archivo JSON
            String jsonContent = FileReader.readFile("Tasks_todo.json");

            // Convertir el contenido JSON a un JSONArray
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonContent);

            // Mostrar menú de opciones al usuario
            System.out.println("Select order:");
            System.out.println("1) Ascending date");
            System.out.println("2) Descending date");
            System.out.println("3) High priority first");
            System.out.println("4) Low priority first");
            int orderOption = scanner.nextInt();

            // Ordenar las tareas según la opción seleccionada por el usuario
            switch (orderOption) {
                case 1:
                    Collections.sort(jsonArray, new Comparator<JSONObject>() {
                        @Override
                        public int compare(JSONObject task1, JSONObject task2) {
                            return ((String) task1.get("date")).compareTo((String) task2.get("date"));
                        }
                    });
                    break;
                case 2:
                    Collections.sort(jsonArray, new Comparator<JSONObject>() {
                        @Override
                        public int compare(JSONObject task1, JSONObject task2) {
                            return ((String) task2.get("date")).compareTo((String) task1.get("date"));
                        }
                    });
                    break;
                case 3:
                    Collections.sort(jsonArray, new Comparator<JSONObject>() {
                        @Override
                        public int compare(JSONObject task1, JSONObject task2) {
                            return getPriorityValue((String) task2.get("priority")) - getPriorityValue((String) task1.get("priority"));
                        }
                    });
                    break;
                case 4:
                    Collections.sort(jsonArray, new Comparator<JSONObject>() {
                        @Override
                        public int compare(JSONObject task1, JSONObject task2) {
                            return getPriorityValue((String) task1.get("priority")) - getPriorityValue((String) task2.get("priority"));
                        }
                    });
                    break;
                default:
                    System.out.println("Invalid option");
                    return;
            }

            // Mostrar las tareas ordenadas
            System.out.println("Tasks:");
            for (Object obj : jsonArray) {
                JSONObject task = (JSONObject) obj;
                System.out.println("ID: " + task.get("id") + ", Name: " + task.get("name") + ", Date: "
                        + task.get("date") + ", Priority: " + task.get("priority"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static int getPriorityValue(String priority) {
        switch (priority) {
            case "H":
                return 3;
            case "I":
                return 2;
            case "L":
                return 1;
            default:
                return 0;
        }
    }
}
