package se.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import se.dataBaseConfig.DoctorMapping;
import se.pckg.Doctor;
import se.pckg.Specialization;

import java.util.Collection;

public class DoctorsRepository {
    private static final int toDate = 2020;
    public static JdbcTemplate jdbcTemplate = se.dataBaseConfig.DataBaseConfig.getJdbcTemplate();
    public static Collection<Doctor> readAll() {
        String sql = "SELECT * FROM public.\"doctor\"";
        return (jdbcTemplate.query(sql, new DoctorMapping()));
    }

    public static Collection<Doctor> readAllWithSpec(String spec) {
        String sql = "SELECT * FROM public.\"doctors\" "
                + "WHERE specialization = \'" + spec + "\'";
        return (jdbcTemplate.query(sql, new DoctorMapping()));
    }

    public static Doctor readById(Integer id) {
        String sql = "SELECT * FROM public.\"doctors\" "
                + "WHERE id = " + id ;
        return (jdbcTemplate.query(sql, new DoctorMapping()).get(0));
    }

    public static void create(Doctor doctor) {
        countingSalary(doctor);
        String sql = "INSERT INTO public.\"doctors\" "
                + "(specialization, \"lastName\", \"firstName\", \"middleName\", birthdate, \"employmentDate\", \"sectionId\", salary) "
                + "VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctor.getSpecialization(),
                doctor.getLastName(), doctor.getFirstName(), doctor.getMiddleName(),
                doctor.getBirthdate(), doctor.getEmploymentDate(),
                doctor.getSectionId(),
                doctor.getSalary());
    }

    public static void update(Doctor doctor) {
        Specialization spec = SpecializationsRepository.readByName(DoctorsRepository.readById(doctor.getId()).getSpecialization());
        if(spec != null) {
            if (!doctor.getSpecialization().equals(spec.getName())) {
                spec.setAmountOfDocs(spec.getAmountOfDocs() - 1);
                spec.setCosts(spec.getCosts() - DoctorsRepository.readById(doctor.getId()).getSalary());
                SpecializationsRepository.updateCosts(spec);
            }
        }
        countingSalary(doctor);
        String sql = "UPDATE public.\"doctors\" SET "
                + "specialization = ?, \"lastName\" = ?, \"firstName\" = ?, \"middleName\" = ?, birthdate = ?, \"employmentDate\" = ?, \"sectionId\" = ?, salary = ?"
                + "WHERE id = ?";
        jdbcTemplate.update(sql, doctor.getSpecialization(),
                doctor.getLastName(), doctor.getFirstName(), doctor.getMiddleName(),
                doctor.getBirthdate(), doctor.getEmploymentDate(),
                doctor.getSectionId(),
                doctor.getSalary(), doctor.getId());
    }

    private static void countingSalary(Doctor doctor) {
        Specialization spec = SpecializationsRepository.readByName(doctor.getSpecialization());
        double multiplier;
        switch((toDate - doctor.getEmploymentDate()) / 5){
            case 0: multiplier = 1; break;
            case 1: multiplier = 1.05; break;
            case 2,3: multiplier = 1.1; break;
            case 4,5,6: multiplier = 1.15; break;
            default: multiplier = 1.2;
        }
        if((toDate - doctor.getBirthdate()) >= 60){
            multiplier += 0.5;
        }
        doctor.setSalary(spec.getWageRate()*multiplier);
        if(doctor.getId() != -1) {
            Doctor o = DoctorsRepository.readById(doctor.getId());
            if (o != null) {
                if (!o.getSpecialization().equals(spec.getName())) {
                    spec.setCosts(spec.getCosts() + doctor.getSalary());
                    spec.setAmountOfDocs(spec.getAmountOfDocs() + 1);
                }
            }
        }
        else {
            spec.setCosts(spec.getCosts() + doctor.getSalary());
            spec.setAmountOfDocs(spec.getAmountOfDocs() + 1);
        }
        SpecializationsRepository.updateCosts(spec);
    }

    public static void delete(Integer id) {
        Doctor doc = DoctorsRepository.readById(id);
        Specialization spec = SpecializationsRepository.readByName(doc.getSpecialization());
        spec.setAmountOfDocs(spec.getAmountOfDocs() - 1);
        spec.setCosts(spec.getCosts() - doc.getSalary());
        SpecializationsRepository.updateCosts(spec);
        String sql = "DELETE FROM public.\"doctors\" "
                + "WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public static void update(Doctor doctor, Specialization spec) {
        countingSalary(doctor, spec);
        String sql = "UPDATE public.\"doctors\" SET "
                + "specialization = ?, \"lastName\" = ?, \"firstName\" = ?, \"middleName\" = ?, birthdate = ?, \"employmentDate\" = ?, \"sectionId\" = ?, salary = ?"
                + "WHERE id = ?";
        jdbcTemplate.update(sql, doctor.getSpecialization(),
                doctor.getLastName(), doctor.getFirstName(), doctor.getMiddleName(),
                doctor.getBirthdate(), doctor.getEmploymentDate(),
                doctor.getSectionId(),
                doctor.getSalary(), doctor.getId());
    }

    private static void countingSalary(Doctor doctor, Specialization spec) {
        double multiplier;
        switch((toDate - doctor.getEmploymentDate()) / 5){
            case 0: multiplier = 1; break;
            case 1: multiplier = 1.05; break;
            case 2,3: multiplier = 1.1; break;
            case 4,5,6: multiplier = 1.15; break;
            default: multiplier = 1.2;
        }
        if((toDate - doctor.getBirthdate()) >= 60){
            multiplier += 0.5;
        }
        doctor.setSalary(spec.getWageRate()*multiplier);
    }
}
