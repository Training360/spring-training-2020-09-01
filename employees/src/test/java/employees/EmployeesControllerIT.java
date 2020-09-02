package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class EmployeesControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeesService employeesService;

    @MockBean
    HelloService helloService;

    @Test
    void testListEmployees() throws Exception {
        when(employeesService.listEmployees(any()))
        .thenReturn(List.of(
                new EmployeeDto(1L, "John Doe"),
                new EmployeeDto(2L, "Jack Doe")
                ));

        mockMvc.perform(get("/api/employees"))
                .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[1].name",
                equalTo("Jack Doe")));
    }

    @Test
    void testCreateEmployee() throws Exception {
        mockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"Jack Doe\"}"))
        .andExpect(status().isCreated());

        verify(employeesService).createEmployee(argThat(command -> command.getName().equals("Jack Doe")));
    }
}
