package pl.ania;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    NotesShowing notesShowing = new NotesShowing();
    NotesRepository notesRepository = new NotesRepository("Notes");
    Reminder reminder = new Reminder(notesRepository);
    NotesCreator notesCreator = new NotesCreator();

    public Menu() {
        new Thread(reminder).start();
        String userChoice;

        do {

            System.out.println("MENU");
            userChoice = showQuestionAndReadAnswer(" 1) Dodaj notatke - wybierz 1\n 2) Przegląd notatek - wybierz 2\n 3) Wyjdź z programu - wybierz x");
            if (userAnswer("1", userChoice)) {
                notesRepository.addNote(notesCreator.createNote());

            } else if (userAnswer("2", userChoice)) {
                notesShowing.showNote(notesRepository.getNotesList());

                chooseIfReadNote();

            } else if (userAnswer("x", userChoice)) {
                System.out.println("Do widzenia");
            } else {
                System.out.println("Niepoprawny wybór. Wybierz jeszcze raz");
            }
        } while (!userAnswer("x", userChoice));

        System.exit(0);
    }

    private void chooseIfReadNote() {

        do {
            System.out.println();
            String userChoice = showQuestionAndReadAnswer("1) Przeczytaj notatke - wybierz 1\n2) Wróć do menu - wybierz x");
            if (userAnswer("1", userChoice)) {
                chooseNumber();
            } else if (userAnswer("x", userChoice)) {
                break;
            } else {
                System.out.println("Niepoprawny wybór. Wybierz jeszcze raz");
            }

        } while (true);
    }

    private void chooseNumber() {
        do {

            String userChoice = showQuestionAndReadAnswer("Wpisz numer notatki, którą chcesz zobaczyć lub cofnij - wybierz x");
            if (notesShowing.getNotesMap().containsKey(userChoice)) {
                chooseIfModify(notesShowing.readBody(userChoice, notesRepository.getNotesList()));
            } else if (userAnswer("x", userChoice)) {
                break;
            } else {
                System.out.println("Nie ma takiej notatki. Wybierz jeszcze raz");
            }
        } while (true);
    }

    private void chooseIfModify(String id) {
        do {
            String userChoice = showQuestionAndReadAnswer("Czy chesz zmodyfikowować notatkę? Wpisz 'TAK' lub 'NIE'");
            if (userAnswer("TAK", userChoice)) {
               List<Note> filterNoteList = notesRepository.getNotesList().stream()
                    .filter(note1 -> note1.getId().equals(id))
                   .collect(Collectors.toList());


                Optional.ofNullable(notesCreator.modifyNote(filterNoteList.get(0))).ifPresent(newNote -> notesRepository.updateNote(filterNoteList.get(0), newNote));
            } else if (userAnswer("NIE", userChoice)) {
                break;
            } else {
                System.out.println("Niepoprawny wybór. Spróbuj jeszcze raz");
            }
        } while (true);

    }

    private boolean userAnswer(String userResponse, String userChoice) {
        return userChoice.equalsIgnoreCase(userResponse);
    }

    private String showQuestionAndReadAnswer(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
