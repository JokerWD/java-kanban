package test;

import entities.Subtask;
import enums.StatusTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    private Subtask subtask1;
    private Subtask subtask2;
    private Subtask subtask3;

    @BeforeEach
    public void setUp() {
        subtask1 = new Subtask("Подзадача 1", "Описание", StatusTask.NEW, 1);
        subtask1.setId(1);

        subtask2 = new Subtask("Подзадача 1", "Описание", StatusTask.NEW, 1);
        subtask2.setId(1);

        subtask3 = new Subtask("Подзадача 3", "Описание", StatusTask.DONE, 2);
        subtask3.setId(2);
    }

    @Test
    public void testSubtaskConstructorAndGetters() {
        assertEquals("Подзадача 1", subtask1.getName());
        assertEquals("Описание", subtask1.getDescription());
        assertEquals(StatusTask.NEW, subtask1.getStatusTask());
        assertEquals(1, subtask1.getEpicId());
    }

    @Test
    public void testSubtaskToString() {
        String expected = "Subtask{id=1, name='Подзадача 1', description='Описание', statusTask=NEW, epicId=1}";
        Assertions.assertEquals(expected, subtask1.toString());
    }

    @Test
    public void testSubtaskEquals() {
        assertEquals(subtask1, subtask2);
        assertNotEquals(subtask1, subtask3);
    }

    @Test
    public void testSubtaskHashCode() {
        assertEquals(subtask1.hashCode(), subtask2.hashCode());
        assertNotEquals(subtask1.hashCode(), subtask3.hashCode());
    }
}