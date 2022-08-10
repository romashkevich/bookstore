package users.usercontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.service.ServiceUser;
import users.service.ServiceUserImpl;
import users.service.dto.UserDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class AppAll extends HttpServlet {
    private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl();
    private static final Logger root = LogManager.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (req.getParameter("id") == null) {
            try {
                List<UserDto> userDtos = new ArrayList<>(SERVICE_USER_ALL.getAllUserDto());
                if (!userDtos.isEmpty()) {
                    out.write("<img src=" + "images/emblema_13.jpg" + ">");
                    out.write("<h1>Users</h1>");
                    for (UserDto uDto : userDtos) {
                        out.write("<a href=" + "http://localhost:8010/bookstore/user?id=" + uDto.getId() + ">" + uDto.getLogin() + "<br></a>");
                    }
                }
            } catch (Exception e) {
                resp.sendError(404, "not connect with db");
            }
        }
        if (!(req.getParameter("id") == null)) {
            try {
                Long idValue = Long.parseLong(req.getParameter("id"));
                long count = (long) SERVICE_USER_ALL.countAllUsersDto();
                if (idValue >= 0 && idValue <= count) {
                    UserDto userDto = SERVICE_USER_ALL.getUserDtoById(idValue);
                    out.write("<h1>User<br></h1>");
                    out.write("<div>" + userDto + "</div>");
                } else {
                    throw new SQLException();
                }
            } catch (NumberFormatException | SQLException e) {
                resp.sendError(404, "this id is not correct or not connect with DB");

            }
        }

    }
}

