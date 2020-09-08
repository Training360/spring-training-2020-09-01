package employees.employees.dto;

import employees.employees.validator.Name;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateEmployeeCommand {

//    @NotBlank(message = "the name of the employee can not be blank")
    @Name(message = "the name of the employee is invalid", maxLength = 10)
    @Schema(description = "name of the new employee",
    example = "Sample Joe")
    private String name;
}
