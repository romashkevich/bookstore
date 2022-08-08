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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookAll")
public class AppAll extends HttpServlet {
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();
    private static final Logger loggerAppAll = LogManager.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(200);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            List<BookDto> bookDtos = new ArrayList<>(SERVICE_BOOK_AppAll.getAllBooksDto());
            if (!bookDtos.isEmpty()) {
                for (BookDto bDto : bookDtos) {
                    out.write("<div>" + bDto + "</div>");
                }
            }
        }
        catch (Exception e) {
            out.write("<div>"+e+"</div>");
            }
    }
}
