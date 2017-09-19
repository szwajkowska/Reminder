package pl.ania;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Reminder implements Runnable {

    private final NotesRepository notesRepository;
    private PrintWriter out;


    public Reminder(NotesRepository notesRepository, PrintWriter out) {
        this.notesRepository = notesRepository;
        this.out = out;
    }

    boolean isFromPast(Note note) {
        return LocalDateTime.now().isAfter(note.getDate());
    }

    boolean checkIfWasRemind(Note note) {
        return note.isWasReminded();
    }

    List<Note> findNotesForReminding() {
        return notesRepository.getNotesList().stream()
            .filter(this::isFromPast)
            .filter(note -> !checkIfWasRemind(note))
            .collect(Collectors.toList());
    }

    public void remind() {

        List<Note> notesForReminding = findNotesForReminding();
        if (!notesForReminding.isEmpty()) {

            out.println("-----------------------------------------");
            out.println("PRZYPOMINAJKA!!!");
            notesForReminding
                .forEach(note -> out.println(note.getTitle()));
            notesForReminding
                .forEach(notesRepository::markAsReminded);
            out.println("PRZYPOMINAJKA!!!");
            out.println("-----------------------------------------");

        }

    }

    @Override
    public void run() {
        while (true) {
            remind();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
