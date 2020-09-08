package employees.employees.service;

import employees.employees.repository.EmployeesDao;
import employees.infra.events.EmployeeHasCreatedEvent;
import employees.employees.dto.UpdateEmployeeCommand;
import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.dto.EmployeeDto;
import employees.employees.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    private ApplicationEventPublisher publisher;

    private EmployeesDao employeesDao;

    public EmployeesService(ModelMapper modelMapper, ApplicationEventPublisher publisher, EmployeesDao employeesDao) {
        this.modelMapper = modelMapper;
        this.publisher = publisher;
        this.employeesDao = employeesDao;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
//        return employees
//                .stream()
//                .map(e -> new EmployeeDto(e.getId(), e.getName()))
//                .collect(Collectors.toList());

        return employeesDao.listEmployees(prefix)
                .stream()
                .map(map())
                .collect(Collectors.toList());

//        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
//        return modelMapper.map(employees, targetListType);
    }

    private Function<Employee, EmployeeDto> map() {
        return e -> modelMapper.map(e, EmployeeDto.class);
    }

    private Predicate<Employee> emptyOrByPrefix(Optional<String> prefix) {
        return e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase());
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
//        var employee = new Employee(idGenerator.incrementAndGet(), command.getName());
//        employees.add(employee); // repository hívás, SQL-el db-be ment
//
//        // Dobunk egy eventet
//        publisher.publishEvent(new EmployeeHasCreatedEvent("Employee has created "
//            + command.getName()));
//
//        return modelMapper.map(employee, EmployeeDto.class);
        return null;

    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
//        var employee =
//                employees.stream()
//                .filter(e -> e.getId() == id)
//                .findAny()
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
//
//        employee.setName(command.getName());

//        return modelMapper.map(employee, EmployeeDto.class);
        return null;
    }

    public void deleteEmployee(long id) {
//        var employee =
//                employees.stream()
//                        .filter(e -> e.getId() == id)
//                        .findAny()
//                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
//        employees.remove(employee);
    }

    public EmployeeDto findEmployeeById(long id) {
//        return modelMapper.map(employees.stream()
//                .filter(e -> e.getId() == id)
//                .findAny()
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found")),
//                EmployeeDto.class);
        return null;
    }
}
