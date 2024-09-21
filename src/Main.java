import entities.Epic;
import service.Managers;
import entities.Subtask;
import entities.Task;
import enums.StatusTask;
import interfaces.TaskManager;


public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();


        Task task1 = new Task("Задача 1", "Описание", StatusTask.NEW);
        Task task2 = new Task("Задача 2", "Описание", StatusTask.NEW);
        manager.addNewTask(task1);
        manager.addNewTask(task2);

        Epic epic1 = new Epic("Эпик 1", "Описание", StatusTask.NEW);
        manager.addNewEpic(epic1);

        Subtask subtask1 = new Subtask("Под задача 1", "Описание", StatusTask.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Под задача 2", "Описание", StatusTask.IN_PROGRESS, epic1.getId());
        manager.addNewSubtask(subtask1);
        manager.addNewSubtask(subtask2);

        manager.getTask(task1.getId());
        printAllTasks(manager);

        manager.getEpic(epic1.getId());
        printAllTasks(manager);

        manager.getSubtask(subtask1.getId());
        printAllTasks(manager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);

            for (Subtask subtask : manager.getEpicSubtasks(epic.getId())) {
                System.out.println("--> " + subtask);
            }
        }
        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
        System.out.println();
    }
}
