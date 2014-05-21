package main.com.myFinances.dao.mappers;

import main.com.myFinances.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sergey Popov on 4/29/2014.
 */
public class UserMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getString("login"),
        resultSet.getString("password"));
        user.setId(resultSet.getInt("id"));
        user.setBalance(resultSet.getDouble("balance"));
        return user;
    }
}
