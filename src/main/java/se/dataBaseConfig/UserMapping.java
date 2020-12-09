package se.dataBaseConfig;

import org.springframework.jdbc.core.RowMapper;
import se.pckg.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapping implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getInt("role"));
        return user;
    }
}
