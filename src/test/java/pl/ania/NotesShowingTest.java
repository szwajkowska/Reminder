package pl.ania;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Not;
import pl.ania.lukasz.NoteRepository;
import pl.ania.lukasz.NoteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesShowingTest {

    @Test
    public void shouldShowNote() throws Exception {
        //given
        NotesShowing notesShowing = new NotesShowing();
        List<Note> notesList = new ArrayList<>();
        Note note1 = new Note("a", "abc", LocalDateTime.now(), false, "1");
        Note note2 = new Note("z", "xyz", LocalDateTime.now(), false, "3");
        notesList.add(note1);
        notesList.add(note2);
        //when
        notesShowing.showNote(notesList);
        //then
        Assert.assertEquals(2, notesShowing.getNotesMap().size());
        Assert.assertEquals(note1, notesShowing.getNotesMap().get("1"));
        Assert.assertEquals(note2, notesShowing.getNotesMap().get("2"));
    }

}
