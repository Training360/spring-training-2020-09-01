package employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @Mock
    HelloService service;

    @InjectMocks
    HelloController controller;

    @Test
    void testSayHello() {
//        var service = mock(HelloService.class);
        when(service.sayHello()).thenReturn("Test");

//        var controller = new HelloController(service);

        var message = controller.sayHello();

        assertThat(message).isEqualTo("TEST");
    }
}
