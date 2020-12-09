package se.DAO;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import se.dataBaseConfig.DataBaseConfig;
import se.dataBaseConfig.UserMapping;
import se.pckg.User;

import java.util.List;

@Component
public class UsersRepository {
    private static JdbcTemplate jdbcTemplate = DataBaseConfig.getJdbcTemplate();


    public User getByLogPass(String login, String password) {


        String sql = "select * from public.users where login = '" + login + "'and password =  '" + password + "'";
        if (jdbcTemplate.query(sql, new UserMapping()).isEmpty())
            return null;

        return jdbcTemplate.query(sql, new UserMapping()).get(0);


    }

    private static int READER_COUNT;

    public static List<User> readAll() {
        String sql = "SELECT * FROM public.users";
        return (jdbcTemplate.query(sql, new UserMapping()));
    }

    public static User readByLogin(String login) {
        String sql = "select * from public.users where login = '" + login + "'";
        return jdbcTemplate.query(sql, new UserMapping()).get(0);
    }

    public static void delete(String login) {
        jdbcTemplate.update("delete from public.users where login = ?", login);
    }


    public static void create(User user) {
        jdbcTemplate.update(
                "INSERT INTO public.users (login, password , role)" +
                        "VALUES (?, ?, ?)",
                user.getLogin(), user.getPassword(), user.getRole());
    }
}
