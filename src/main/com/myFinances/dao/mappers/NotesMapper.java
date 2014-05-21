package main.com.myFinances.dao.mappers;

import main.com.myFinances.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 4/29/2014.
 */
public class NotesMapper implements RowMapper<Notes> {
    @Override
    public Notes mapRow(ResultSet resultSet, int i) throws SQLException {
        Notes notes = new Notes(resultSet.getDouble("amount"), resultSet.getDate("notedate"));
        notes.setId(resultSet.getInt("id"));
        notes.setInfo(resultSet.getString("info"));
        notes.setCategory(new Category(resultSet.getString("category")));
        notes.getCategory().setId(resultSet.getInt("categ_id"));
        return notes;
    }
}
