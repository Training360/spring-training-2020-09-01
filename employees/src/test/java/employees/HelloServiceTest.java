package employees;

import employees.hello.service.HelloService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        // Given
        var service = new HelloService("TEST");

        // When
        var message = service.sayHello();

        // Then
        assertThat(message).startsWith("Hello (Spring Boot Service) World TEST");
    }

    @Test
    void testSayHelloSort() {
        assertThat(new HelloService("TEST").sayHello())
                .startsWith("Hello (Spring Boot Service) World TEST");
    }


}
