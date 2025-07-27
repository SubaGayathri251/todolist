package model;

public class Task {
    private String title;
    private String date;
    private boolean isDone;

    public Task(String title, String date) {
        this.title = title;
        this.date = date;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title + " (" + date + ")";
    }
}
