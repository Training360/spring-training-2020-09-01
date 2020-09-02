package employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateEmployeeCommand {

    @NotBlank
    @Schema(description = "name of the new employee",
    example = "Sample Joe")
    private String name;
}
