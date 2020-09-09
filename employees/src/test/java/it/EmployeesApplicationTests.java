package it;

import employees.EmployeesApplication;
import employees.gateway.EventStoreGateway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = EmployeesApplication.class)
class EmployeesApplicationTests {

	@MockBean
	EventStoreGateway eventStoreGateway;

	@Test
	void contextLoads() {
	}

}
