package it;

import employees.EmployeesApplication;
import employees.eventstoregateway.EventStoreGateway;
import employees.timesheetgateway.TimesheetGateway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = EmployeesApplication.class)
class EmployeesApplicationTests {

	@MockBean
	EventStoreGateway eventStoreGateway;

	@MockBean
	TimesheetGateway timesheetGateway;

	@Test
	void contextLoads() {
	}

}
