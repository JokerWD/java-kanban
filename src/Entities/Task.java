package Entities;

import Enums.StatusTask;

public class Task {
    private String name;
    private String description;
    private int id;
    private StatusTask statusTask;

    public Task(String name, String description, int id, StatusTask statusTask) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.statusTask = statusTask;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }
}
