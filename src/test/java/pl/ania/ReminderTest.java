package pl.ania;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lukasz on 2016-11-17.
 */
public class ReminderTest {

    NotesRepository notesRepository = Mockito.mock(NotesRepository.class);

    Reminder reminder = new Reminder(notesRepository);

    @Test
    public void shouldRemind() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now().minusMinutes(1);
        Note note = new Note("a", "b", now, false, "1");

        //when
        boolean result = reminder.isFromPast(note);

        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldListNotesForReminding() throws Exception {
        //given
        Note note = new Note("before", "b", LocalDateTime.now().minusMinutes(1), false, "1");
        Note note2 = new Note("after", "b", LocalDateTime.now().plusMinutes(1), false, "2");
        Mockito.when(notesRepository.getNotesList()).thenReturn(Arrays.asList(note, note2));
        //when
        List<Note> notesResult = reminder.findNotesForReminding();
        //then
        Assert.assertEquals(1, notesResult.size());
        Assert.assertEquals("before", notesResult.get(0).getTitle());
    }

    @Test
    public void shouldCheckIfWasRemind() throws Exception {
        //given
        Note note = new Note("a", "b", LocalDateTime.now().minusMinutes(1), false, "1");
        List<Note> noteList = new ArrayList<>();
        //when
        noteList.add(note);
        boolean wasRemind = reminder.checkIfWasRemind(note);
        //then
        Assert.assertTrue(wasRemind);

    }
}