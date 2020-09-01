package employees;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServiceInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("employeesService",
                new EmployeesService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
