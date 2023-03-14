package controller.v2;

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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/testHTML.html";
        String id = "id";
        String login = "login";
        String in = req.getParameter("name");
        String in2 = req.getParameter("name1");
        ServiceUser serviceUser = new ServiceUserImpl();
        PrintWriter out = resp.getWriter();

        try {
            if (in.equals(id)) {
                UserDto userDto = serviceUser.getUserDtoById(Long.parseLong(in2));
                if (userDto.getId() != null) {
                    out.print(userDto);

                } else throw new IOException();
            }
            if(in.equals(login)){
                UserDto userDto = serviceUser.getUserDtoByLolin(in2);
                if(userDto.getEmail()!=null){
                    out.print(userDto);
                } else throw new IOException();
            }


        } catch (Exception e) {
            ServletContext servletContextError = getServletContext();
            RequestDispatcher requestDispatcher = servletContextError.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
