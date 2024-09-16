import Entities.Task;
import Enums.StatusTask;
import User.Manager;

import java.util.Scanner;

public class UIHandler {
    public void showMenu(Scanner scanner) {
        Manager manager = new Manager(scanner);
        while (true) {
            printMenuCategories();
            int point = checkInUser(scanner);
            if (point == 4) return;
            String category = getCategory(point);
            if (category == null) {
                System.out.println("Неверный пункт в меню, введите число от 1 до 4.");
                continue;
            }
            printMenuTask();
            handleTaskAction(scanner, manager, category);
        }
    }

    private void handleTaskAction(Scanner scanner, Manager manager, String category) {
        switch (checkInUser(scanner)) {
            case 1:
                manager.print(category);
                break;
            case 2:
                manager.remove(category);
                break;
            case 3:
                System.out.print("Введите id таска: ");
                System.out.println(manager.getTaskById(checkInUser(scanner)));
                break;
            case 4:
                manager.create(category);
                System.out.println("Задача успешно создана");
                break;
            case 5:
                updateTask(scanner, manager);
                break;
            case 6:
                System.out.print("Укажите ID таска: ");
                manager.removeItemById(checkInUser(scanner));
                break;
            case 7:
                System.out.print("Введите ID эпика: ");
                int epicId = checkInUser(scanner);
                manager.printSubtasksForEpic(epicId);
                break;
            case 8:
                return;
            default:
                System.out.println("Неверный пункт в меню, введите число от 1 до 7.");
        }
    }

    private void updateTask(Scanner scanner, Manager manager) {
        System.out.print("Укажите id задачи: ");
        int id = checkInUser(scanner);

        System.out.print("Укажите название задачи: ");
        String name = scanner.nextLine();
        System.out.print("Укажите описание задачи: ");
        String description = scanner.nextLine();
        Task task = new Task(name, description);

        System.out.println("Укажите статус задачи: ");
        System.out.println("1. IN_PROGRESS");
        System.out.println("2. DONE");
        switch (checkInUser(scanner)) {
            case 1:
                task.setStatusTask(StatusTask.IN_PROGRESS);
                break;
            case 2:
                task.setStatusTask(StatusTask.DONE);
                break;
            default:
                System.out.println("Ошибка выход за пределы.");
                return;
        }
        manager.updateTask(id, task);
    }

    private void printMenuCategories() {
        System.out.println("Выберите тип задачи: ");
        System.out.println("1. Задача");
        System.out.println("2. Подзадачи");
        System.out.println("3. Эпик");
        System.out.println("4. Выход");
        System.out.print("Введите число: ");
    }

    private void printMenuTask() {
        System.out.println("Выберите тип меню: ");
        System.out.println("1. Получение списка всех задач");
        System.out.println("2. Удаление всех задач");
        System.out.println("3. Получение по индификатору");
        System.out.println("4. Создание задачи");
        System.out.println("5. Обновление задачи");
        System.out.println("6. Удаление по индификатору");
        System.out.println("7. Получение подзадач для эпика");
        System.out.println("8. Выход");
        System.out.print("Введите число: ");
    }

    private int checkInUser(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int point = scanner.nextInt();
            scanner.nextLine();
            return point;
        } else {
            System.out.println("Извините, но это явно не число. Пожалуйста укажите пункт из меню.");
            scanner.nextLine();
            System.out.print("Введите число: ");
            return checkInUser(scanner);
        }
    }

    private String getCategory(int point) {
        return switch (point) {
            case 1 -> "Task";
            case 2 -> "Subtask";
            case 3 -> "Epic";
            default -> null;
        };
    }
}
