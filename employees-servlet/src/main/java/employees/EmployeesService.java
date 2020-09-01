package employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeesService {

    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees =
            Collections.synchronizedList(new ArrayList<>(List.of(
                    new Employee(idGenerator.incrementAndGet(), "John Doe"),
                    new Employee(idGenerator.incrementAndGet(), "Jack Doe")
            )));

    public List<Employee> listEmployees() {
        return employees;
    }

    public void createEmployee(String name) {
        employees.add(new Employee(idGenerator.incrementAndGet(), name));
    }
}
