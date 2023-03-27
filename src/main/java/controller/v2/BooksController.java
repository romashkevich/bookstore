package controller.v2;

import books.service.ServiceBook;
import books.service.ServiceBookImpl;
import books.service.dto.BookDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BooksController extends HttpServlet {
    private final ServiceBook serviceBook = new ServiceBookImpl();
    private String path = "/testHTML.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try {
            List<BookDto> bookDtos = (List<BookDto>) serviceBook.getAllBooksDto();
            if (!bookDtos.isEmpty()) {
                req.setAttribute("books",bookDtos);
                req.getRequestDispatcher("jsp/booksv.jsp").forward(req,resp);
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
