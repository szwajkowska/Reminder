package pl.ania.lukasz;

import pl.ania.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NoteRepository {
    public void save(Note note) {
        saveNote(note.getTitle(), note.getBody());
    }

    public void saveNote(String title, String body){
        try {
            PrintWriter writer = new PrintWriter(new File("Notes/" + title + ".txt"));
            writer.println(body);
            writer.close();
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }

    }

    public void saveNote(Note note) {

    }
}
