package service;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String title, String date) {
        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }
        tasks.add(new Task(title, date));
    }

    public void toggleTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).toggleDone();
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int getCompletedCount() {
        return (int) tasks.stream().filter(Task::isDone).count();
    }
}
