package Entities;

import Enums.StatusTask;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, StatusTask statusTask) {
        super(name, description,  statusTask);
    }
    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int id) {
        subtaskIds.add(id);
    }
}
