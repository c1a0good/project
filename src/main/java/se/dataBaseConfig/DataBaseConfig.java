package se.dataBaseConfig;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataBaseConfig {
    static public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    static public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/JavaLaba5");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("admin");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }
}
