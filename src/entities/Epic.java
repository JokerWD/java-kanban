package entities;

import enums.StatusTask;
import service.TaskStorage;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Epic extends Task {
    private final Set<Integer> subtaskIds;

    public Epic(String name, String description, StatusTask statusTask) {
        super(name, description, statusTask);
        this.subtaskIds = new HashSet<>();
    }


    public Set<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtask(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    public void removeSubtask(int subtaskId) {
        subtaskIds.remove(subtaskId);
    }

    public void updateStatus(TaskStorage taskStorage) {
        if (subtaskIds.isEmpty()) {
            setStatusTask(StatusTask.NEW);
            return;
        }

        boolean anyInProgress = false;
        boolean allDone = true;

        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = taskStorage.getSubtask(subtaskId);
            if (subtask != null) {
                StatusTask status = subtask.getStatusTask();
                if (status == StatusTask.IN_PROGRESS) {
                    anyInProgress = true;
                } else if (status != StatusTask.DONE) {
                    allDone = false;
                }
            }
        }

        if (anyInProgress) {
            setStatusTask(StatusTask.IN_PROGRESS);
        } else if (allDone) {
            setStatusTask(StatusTask.DONE);
        } else {
            setStatusTask(StatusTask.NEW);
        }
    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", statusTask=" + getStatusTask() +
                ", subtaskIds=" + subtaskIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epic)) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIds, epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIds);
    }
}
