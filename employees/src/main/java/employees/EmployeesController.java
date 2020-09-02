package employees;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping("/api/employees")
    @ResponseBody
    public List<EmployeeDto> listEmployees(
            @RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }
}
