package Entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TaskStorage {
    private final HashMap<Integer, Task> taskHashMap;
    private final HashMap<Integer, Epic> epicHashMap;
    private final HashMap<Integer, Subtask> subtaskHashMap;
    private static int ID = 0;
    private final Set<Integer> availableIds;



    public TaskStorage() {
        taskHashMap = new HashMap<>();
        epicHashMap = new HashMap<>();
        subtaskHashMap = new HashMap<>();
        availableIds = new HashSet<>();

    }

    public <T extends Task> void add(T task) {
        int id = getNextId();
        if (task instanceof Epic) {
            epicHashMap.put(id, (Epic) task);
        } else if (task instanceof Subtask) {
            subtaskHashMap.put(id, (Subtask) task);
        } else {
            taskHashMap.put(id, task);
        }
    }
    public void removeItemToId(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            if (task instanceof Epic) {
                Epic epic = (Epic) task;
                for (int subtaskId : epic.getSubtaskIds()) {
                    subtaskHashMap.remove(subtaskId);
                    availableIds.add(subtaskId);
                }
                epicHashMap.remove(id);
            } else if (task instanceof Subtask) {
                subtaskHashMap.remove(id);
            } else {
                taskHashMap.remove(id);
            }
            availableIds.add(id);
        }
    }

    public Task getTaskById(int id) {
        if(isIdPresent(id)){
            if (taskHashMap.containsKey(id)) {
                return taskHashMap.get(id);
            } else if (epicHashMap.containsKey(id)) {
                return epicHashMap.get(id);
            } else if (subtaskHashMap.containsKey(id)) {
                return subtaskHashMap.get(id);
            }
        }
        return null;
    }
    public void updateTask(int id, Task updatedTask) {
        if (getTaskById(id) != null) {
            if (updatedTask instanceof Epic) {
                epicHashMap.put(id, (Epic) updatedTask);
            } else if (updatedTask instanceof Subtask) {
                subtaskHashMap.put(id, (Subtask) updatedTask);
            } else {
                taskHashMap.put(id, updatedTask);
            }
        } else {
            System.out.println("таск по данному ID " + id + "не обнаружен.");
        }
    }
    public void remove(String category) { // исправить
        switch (category) {
            case "Epic":
                epicHashMap.clear();
                break;
            case "Subtask":
                subtaskHashMap.clear();
                break;
            case "Task":
                taskHashMap.clear();
                break;
            default:
                System.out.printf("Задачи в категории %s не существует.%n", category);
                return;
        }

        recalculateIds();
    }



    public HashMap<Integer, Task> getTaskHashMap() {
        return taskHashMap;
    }

    public HashMap<Integer, Epic> getEpicHashMap() {
        return epicHashMap;
    }

    public HashMap<Integer, Subtask> getSubtaskHashMap() {
        return subtaskHashMap;
    }

    private void recalculateIds() {
        Set<Integer> usedIds = new HashSet<>();
        usedIds.addAll(taskHashMap.keySet());
        usedIds.addAll(epicHashMap.keySet());
        usedIds.addAll(subtaskHashMap.keySet());

        availableIds.clear();
        for (int i = 1; i <= ID; i++) {
            if (!usedIds.contains(i)) {
                availableIds.add(i);
            }
        }

        if (usedIds.isEmpty()) {
            ID = 0;
        } else {
            ID = Collections.max(usedIds);
        }
    }
    private boolean isIdPresent(int id){
        if (id > ID || id < 0){
            System.out.printf("таск по данному ID =  %d, не обнаружен  .\n", id);
            return false;
        }
        return taskHashMap.containsKey(id) || epicHashMap.containsKey(id) || subtaskHashMap.containsKey(id);
    }
    private int getNextId() {
        if (!availableIds.isEmpty()) {
            int id = availableIds.iterator().next();
            availableIds.remove(id);
            return id;
        }
        return ++ID;
    }
}
