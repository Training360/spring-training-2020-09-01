package employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateEmployeeCommand {

    @Schema(description = "name of the new employee",
    example = "Sample Joe")
    private String name;
}
