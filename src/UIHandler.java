import Entities.Task;
import Enums.StatusTask;
import User.Manager;

import java.util.Scanner;

public class UIHandler {
    public void ShowMenu(Scanner scanner) {
        Manager manager = new Manager(scanner);
        while (true) {
            printMenuTask();
            switch (checkInUser(scanner)) {
                case 1:
                    printMenuSTack();
                    workToSwitch(scanner, manager);
                    break;
                case 2:
                    printMenuSTack();
                    break;
                case 3:
                    printMenuTaskEpic();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный пункт в меню, введите число от 1 до 3.");
            }
        }
    }


    private void workToSwitch(Scanner scanner, Manager manager) {
        switch (checkInUser(scanner)) {
            case 1:
                manager.print("Task");
                break;
            case 2:
                manager.remove("Task");
                break;
            case 3:
                System.out.println("Введите id таска: ");
                manager.getTaskById(checkInUser(scanner));
                break;
            case 4:
                createTask(scanner, manager);
                System.out.println("Задача успешно создана");
                break;
            case 5:
                System.out.print("Укажите id задачи: ");
                int id = checkInUser(scanner);
                Task task = createTask(scanner, manager);
                System.out.println("Укажите статус задачи: ");
                System.out.println("1. IN_PROGRESS");
                System.out.println("2. DONE");
                switch (checkInUser(scanner)){
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
                manager.updateTask(id,task);
                break;
            case 6:
                System.out.println("Укажите ID таска: ");
                manager.removeItemToId(checkInUser(scanner));
                break;
            case 7:
                return;
            default:
                System.out.println("Выход за пределы меню.");
                break;

        }
    }
    private Task createTask(Scanner scanner, Manager manager) {
        System.out.print("Укажите название задачи: ");
        String name = scanner.nextLine();
        System.out.print("Укажите описание задачи: ");
        String description = scanner.nextLine();
        return manager.createTask(name, description);
    }
    private void printMenuTask(){
        System.out.println("Выберетире тип задачи: ");
        System.out.println("1. Задача");
        System.out.println("2. Подзадачи");
        System.out.println("3. Эпик");
        System.out.println("4. Выход");
        System.out.print("Введите число: ");
    }
    private void printMenuSTack(){
        System.out.println("Выберети тип меню: ");
        System.out.println("1. Получение списка всех задач");
        System.out.println("2. Удаление всех задач");
        System.out.println("3. Получение по индификатору");
        System.out.println("4. Создание задачи");
        System.out.println("5. Обновление задачи");
        System.out.println("6. Удаление по индификатору");
        System.out.println("7. Выход");
        System.out.print("Ввеедите число: ");
    }
    private void printMenuTaskEpic(){
        System.out.println("Выберети тип меню: ");
        System.out.println("1. Получение списка всех задач ");
        System.out.println("2. Удаление всех задач");
        System.out.println("3. Получение по индификатору");
        System.out.println("4. Создание задачи");
        System.out.println("5. Обновление задачи");
        System.out.println("6. Удаление по индификатору");
        System.out.println("7. Получение списка всех подзачад эпика");
        System.out.println("8. Выход");
        System.out.print("Ввеедите число: ");
    }

    private int checkInUser(Scanner scanner){
        int point;
        if (scanner.hasNextInt()) {
            point = scanner.nextInt();
            scanner.nextLine();
            return point;
        } else {
            System.out.println("Извините, но это явно не число. Пожалуйста укажите пункт из меню.");
            scanner.nextLine();
            System.out.print("Введите число: ");
            return checkInUser(scanner);
        }
    }
}
