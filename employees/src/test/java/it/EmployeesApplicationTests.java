package it;

import employees.EmployeesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = EmployeesApplication.class)
class EmployeesApplicationTests {

	@Test
	void contextLoads() {
	}

}
