package employees;

import java.util.ArrayList;
import java.util.List;

public class EmployeesService {

    private List<Employee> employees =
            new ArrayList<>(List.of(
                    new Employee(1L, "John Doe"),
                    new Employee(2L, "Jack Doe")
            ));

    public List<Employee> listEmployees() {
        return employees;
    }
}