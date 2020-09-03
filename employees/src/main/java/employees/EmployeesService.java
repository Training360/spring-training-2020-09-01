package employees;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private AtomicLong idGenerator =
            new AtomicLong();

    private List<Employee> employees =
            Collections.synchronizedList(new ArrayList<>(
            List.of(
            new Employee(idGenerator.incrementAndGet(), "John Doe"),
            new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));

    private ModelMapper modelMapper;

    private ApplicationEventPublisher publisher;

    public EmployeesService(ModelMapper modelMapper, ApplicationEventPublisher publisher) {
        this.modelMapper = modelMapper;
        this.publisher = publisher;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
//        return employees
//                .stream()
//                .map(e -> new EmployeeDto(e.getId(), e.getName()))
//                .collect(Collectors.toList());

        return employees.stream()
                .filter(emptyOrByPrefix(prefix))
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
        var employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee); // repository hívás, SQL-el db-be ment

        // Dobunk egy eventet
        publisher.publishEvent(new EmployeeHasCreatedEvent("Employee has created "
            + command.getName()));

        return modelMapper.map(employee, EmployeeDto.class);

    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        var employee =
                employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        employee.setName(command.getName());

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        var employee =
                employees.stream()
                        .filter(e -> e.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employees.remove(employee);
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found")),
                EmployeeDto.class);
    }
}
