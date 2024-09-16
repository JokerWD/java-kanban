package Entities;

import Enums.StatusTask;

public class Subtask extends Task{
    public Subtask(String name, String description, StatusTask statusTask, int epicId) {
        super(name, description, statusTask);

    }
}
