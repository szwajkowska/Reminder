package pl.ania;

import java.time.LocalDateTime;

public class Note {

    private String body;
    private String title;
    private LocalDateTime date;
    private boolean wasReminded;
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!body.equals(note.body)) return false;
        if (!title.equals(note.title)) return false;
        if(!date.equals(note.date)) return false;
        return id == note.id;

    }

    @Override
    public int hashCode() {
        int result = body.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    public Note(String title, String body, LocalDateTime date, boolean wasReminded, String id) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.wasReminded = wasReminded;
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isWasReminded() {return wasReminded; }

    public String getId() {return id;}
}
