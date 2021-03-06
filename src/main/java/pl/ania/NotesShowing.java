package pl.ania;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesShowing {

    private HashMap<String, String> notesMap = new HashMap<>();

    public Map<String, String> getNotesMap() {
        return notesMap;
    }

    public void showNote(List<Note> noteList, PrintWriter out) {

        for (Integer i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            out.println((i + 1) + ")" + note.getTitle() + " " + note.getDate());
            Integer iPlusOne = i + 1;
            notesMap.put(iPlusOne.toString(), note.getId());
        }

    }

    public String readBody(String key, List<Note> noteList, PrintWriter out) {
        String id = notesMap.get(key);
        noteList.stream()
            .filter(note -> note.getId().equals(id))
            .forEach(note -> out.println("Treść notatki to: " + note.getBody()));
        return id;
    }



}

