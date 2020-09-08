package employees.employees.repository;

import employees.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends
        JpaRepository<Employee, Long> {
}
