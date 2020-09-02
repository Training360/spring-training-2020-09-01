package employees;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping
    public List<EmployeeDto> listEmployees(
            @RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }

    @PostMapping
    public EmployeeDto createEmployee(
            @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("{id}")
    public EmployeeDto updateEmployee(
            @PathVariable("id") long id,
            @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

}
