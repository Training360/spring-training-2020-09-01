package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@Slf4j
public class EmployeesController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesController.class);

    private final EmployeesService employeesService;

    @GetMapping(produces =
            {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public EmployeesDto listEmployees(
            @RequestParam Optional<String> prefix) {
        log.debug("List employees: {}", prefix);

        return new EmployeesDto(employeesService.listEmployees(prefix));

//        return employeesService.listEmployees(prefix)
//                .stream()
//                .map(e ->new EmployeeDto(e.getId(), e.getName().toUpperCase())).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(
            @Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("{id}")
    public EmployeeDto updateEmployee(
            @PathVariable("id") long id,
            @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleIllegalArgumentException(IllegalArgumentException iae) {
        System.out.println("Error: " + iae.getMessage());

        var problem =
                Problem.builder()
                .withType(URI.create("employees/not-found"))
                .withTitle("Employee not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationException(MethodArgumentNotValidException e) {
        List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("employees/validation-error"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(e.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }


}
