package employeesclient;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EmployeesClientApplication implements CommandLineRunner {

	private EmployeesGateway employeesGateway;

	public static void main(String[] args) {
		SpringApplication.run(EmployeesClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello console");
		System.out.println(employeesGateway.listEmployees());
	}
}
