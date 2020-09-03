package employees;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@AllArgsConstructor
public class EmployeesWebsocketClientApplication implements CommandLineRunner {

	private EmployeesGateway employeesGateway;

	public static void main(String[] args) {
		SpringApplication.run(EmployeesWebsocketClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeesGateway.subscribe();

		var scanner = new Scanner(System.in);
		scanner.nextLine();

	}
}
