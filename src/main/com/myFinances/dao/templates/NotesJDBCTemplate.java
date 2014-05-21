package main.com.myFinances.dao.templates;

import main.com.myFinances.dao.NoteDAO;
import main.com.myFinances.dao.mappers.NotesMapper;
import main.com.myFinances.exceptions.NoteNotFoundException;
import main.com.myFinances.exceptions.UserNotFoundException;
import main.com.myFinances.model.Notes;
import main.com.myFinances.model.User;
import main.com.myFinances.service.UserServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergey Popov on 4/29/2014.
 */
public class NotesJDBCTemplate implements NoteDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Notes getNote(int id) throws NoteNotFoundException {
        String SQL = "select N.id, N.amount, C.name as CATEGORY, N.notedate, N.info, C.id as CATEG_ID " +
                "from note N join category C on N.category_id = C.id where N.id = ?";
        try {
            Notes notes = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new NotesMapper());
            return notes;
        } catch (EmptyResultDataAccessException e) {
            throw new NoteNotFoundException();
        }
    }

    @Override
    public List<Notes> getNotesForUser(int userId) {
        String SQL = "select N.id, N.amount, C.name as CATEGORY, N.notedate, N.info, C.id as CATEG_ID " +
                "from note N join category C on N.category_id = C.id " +
                "where N.user_id = ? order by N.notedate;";
        return jdbcTemplate.query(SQL, new Object[]{userId}, new NotesMapper());
    }

    @Override
    public int saveNote(Notes notes, int userId) {
        if (notes.getId() <= 0) {
            return insertNote(notes, userId);
        } else {
            updateNote(notes);
            return notes.getId();
        }
    }

    @Override
    public void deleteNote(int id) {
        final String SQL = "delete from note where id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<Notes> getNotes(String login, Date beginDate, Date endDate, int categoryId, Double amount)
            throws UserNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        int userId = UserServiceImpl.getInstance().getUser(login).getId();

        LinkedList<Object> paramList = new LinkedList();
        StringBuilder SQL = new StringBuilder();
        SQL.append("select N.id, N.amount, C.name as CATEGORY, N.notedate, N.info, C.id as CATEG_ID ")
                .append("from note N join category C on N.category_id = C.id ")
                .append("where (N.user_id = ?) and (N.notedate between ? and ?) ");
        paramList.add(userId);
        paramList.add(beginDate);
        paramList.add(endDate);
        if(categoryId > 0) {
            SQL.append(" and (N.category_id = ?) ");
            paramList.add(categoryId);
        }
        if (amount != null) {
            SQL.append(" and (amount > ?) ");
            paramList.add(amount);
        }
        SQL.append(" order by N.notedate ");

        return jdbcTemplate.query(SQL.toString(), paramList.toArray(), new NotesMapper());
    }


    private int insertNote(final Notes notes, final int userId) {
        final String SQL = "insert into note (amount, category_id, importancy_id, notedate, info, user_id) " +
                "values (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(SQL, new String[]{"id"});
                pst.setDouble(1, notes.getAmount());
                pst.setInt(2, notes.getCategory().getId());
                pst.setObject(3, null);
                pst.setDate(4, new java.sql.Date(notes.getDate().getTime()));
                pst.setString(5, notes.getInfo());
                pst.setInt(6, userId);
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    private void updateNote(Notes notes) {
        String SQL = "update note set amount = ?, category_id = ?, " +
                "importancy_id = ?, notedate = ?, info = ? where id = ?";
        jdbcTemplate.update(SQL, notes.getAmount(), notes.getCategory().getId(),
                null, notes.getDate(), notes.getInfo(), notes.getId());
    }

}