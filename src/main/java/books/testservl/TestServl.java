package books.testservl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class TestServl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/testHTML.html";
        String in = req.getParameter("name");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (in != null && in.isEmpty()) {
            out.print("not null but is empty");
        } else {
            out.print("<h1>" + in + "</h1>");
        }

        if (in == null) {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        } else {
            System.out.println(in);
            out.println("<h1>" + "yoo is bitch" + "</h1>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
