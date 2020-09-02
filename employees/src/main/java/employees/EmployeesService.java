package employees;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
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

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployees() {
//        return employees
//                .stream()
//                .map(e -> new EmployeeDto(e.getId(), e.getName()))
//                .collect(Collectors.toList());

        return employees.stream().map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());

//        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
//        return modelMapper.map(employees, targetListType);
    }
}
