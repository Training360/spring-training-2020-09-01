package it;

import employees.EmployeesApplication;
import employees.eventstoregateway.EventStoreGateway;
import employees.hello.controller.HelloController;
import employees.timesheetgateway.TimesheetGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = EmployeesApplication.class)
public class HelloIT {

    @MockBean
    EventStoreGateway eventStoreGateway;

    @Autowired
    HelloController helloController;

    @MockBean
    TimesheetGateway timesheetGateway;

    @Test
    void testSayHello() {
        var message = helloController.sayHello();
        assertThat(message).startsWith("HELLO");
    }
}
