package employees.employees.repository;

import employees.employees.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeesRepository extends
        MongoRepository<Employee, String> {
}
