package pl.ania.lukasz;

import pl.ania.Note;

public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void add(Note note) {
        noteRepository.save(note);
    }
}
