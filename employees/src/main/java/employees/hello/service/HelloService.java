package employees.hello.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {


    private String welcomeMessage;

    public HelloService(@Value("${employees.welcome.message}") String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String sayHello() {
        return "Hello (Spring Boot Service) World " + welcomeMessage +
                LocalDateTime.now();
    }
}
