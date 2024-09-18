import entities.Epic;
import entities.Subtask;
import entities.Task;
import enums.StatusTask;
import service.TaskStorage;

public class Main {
    public static void main(String[] args) {
        TaskStorage storage = new TaskStorage();

        Task task1 = new Task("Таск 1", "Описание", StatusTask.NEW);
        Task task2 = new Task("Таск 2", "Описание", StatusTask.NEW);

        storage.addNewTask(task1);
        storage.addNewTask(task2);

        Epic epic1 = new Epic("Эпик 1", "Описание", StatusTask.NEW);
        Epic epic2 = new Epic("Эпик 2", "Описание", StatusTask.NEW);

        storage.addNewEpic(epic1);
        storage.addNewEpic(epic2);

        Subtask subtask1 = new Subtask("Дополнительный таск 1", "Описание", StatusTask.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Дополнительный таск 2", "Описание", StatusTask.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("Дополнительный таск 3", "Описание", StatusTask.NEW, epic2.getId());

        storage.addNewSubtask(subtask1);
        storage.addNewSubtask(subtask2);
        storage.addNewSubtask(subtask3);

        printTasks(storage);

        task1.setStatusTask(StatusTask.IN_PROGRESS);
        storage.updateTask(task1);

        subtask1.setStatusTask(StatusTask.DONE);
        storage.updateSubtask(subtask1);

        subtask2.setStatusTask(StatusTask.IN_PROGRESS);
        storage.updateSubtask(subtask2);
        
        System.out.println("\nОбновление статусов у тасков:");
        System.out.println("Таск 1: " + storage.getTask(task1.getId()));
        System.out.println("Дополнительный таск 1: " + storage.getSubtask(subtask1.getId()));
        System.out.println("Дополнительный таск 2: " + storage.getSubtask(subtask2.getId()));
        System.out.println("Эпик 1: " + storage.getEpic(epic1.getId()));

        storage.deleteTask(task2.getId());
        storage.deleteEpic(epic2.getId());

        System.out.println("\nСитуация с тасками после удаления:");
        printTasks(storage);
    }

    private static void printTasks(TaskStorage storage) {
        System.out.println("Список тасков:");
        for (Task task : storage.getTasks()) {
            System.out.println(task);
        }

        System.out.println("\nСписок эпиков:");
        for (Epic epic : storage.getEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nСписок дополнительных тасков:");
        for (Subtask subtask : storage.getSubtasks()) {
            System.out.println(subtask);
        }
    }
}
