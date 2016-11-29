package pl.ania;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Created by lukasz on 2016-11-14.
 */
public class NotesRepositoryTest {

    NotesRepository notesRepository;

    private String tempPath;


    @Before
    public void setup() throws IOException {
        tempPath = Files.createTempDirectory("testingNotes").toString();
        notesRepository = new NotesRepository(tempPath);
    }

    @Test
    public void shouldAddNoteToList() throws Exception {
        //given
        Note note = new Note("title", "body", LocalDateTime.now(),false, "32");

        //when
        notesRepository.addNote(note);

        //then
        Assert.assertEquals(1, notesRepository.getNotesList().size());
        Assert.assertTrue(Files.exists(Paths.get(tempPath, "title.txt")));
    }

    @Test
    public void shouldReadFilesInFolder() throws Exception {
        //given
        PrintWriter writer = new PrintWriter(new File(tempPath + "/test.txt"));
        writer.println("test\ntestujemy\n2007-12-03T10:15:30\nfalse");
        writer.close();

        //when
        notesRepository.getNotesList();

        //then
        Note note = notesRepository.getNotesList().get(0);
        Assert.assertEquals("test", note.getTitle());
        Assert.assertEquals("testujemy", note.getBody());
        Assert.assertEquals("2007-12-03T10:15:30", note.getDate().toString());
        Assert.assertEquals(Boolean.valueOf("false"), note.isWasReminded());

    }
}