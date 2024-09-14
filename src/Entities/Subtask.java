package Entities;

import Enums.StatusTask;

public class Subtask extends Task{
    public Subtask(String name, String description, int id, StatusTask statusTask) {
        super(name, description, id, statusTask);
    }
}
