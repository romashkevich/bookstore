package controller;

import jakarta.servlet.ServletException;
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

public class UserCommand implements Command{
        private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl();

        public String execute(HttpServletRequest req){
                try {
                    Long idValue = Long.parseLong(req.getParameter("id"));
                    long count = (long) SERVICE_USER_ALL.countAllUsersDto();
                    if (idValue >= 0 && idValue <= count) {
                        UserDto userDto = SERVICE_USER_ALL.getUserDtoById(idValue);
                        req.setAttribute("user",userDto);
                        return "jsp/user.jsp";
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    return "jsp/error.jsp";
                }

        }
    }


