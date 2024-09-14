package User;

import Entities.Epic;
import Entities.Subtask;
import Entities.Task;

import java.util.HashMap;

public class Manager {
    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();
}
