package employees.infra.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeHasCreatedEvent {

    private String message;
}
