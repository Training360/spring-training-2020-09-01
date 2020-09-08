package employees.employees.repository;

import employees.employees.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImageOp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeesDao {

    private JdbcTemplate jdbcTemplate;

    private static Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("emp_name");
        long id = resultSet.getLong("id");
        Employee employee = new Employee(id, name);
        return employee;
    }

    public List<Employee> listEmployees(Optional<String> prefix) {
        return jdbcTemplate.query("select id, emp_name from employees",
                EmployeesDao::mapRow);
    }
}
