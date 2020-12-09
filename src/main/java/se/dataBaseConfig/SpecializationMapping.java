package se.dataBaseConfig;

import org.springframework.jdbc.core.RowMapper;
import se.pckg.Specialization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecializationMapping implements RowMapper<Specialization> {
    @Override
    public Specialization mapRow(ResultSet resultSet, int i) throws SQLException {
        Specialization spec = new Specialization();
        spec.setId(resultSet.getInt("id"));
        spec.setName(resultSet.getString("name"));
        spec.setNarrow(resultSet.getBoolean("narrow"));
        spec.setAmountOfDocs(resultSet.getInt("amountOfDocs"));
        spec.setWageRate(resultSet.getDouble("wageRate"));
        spec.setCosts(resultSet.getDouble("costs"));
        return spec;
    }
}