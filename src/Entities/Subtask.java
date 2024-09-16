package Entities;

import Enums.StatusTask;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, String description, StatusTask statusTask, int epicId) {
        super(name, description, statusTask);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
    public void setStatusTask(StatusTask statusTask) {
        super.setStatusTask(statusTask);
        Epic epic = TaskStorage.getEpicHashMap().get(epicId);
        if (epic != null) {
            epic.updateStatus();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", epicId=" + epicId;
    }
}
