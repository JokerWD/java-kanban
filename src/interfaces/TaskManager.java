package interfaces;

import entities.Epic;
import entities.Subtask;
import entities.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getTasks();
    List<Subtask> getSubtasks();
    List<Epic> getEpics();
    List<Subtask> getEpicSubtasks(int epicId);
    Task getTask(int id);
    Subtask getSubtask(int id);
    Epic getEpic(int id);
    void addNewTask(Task task);
    void addNewEpic(Epic epic);
    void addNewSubtask(Subtask subtask);
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);
    void deleteTask(int id);
    void deleteEpic(int id);
    void deleteSubtask(int id);
    void deleteTasks();
    void deleteSubtasks();
    void deleteEpics();
    List<Task> getHistory();
    Task getTaskById(int id);
    Subtask getSubtaskById(int id);
    Epic getEpicById(int id);
}
