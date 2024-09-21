package test;

import entities.Task;
import enums.StatusTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task("Таск 1", "Описание", StatusTask.NEW);

        assertEquals("Таск 1", task.getName(), "Имя задачи должно совпадать");
        assertEquals("Описание", task.getDescription(), "Описание задачи должно совпадать");
        assertEquals(StatusTask.NEW, task.getStatusTask(), "Статус задачи должен совпадать");
    }

    @Test
    void testSetters() {
        Task task = new Task("Таск 1", "Описание", StatusTask.NEW);
        task.setName("Таск 1 конец");
        task.setDescription("Описание 1");
        task.setStatusTask(StatusTask.DONE);

        assertEquals("Таск 1 конец", task.getName(), "Имя задачи должно быть обновлено");
        assertEquals("Описание 1", task.getDescription(), "Описание задачи должно быть обновлено");
        assertEquals(StatusTask.DONE, task.getStatusTask(), "Статус задачи должен быть обновлен");
    }

    @Test
    void testEqualsAndHashCode() {
        Task task1 = new Task("Таск 1", "Описание", StatusTask.NEW);
        task1.setId(1);

        Task task2 = new Task("Таск 2", "Описание", StatusTask.NEW);
        task2.setId(1);

        Task task3 = new Task("Таск 3", "Описание", StatusTask.NEW);
        task3.setId(2);

        assertEquals(task1, task2, "Задачи с одинаковым ID должны быть равны");
        assertNotEquals(task1, task3, "Задачи с разными ID не должны быть равны");

        assertEquals(task1.hashCode(), task2.hashCode(), "Задачи с одинаковым ID должны иметь одинаковый hashCode");
        assertNotEquals(task1.hashCode(), task3.hashCode(), "Задачи с разными ID должны иметь разные hashCode");
    }
    @Test
    public void testTasksEquality() {
        Task task1 = new Task("Таск 1", "Описание", StatusTask.NEW);
        Task task2 = new Task("Tаск 2","Описание", StatusTask.DONE);

        assertEquals(task1, task2);
    }

}