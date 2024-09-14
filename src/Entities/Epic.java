package Entities;

import Enums.StatusTask;

public class Epic extends Task{
    public Epic(String name, String description, int id, StatusTask statusTask) {
        super(name, description, id, statusTask);
    }
}
