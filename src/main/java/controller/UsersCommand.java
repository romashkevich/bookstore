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

public class UsersCommand implements Command{

        private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl();

        public String execute(HttpServletRequest req){
                try {
                    List<UserDto> userDtos = new ArrayList<>(SERVICE_USER_ALL.getAllUserDto());
                    if (userDtos==null) {
                        throw new Exception();
                    }
                    req.setAttribute("users",userDtos);
                    return "jsp/users.jsp";

                } catch (Exception e) {
                    return "jsp/error.jsp";
                }

        }
    }


