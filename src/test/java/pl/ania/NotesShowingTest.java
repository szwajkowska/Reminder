package pl.ania;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotesShowingTest {

    @Test
    public void shouldShowNote() throws Exception {
        //given
        NotesShowing notesShowing = new NotesShowing();
        List<Note> notesList = new ArrayList<>();
        Note note1 = new Note("a", "abc", LocalDateTime.now(), false, "1");
        Note note2 = new Note("z", "xyz", LocalDateTime.now(), false, "3");
        PrintWriter out = Mockito.mock(PrintWriter.class);

        notesList.add(note1);
        notesList.add(note2);
        //when
        notesShowing.showNote(notesList, out);
        //then
        Assert.assertEquals(2, notesShowing.getNotesMap().size());
        Assert.assertEquals(note1, notesShowing.getNotesMap().get("1"));
        Assert.assertEquals(note2, notesShowing.getNotesMap().get("2"));
    }

}
