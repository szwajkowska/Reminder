package pl.ania;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    Scanner scanner;
    NotesShowing notesShowing = new NotesShowing();
    NotesRepository notesRepository = new NotesRepository("Notes");
    Reminder reminder;
    NotesCreator notesCreator;

    public Menu(PrintWriter out, BufferedReader in) {
        notesCreator = new NotesCreator(in);
        reminder = new Reminder(notesRepository, out);
        scanner = new Scanner(in);
        new Thread(reminder).start();
        String userChoice;

        do {

            out.println("MENU");
            userChoice = showQuestionAndReadAnswer(" 1) Dodaj notatke - wybierz 1\n 2) Przegląd notatek - wybierz 2" +
                "\n 3) Wyjdź z programu - wybierz x", out);
            if (userAnswer("1", userChoice)) {
                notesRepository.addNote(notesCreator.createNote(out));

            } else if (userAnswer("2", userChoice)) {
                notesShowing.showNote(notesRepository.getNotesList(), out);

                chooseIfReadNote(out);

            } else if (userAnswer("x", userChoice)) {
                out.println("Do widzenia");
            } else {
                out.println("Niepoprawny wybór. Wybierz jeszcze raz");
            }
        } while (!userAnswer("x", userChoice));

        System.exit(0);
    }

    

    private void chooseIfReadNote(PrintWriter out) {

        do {
            out.println();
            String userChoice = showQuestionAndReadAnswer("1) Przeczytaj notatke - wybierz 1\n2) Wróć do menu - wybierz x", out);
            if (userAnswer("1", userChoice)) {
                chooseNumber(out);
            } else if (userAnswer("x", userChoice)) {
                break;
            } else {
                out.println("Niepoprawny wybór. Wybierz jeszcze raz");
            }

        } while (true);
    }

    private void chooseNumber(PrintWriter out) {
        do {

            String userChoice = showQuestionAndReadAnswer("Wpisz numer notatki, którą chcesz zobaczyć lub cofnij - wybierz x", out);
            if (notesShowing.getNotesMap().containsKey(userChoice)) {
                chooseIfModify(notesShowing.readBody(userChoice, notesRepository.getNotesList(), out), out);
            } else if (userAnswer("x", userChoice)) {
                break;
            } else {
                out.println("Nie ma takiej notatki. Wybierz jeszcze raz");
            }
        } while (true);
    }

    private void chooseIfModify(String id, PrintWriter out) {
        do {
            String userChoice = showQuestionAndReadAnswer("Czy chesz zmodyfikowować notatkę? Wpisz 'TAK' lub 'NIE'", out);
            if (userAnswer("TAK", userChoice)) {
               List<Note> filterNoteList = notesRepository.getNotesList().stream()
                    .filter(note1 -> note1.getId().equals(id))
                   .collect(Collectors.toList());


                Optional.ofNullable(notesCreator.modifyNote(filterNoteList.get(0), out)).ifPresent(newNote -> notesRepository.updateNote(filterNoteList.get(0), newNote));
            } else if (userAnswer("NIE", userChoice)) {
                break;
            } else {
                out.println("Niepoprawny wybór. Spróbuj jeszcze raz");
            }
        } while (true);

    }

    private boolean userAnswer(String userResponse, String userChoice) {
        return userChoice.equalsIgnoreCase(userResponse);
    }

    private String showQuestionAndReadAnswer(String question, PrintWriter out) {
        out.println(question);
        return scanner.nextLine();
    }
}
