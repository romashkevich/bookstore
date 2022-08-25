package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Hello!<br></h1>");
        out.write("<h2>What you want seen?<br>" + "make your choice</h2>");
        out.write("<a href=" + "http://localhost:8010/bookstore/user" + ">" + "Users" + "<br></a>");
        out.write("<a href=" + "http://localhost:8010/bookstore/book" + ">" + "Books" + "<br></a>");
    }
}
