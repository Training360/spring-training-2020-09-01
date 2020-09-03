package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDto {

    private List<EmployeeDto> employees;
}
