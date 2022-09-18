package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorCommand implements Command{
    @Override
    public String execute(HttpServletRequest req){
        req.setAttribute("message", "ooops");
        return "jsp/error.jsp";
    }
}
