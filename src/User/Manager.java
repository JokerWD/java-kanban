package User;

import Entities.Epic;
import Entities.Subtask;
import Entities.Task;
import Entities.TaskStorage;
import Enums.StatusTask;

import java.util.Scanner;

public class Manager extends TaskStorage {
    Scanner scanner;
    public Manager(Scanner scanner) {
        this.scanner = scanner;
    }

    public Task createTask(String name, String description) {
        Task task = new Task(name, description, StatusTask.NEW);
        add(task);
        return task;
    }

    public void createEpic(String name, String description) {
        Epic epic = new Epic(name, description, StatusTask.NEW);
        add(epic);
    }

    public void createSubtask(String name, String description, int epicId) {
        if (!getEpicHashMap().containsKey(epicId)) {
            System.out.println("Эпика по данному ID " + epicId + " не существует.");
            return;
        }
        Subtask subtask = new Subtask(name, description, StatusTask.NEW, epicId);
        add(subtask);
    }

    public void print(String categories) {
        switch (categories) {
            case "Epic":
                System.out.println(getEpicHashMap().toString());
                break;
            case "Subtask":
                System.out.println(getSubtaskHashMap().toString());
                break;
            case "Task":
                System.out.println(getTaskHashMap().toString());
                break;
            default:
                System.out.printf("таска в такой категории  %s не существует.", categories);
                break;
        }
    }


}
