package Entities;


import java.util.*;

public class TaskStorage {
    private static HashMap<Integer, Task> taskHashMap;
    private static HashMap<Integer, Epic> epicHashMap;
    private static HashMap<Integer, Subtask> subtaskHashMap;
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
            Subtask subtask = (Subtask) task;
            Epic epic = epicHashMap.get(subtask.getEpicId());
            if (epic != null) {
                epic.addSubtask(id);
                subtaskHashMap.put(id, subtask);
            } else {
                System.out.println("Эпик по данному ID " + subtask.getEpicId() + " не существует.");
                availableIds.add(id);
            }
        } else {
            taskHashMap.put(id, task);
        }
    }

    public void removeItemById(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            if (task instanceof Epic) {
                Epic epic = (Epic) task;
                for (int subtaskId : epic.getSubtaskIds()) {
                    subtaskHashMap.remove(subtaskId);
                }
                epicHashMap.remove(id);
            } else if (task instanceof Subtask) {
                Subtask subtask = (Subtask) task;
                Epic epic = epicHashMap.get(subtask.getEpicId());
                if (epic != null) {
                    epic.removeSubtask(id);
                }
                subtaskHashMap.remove(id);
            } else {
                taskHashMap.remove(id);
            }
        }
    }

    public static Task getTaskById(int id) {
        if (isIdPresent(id)) {
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
        Task existingTask = getTaskById(id);
        if (existingTask != null) {
            if (existingTask instanceof Subtask) {
                Subtask subtask = (Subtask) existingTask;
                subtask.setName(updatedTask.getName());
                subtask.setDescription(updatedTask.getDescription());
                subtask.setStatusTask(updatedTask.getStatusTask());
                Epic epic = epicHashMap.get(subtask.getEpicId());
                if (epic != null) {
                    epic.updateStatus();
                }
            } else {
                existingTask.setName(updatedTask.getName());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setStatusTask(updatedTask.getStatusTask());
            }
        } else {
            System.out.println("Задача по данному ID " + id + " не обнаружена.");
        }
    }

    public void remove(String category) {
        switch (category) {
            case "Epic":
                epicHashMap.clear();
                subtaskHashMap.clear();
                break;
            case "Subtask":
                subtaskHashMap.clear();
                epicHashMap.values().forEach(epic -> epic.getSubtaskIds().clear());
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
    public List<Subtask> getSubtasksForEpic(int epicId) {
        List<Subtask> subtasks = new ArrayList<>();
        Epic epic = epicHashMap.get(epicId);
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                Subtask subtask = subtaskHashMap.get(subtaskId);
                if (subtask != null) {
                    subtasks.add(subtask);
                }
            }
        }
        return subtasks;
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

    private static boolean isIdPresent(int id) {
        if (id > ID || id < 0) {
            System.out.printf("таск по данному ID =  %d, не обнаружен  .%n", id);
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

    public HashMap<Integer, Task> getTaskHashMap() {
        return taskHashMap;
    }

    public static HashMap<Integer, Epic> getEpicHashMap() {
        return epicHashMap;
    }

    public static HashMap<Integer, Subtask> getSubtaskHashMap() {
        return subtaskHashMap;
    }
}
