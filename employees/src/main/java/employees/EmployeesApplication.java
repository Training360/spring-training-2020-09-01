package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import employees.timesheetgateway.TimesheetConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import training.timesheet.gateway.Timesheet;

@SpringBootApplication
@EnableConfigurationProperties(TimesheetConfig.class)
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.findAndRegisterModules();
	}

//	@Bean
//	public EmployeesService employeesService() {
//		return new EmployeesService();
//	}

}
