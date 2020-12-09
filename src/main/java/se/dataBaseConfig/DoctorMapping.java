package se.dataBaseConfig;


import org.springframework.jdbc.core.RowMapper;
import se.pckg.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapping implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
        Doctor doc = new Doctor();
        doc.setId(resultSet.getInt("id"));
        doc.setSpecialization(resultSet.getString("specialization"));
        doc.setLastName(resultSet.getString("lastName"));
        doc.setFirstName(resultSet.getString("firstName"));
        doc.setMiddleName(resultSet.getString("middleName"));
        doc.setBirthdate(resultSet.getInt("birthdate"));
        doc.setEmploymentDate(resultSet.getInt("employmentDate"));
        doc.setSectionId(resultSet.getInt("sectionId"));
        doc.setSalary(resultSet.getDouble("salary"));
        return doc;
    }
}