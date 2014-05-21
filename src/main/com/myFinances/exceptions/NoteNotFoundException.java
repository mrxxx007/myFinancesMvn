package main.com.myFinances.exceptions;

/**
 * Created by Admin on 01.05.14.
 */
public class NoteNotFoundException extends DAOException {
    public NoteNotFoundException() {
        super("Note not found.");
    }
}
