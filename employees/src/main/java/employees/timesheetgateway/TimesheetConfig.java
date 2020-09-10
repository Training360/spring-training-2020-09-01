package employees.timesheetgateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@ConfigurationProperties("timesheet")
@Data
@Validated
public class TimesheetConfig {

    @Size(min = 3)
    private String host;

    private int port;
}
