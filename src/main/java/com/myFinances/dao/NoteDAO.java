package com.myFinances.dao;

import com.myFinances.exceptions.NoteNotFoundException;
import com.myFinances.exceptions.UserNotFoundException;
import com.myFinances.model.Notes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergey Popov on 4/29/2014.
 */
public interface NoteDAO {
    public Notes getNote(int id) throws NoteNotFoundException;

    public List<Notes> getNotesForUser(int userId);

    public List<Notes> getNotes(String login, Date beginDate, Date endDate, int categoryId, Double amount) throws UserNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    public int saveNote(Notes notes, int userId);

    public void deleteNote(int id);
}
