package pl.ania;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class NotesCreator {

    Scanner scanner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public NotesCreator(BufferedReader in) {
        scanner = new Scanner(in);
    }

    public Note createNote(PrintWriter out) {
        String title = showQuestionAndReadAnswer("Proszę podać tytuł notatki", out);
        String body = showQuestionAndReadAnswer("Proszę podać tresc notatki", out);
        return new Note(title, body, addDate("Proszę podać datę w formacie yyyy-MM-dd HH:mm", out), false, UUID.randomUUID().toString());

    }

    private LocalDateTime addDate(String message, PrintWriter out) {
        while (true) {
            String date = showQuestionAndReadAnswer(message, out);
            try {
                out.println("Notatka została zapisana");
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException pe) {
                pe.printStackTrace();
            }
            out.println("Niepoprawny format daty! Spróbuj jeszcze raz");
        }

    }

    public Note modifyNote(Note oldNote, PrintWriter out) {

        String title = oldNote.getTitle();
        String body = oldNote.getBody();
        LocalDateTime date = oldNote.getDate();
        Note newNote = null;
        boolean reminded = oldNote.isWasReminded();
        while (true) {
            String userChoice = showQuestionAndReadAnswer("Co chcesz zmodyfikować? \n1) Zmień tytuł - wybierz 1" +
                "\n2)Zmień treść notatki - wybierz 2\n3)Zmień datę przypomnienia - wybierz 3\n" +
                "4)Cofnij - wybierz x ", out);

            if (userAnswer("1", userChoice)) {
                title = showQuestionAndReadAnswer("Podaj nowy tytuł", out);
                reminded = false;
            } else if (userAnswer("2", userChoice)) {
                body = showQuestionAndReadAnswer("Podaj nową treść", out);
                reminded = false;
            } else if (userAnswer("3", userChoice)) {
                date = addDate("Podaj nową datę", out);
                reminded = false;
            } else if (userAnswer("x", userChoice)) {
                break;
            } else {
                out.println("Nieprawidłowy wybór. Spróbuj jeszcze raz");
            }

            newNote = new Note(title, body, date, reminded, oldNote.getId());
            out.println("Notatka zostałą zmodyfikowana");

        }
        return newNote;
    }

    private boolean userAnswer(String userResponse, String userChoice) {
        return userChoice.equalsIgnoreCase(userResponse);
    }

    private String showQuestionAndReadAnswer(String question, PrintWriter out) {
        out.println(question);
        return scanner.nextLine();
    }

}
