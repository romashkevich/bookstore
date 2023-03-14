package controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import users.service.ServiceUser;
import users.service.ServiceUserImpl;
import users.service.dto.UserDto;

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


