package entities;

import enums.StatusTask;

import java.util.Objects;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, StatusTask statusTask, int epicId) {
        super(name, description, statusTask);
        this.epicId = epicId;
    }


    public int getEpicId() {
        return epicId;
    }



    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", statusTask=" + getStatusTask() +
                ", epicId=" + epicId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subtask)) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }
}
