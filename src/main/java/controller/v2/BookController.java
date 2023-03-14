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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class BookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = "/testHTML.html";
        String id = "id";
        String isbn = "isbn";
        String author = "author";
        String in = req.getParameter("name");
        String in2 = req.getParameter("name1");

        resp.setContentType("text/html;charset=UTF-8");
        ServiceBook serviceBook = new ServiceBookImpl();
        PrintWriter out = resp.getWriter();
        out.print(in);
        out.print(in2);

        try {

            if (in.equals(id)) {
                BookDto bookDto = serviceBook.getBookDtoById(Long.parseLong(in2));
                if (bookDto.getId() != null) {
                    out.print(bookDto);
                } else {
                    throw new IOException();
                }
            }
            if (in.equals(isbn)) {
                BookDto bookDto = serviceBook.getBookDtoByIsbn(in2);
                if (bookDto.getId() != null) {
                    out.print(bookDto);
                } else {
                    throw new IOException();
                }
            }
            if (in.equals(author)) {
                List<BookDto> bookDtos = serviceBook.getBookDtoByAuthor(in2);
                out.print("<h1>Book</h1>");
                if (!bookDtos.isEmpty()) {
                    for (BookDto book : bookDtos) {
                        out.print("<div>" + book + "</div>");
                    }
                }else {
                    throw new IOException();
                }
            }

        } catch (Exception e) {
            ServletContext servletContextError = getServletContext();
            RequestDispatcher requestDispatcher = servletContextError.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        }
    }
}
