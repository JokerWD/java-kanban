package Entities;

import Enums.StatusTask;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private StatusTask statusTask;

    public Task(String name, String description, StatusTask statusTask) {
        this.name = name;
        this.description = description;
        this.statusTask = statusTask;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public StatusTask getStatusTask() {
        return statusTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", statusTask=" + statusTask +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(description, task.description) && statusTask == task.statusTask;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, statusTask);
    }
}
