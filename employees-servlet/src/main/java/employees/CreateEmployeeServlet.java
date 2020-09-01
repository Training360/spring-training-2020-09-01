package employees;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-employee.html")
public class CreateEmployeeServlet extends HttpServlet {

    private EmployeesService employeesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeesService = (EmployeesService) config.getServletContext().getAttribute("employeesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/create-employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        employeesService.createEmployee(name);
        // SOSE
//        req.setAttribute("employees", employeesService.listEmployees());
//        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req, resp);

        resp.sendRedirect("/employees.html");
    }
}
