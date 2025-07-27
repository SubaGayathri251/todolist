package controller;

import service.TaskService;
import model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TaskController {
    private final TaskService taskService = new TaskService();
    private final Scanner scanner = new Scanner(System.in);

    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.now().format(formatter);
    }

    public void start() {
        while (true) {
            System.out.println("\nTo-Do Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Toggle Task Complete");
            System.out.println("3. Delete Task");
            System.out.println("4. Show Tasks");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    String date = getCurrentDate();
                    try {
                        taskService.addTask(title, date);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "2" -> {
                    showTasks();
                    System.out.print("Enter task number to toggle: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    taskService.toggleTask(idx);
                }
                case "3" -> {
                    showTasks();
                    System.out.print("Enter task number to delete: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    taskService.deleteTask(idx);
                }
                case "4" -> showTasks();
                case "5" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void showTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
        } else {
            System.out.println("\nTasks:");
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i++ + ". " + task);
            }
            System.out.println("Completed: " + taskService.getCompletedCount() + "/" + tasks.size());
        }
    }
}
