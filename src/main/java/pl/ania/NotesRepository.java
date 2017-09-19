package pl.ania;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesRepository {

    private List<Note> notesList = new ArrayList<>();
    private String location;
    private Random random = new Random();

    public NotesRepository(String location) {
        this.location = location;
        readNotesFromFile();
    }

    public void addNote(Note note) {
        notesList.add(note);
        saveNote(note);
    }

    public List<Note> getNotesList() {
        return notesList;
    }

    private List<Note> readNotesFromFile() {
        notesList.clear();
        try {
            Files.walk(Paths.get(location))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        List<String> lines = Files.readAllLines(file);
                        notesList.add(new Note(lines.get(0),
                            lines.get(1),
                            LocalDateTime.parse(lines.get(2)),
                            Boolean.valueOf(lines.get(3)),
                            lines.get(4)));
                    } catch (IOException | DateTimeParseException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notesList;
    }

    private void saveNote(Note note) {
        String fileName = location + "/" + note.getTitle() + ".txt";
        if (!Files.exists(Paths.get(fileName))) {
            writeToFile(note, note.getTitle());
        } else {
            writeToFile(note, note.getTitle() + random.nextInt(1000));
        }
    }

    private void writeToFile(Note note, String fileName) {
        try {

            PrintWriter writer = new PrintWriter(new File(location + "/" + fileName + ".txt"));
            writer.println(note.getTitle() + "\n" + note.getBody() + "\n" + note.getDate()
                + "\n" + note.isWasReminded() + "\n" + note.getId());
            writer.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }

    public void markAsReminded(Note oldNote) {
        changeNote(oldNote, new Note(oldNote.getTitle(), oldNote.getBody(), oldNote.getDate(), true, oldNote.getId()));
    }

    public void updateNote(Note oldNote, Note newNote) {
        changeNote(oldNote, newNote);
    }

    private void changeNote(Note oldNote, Note newNote) {
        try {
            Files.walk(Paths.get(location))
                .filter(Files::isRegularFile)
                .forEach((path) -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
        notesList.remove(oldNote);
        notesList.add(newNote);
        notesList.forEach(this::saveNote);
    }

}
