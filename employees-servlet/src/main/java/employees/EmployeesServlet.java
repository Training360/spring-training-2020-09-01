package employees;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees.html")
public class EmployeesServlet extends HttpServlet {

    private EmployeesService employeesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeesService = (EmployeesService) config.getServletContext().getAttribute("employeesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var employees = employeesService.listEmployees();
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp")
                .forward(req, resp);
    }
}
