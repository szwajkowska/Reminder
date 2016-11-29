package pl.ania;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Reminder implements Runnable {

    private final NotesRepository notesRepository;


    public Reminder(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
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

            System.out.println("-----------------------------------------");
            System.out.println("PRZYPOMINAJKA!!!");
            notesForReminding
                .forEach(note -> System.out.println(note.getTitle()));
            notesForReminding
                .forEach(notesRepository::markAsReminded);
            System.out.println("PRZYPOMINAJKA!!!");
            System.out.println("-----------------------------------------");

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
