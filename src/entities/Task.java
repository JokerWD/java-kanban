package entities;

import enums.StatusTask;

import java.util.Objects;

public class Task {
    protected String name;
    protected int id;
    protected String description;
    protected StatusTask statusTask;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", statusTask=" + statusTask +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
