package test;

import entities.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import enums.StatusTask;

public class EpicTest {
    private Epic epic;

    @BeforeEach
    public void setUp() {
        epic = new Epic("Эпик 1", "Описание", StatusTask.NEW);
    }

    @Test
    void testAddSubtask() {
        epic.addSubtask(1);
        epic.addSubtask(2);

        assertEquals(2, epic.getSubtaskIds().size(), "Количество подзадач должно быть 2");
    }

    @Test
    void testRemoveSubtask() {
        epic.addSubtask(1);
        epic.addSubtask(2);
        epic.removeSubtask(1);

        assertEquals(1, epic.getSubtaskIds().size(), "Количество подзадач должно быть 1 после удаления");
    }
}
