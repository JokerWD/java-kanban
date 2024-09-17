package entities;

import enums.StatusTask;

import java.util.Objects;

public class Task {
    private final String name;
    private int id;
    private final String description;
    private StatusTask statusTask;

    public Task(String name, String description, StatusTask statusTask) {
        this.name = name;
        this.description = description;
        this.statusTask = statusTask;
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
