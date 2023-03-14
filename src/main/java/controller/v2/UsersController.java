package controller.v2;

import books.service.ServiceBook;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import users.service.ServiceUser;
import users.service.ServiceUserImpl;
import users.service.dto.UserDto;

import javax.security.sasl.SaslServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    private ServiceUser serviceUser = new ServiceUserImpl();
    private String path = "/testHTML.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            List<UserDto> userDtos = serviceUser.getAllUserDto();
            if (!userDtos.isEmpty()) {
                for (UserDto ud : userDtos) {
                    out.print(ud);
                    out.print("<br></br>");
                    out.print("<h1>proverka</h1>");
                }
            } else throw new IOException();
        } catch (Exception e) {
            ServletContext servletContextError = getServletContext();
            RequestDispatcher requestDispatcher = servletContextError.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
