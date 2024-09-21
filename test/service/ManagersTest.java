package test;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import org.junit.jupiter.api.Test;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;
import service.Managers;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    public void testGetDefaultTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Таск менеджер не должен быть null");
    }

    @Test
    public void testGetDefaultHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "Таск менеджер не должен быть null");
    }

}