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

@WebServlet("/userAll")
public class AppAll extends HttpServlet {
    private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl();
    private static final Logger root = LogManager.getRootLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            List<UserDto> userDtos = new ArrayList<>(SERVICE_USER_ALL.getAllUserDto());
            if(!userDtos.isEmpty()) {
                for(UserDto uDto : userDtos) {
                    out.write("<div>"+uDto+"</div>");
                }
            }
        } catch (Exception e) {
            out.write("<div>"+e+"</div>");
        }
    }
}

