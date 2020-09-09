package employees.employees.service;

import employees.employees.dto.UpdateEmployeeCommand;
import employees.employees.dto.CreateEmployeeCommand;
import employees.employees.dto.EmployeeDto;
import employees.employees.entity.Employee;
import employees.employees.repository.EmployeesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    private EmployeesRepository employeesRepository;

    public EmployeesService(ModelMapper modelMapper, EmployeesRepository employeesRepository) {
        this.modelMapper = modelMapper;
        this.employeesRepository = employeesRepository;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return employeesRepository.
                findAll()
                .stream()
                .map(map())
                .collect(Collectors.toList());
    }

    private Function<Employee, EmployeeDto> map() {
        return e -> modelMapper.map(e, EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = modelMapper.map(command, Employee.class);
        employeesRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);

    }

    @Transactional
    public EmployeeDto updateEmployee(String id, UpdateEmployeeCommand command) {
        var employee = employeesRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(String id) {
        employeesRepository.deleteById(id);

    }

    public EmployeeDto findEmployeeById(String id) {
        var employee = employeesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
