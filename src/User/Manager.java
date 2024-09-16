package User;

import Entities.Epic;
import Entities.Subtask;
import Entities.Task;
import Entities.TaskStorage;
import Enums.StatusTask;

import java.util.*;

public class Manager extends TaskStorage {
    private final Scanner scanner;

    public Manager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void create(String category) {
        System.out.print("Укажите название задачи: ");
        String name = scanner.nextLine();
        System.out.print("Укажите описание задачи: ");
        String description = scanner.nextLine();

        Task task;
        switch (category) {
            case "Task":
                task = new Task(name, description, StatusTask.NEW);
                add(task);
                break;
            case "Subtask":
                System.out.print("Введите ID эпика: ");
                int epicId = scanner.nextInt();
                scanner.nextLine();
                if (!getEpicHashMap().containsKey(epicId)) {
                    System.out.println("Эпика по данному ID " + epicId + " не существует.");
                    return;
                }
                task = new Subtask(name, description, StatusTask.NEW, epicId);
                add(task);
                break;
            case "Epic":
                task = new Epic(name, description, StatusTask.NEW);
                add(task);
                break;
            default:
                System.out.println("Неизвестная категория");
        }
    }
    public void printSubtasksForEpic(int epicId) {
        List<Subtask> subtasks = getSubtasksForEpic(epicId);
        if (subtasks.isEmpty()) {
            System.out.println("У эпика с ID " + epicId + " нет подзадач.");
        } else {
            System.out.println("Подзадачи для эпика с ID " + epicId + ":");
            for (Subtask subtask : subtasks) {
                System.out.println(subtask);
            }
        }
    }

    public void print(String categories) {
        switch (categories) {
            case "Epic":
                for (Epic epic : getEpicHashMap().values()) {
                    System.out.println(epic);
                    System.out.println("Подзадачи:");
                    for (int subtaskId : epic.getSubtaskIds()) {
                        Subtask subtask = getSubtaskHashMap().get(subtaskId);
                        if (subtask != null) {
                            System.out.println("  " + subtask);
                        }
                    }
                }
                break;
            case "Subtask":
                for (Subtask subtask : getSubtaskHashMap().values()) {
                    System.out.println(subtask);
                }
                break;
            case "Task":
                for (Task task : getTaskHashMap().values()) {
                    System.out.println(task);
                }
                break;
            default:
                System.out.printf("Задачи в категории %s не существует.%n", categories);
                break;
        }
    }
}
