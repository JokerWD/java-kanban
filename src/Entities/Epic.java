package Entities;

import Enums.StatusTask;

import java.util.HashSet;
import java.util.Set;

public class Epic extends Task {
    private final Set<Integer> subtaskIds = new HashSet<>();

    public Epic(String name, String description, StatusTask statusTask) {
        super(name, description, statusTask);
    }

    public void addSubtask(int subtaskId) {
        subtaskIds.add(subtaskId);
        updateStatus();
    }

    public void removeSubtask(int subtaskId) {
        subtaskIds.remove(subtaskId);
        updateStatus();
    }

    public Set<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void updateStatus() {
        if (subtaskIds.isEmpty()) {
            setStatusTask(StatusTask.NEW);
            return;
        }

        boolean anyInProgress = false;
        boolean allDone = false;

        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskStorage.getTaskById(subtaskId);
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
}
