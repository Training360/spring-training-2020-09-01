package it;

import employees.EmployeesApplication;
import employees.employees.controller.EmployeesController;
import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.repository.EmployeesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = EmployeesApplication.class)
public class EmployeesIT {

    @Autowired
    EmployeesController controller;

    @Autowired
    EmployeesRepository repository;

    @BeforeEach
    void init() {
        repository.deleteAll();
    }


    @Test
    void testCreateAndList() {
        var created = controller.createEmployee(new CreateEmployeeCommand("John Doe"));

//        var employees = controller.listEmployees(Optional.empty());
//        assertEquals(1, employees.getEmployees().size());

        var id = created.getId();
        var employee = controller.findEmployeeById(id);
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testCreateAndListJane() {
        controller.createEmployee(new CreateEmployeeCommand("Jane Doe"));

        var employees = controller.listEmployees(Optional.empty());

        assertEquals(1, employees.getEmployees().size());
    }
}
