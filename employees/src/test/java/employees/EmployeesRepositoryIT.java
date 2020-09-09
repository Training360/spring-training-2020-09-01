package employees;

import employees.employees.entity.Employee;
import employees.employees.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeesRepositoryIT {

    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    void testSaveAndList() {
        employeesRepository.save(new Employee("John Doe"));

        var employees = employeesRepository.findAll();
        assertEquals(1, employees.size());

        assertThat(employees)
                .extracting(Employee::getName)
                .containsExactly("John Doe");
    }

    @Test
    void testSaveAndFindById() {
        var employee = new Employee("John Doe");
        employeesRepository.save(employee);

        System.out.println("Id: " + employee.getId());

        var loadedEmployee = employeesRepository.findById(employee.getId());

        assertEquals("John Doe", loadedEmployee.get().getName());
    }
}
