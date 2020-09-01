package employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        // Given
        var service = new HelloService();

        // When
        var message = service.sayHello();

        // Then
        assertThat(message).startsWith("Hello (Spring Boot");
    }

    @Test
    void testSayHelloSort() {
        assertThat(new HelloService().sayHello()).startsWith("Hello");
    }


}
