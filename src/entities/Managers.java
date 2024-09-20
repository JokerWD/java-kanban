package entities;

import Interfaces.HistoryManager;
import service.InMemoryHistoryManager;
import service.InMemoryTaskManager;
import Interfaces.TaskManager;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }


}
