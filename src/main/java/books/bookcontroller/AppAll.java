package books.bookcontroller;

import books.service.ServiceBook;
import books.service.ServiceBookImpl;
import books.service.dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class AppAll extends HttpServlet {
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();
    private static final Logger loggerAppAll = LogManager.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(200);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (req.getParameter("id") == null) {
            try {
                List<BookDto> bookDtos = new ArrayList<>(SERVICE_BOOK_AppAll.getAllBooksDto());
                if (!bookDtos.isEmpty()) {
                    out.write("<img src=" + "images/emblema_13.jpg" + ">");
                    out.write("<h1>Books</h1>");
                    for (BookDto bDto : bookDtos) {
                        out.write("<a href=" + "http://localhost:8010/bookstore/book?id=" + bDto.getId() + ">" + bDto.getTitle() + "<br></a>");
                    }
                }
            } catch (Exception e) {
                resp.sendError(404, "not connect with db");

            }
        }

        if (!(req.getParameter("id") == null)) {
            try {
                Long idValue = Long.parseLong(req.getParameter("id"));
                long count = (long)SERVICE_BOOK_AppAll.countAllBookDto();
                if (idValue >= 0 && idValue <= count) {
                    BookDto bookDto = SERVICE_BOOK_AppAll.getBookDtoById(idValue);
                    out.write("<h1>Book</h1>");
                    out.write("");
                    out.write("<div>" + bookDto + "</div>");
                } else {
                    throw new SQLException();
                }

            } catch (NumberFormatException | SQLException e) {
                resp.sendError(404, "this id is not correct or not connect with DB");
            }

        }


    }

}
