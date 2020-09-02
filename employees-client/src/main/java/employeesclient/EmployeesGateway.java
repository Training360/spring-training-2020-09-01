package employeesclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeesGateway {

    public List<Employee> listEmployees() {
        var restTemplate = new RestTemplate();

        return restTemplate.exchange(
                "http://localhost:8080/api/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        ).getBody();
    }
}
