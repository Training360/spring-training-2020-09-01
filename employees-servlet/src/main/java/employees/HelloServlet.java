package employees;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/hello.html")
public class HelloServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Create servlet");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy servlet");
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try(var out = resp.getWriter()) {
//            out.println(String.format("<html><body><h1>Hello (Servlet) World!</h1>" +
//                    "<p>%s</p></body></html>", LocalDateTime.now()));
//        }

        var message = "Hello (Model2) World!";
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }
}
