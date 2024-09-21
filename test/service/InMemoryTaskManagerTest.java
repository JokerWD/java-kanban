package test;

import entities.Epic;
import entities.Subtask;
import entities.Task;
import enums.StatusTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void testAddAndFindTaskById() {
        Task task = new Task("Такс 1", "Описание", StatusTask.NEW);
        taskManager.addNewTask(task);

        Task foundTask = taskManager.getTaskById(1);
        assertNotNull(foundTask);
        assertEquals(task, foundTask);
    }

    @Test
    public void testAddAndFindSubtaskById() {
        Epic epic = new Epic( "Эпик 1", "Описание", StatusTask.NEW);
        taskManager.addNewEpic(epic);

        Subtask subtask = new Subtask( "Подзадача 1", "Описание", StatusTask.NEW, 1);
        taskManager.addNewSubtask(subtask);

        Subtask foundSubtask = taskManager.getSubtaskById(2);
        assertNotNull(foundSubtask);
        assertEquals(subtask, foundSubtask);
    }

    @Test
    public void testAddAndFindEpicById() {
        Epic epic = new Epic( "Эпик 1", "Описание", StatusTask.NEW);
        taskManager.addNewEpic(epic);

        Epic foundEpic = taskManager.getEpicById(1);
        assertNotNull(foundEpic);
        assertEquals(epic, foundEpic);
    }

}