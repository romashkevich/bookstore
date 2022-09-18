package controller;

import books.service.ServiceBook;
import books.service.ServiceBookImpl;
import books.service.dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksCommand implements Command{
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();

    public String execute(HttpServletRequest req){
            try {
                List<BookDto> bookDtos = new ArrayList<>(SERVICE_BOOK_AppAll.getAllBooksDto());
                if (bookDtos==null){
                    throw new Exception();
                }
                req.setAttribute("books", bookDtos);
                return "jsp/books.jsp";
            } catch (Exception e) {
                return "jsp/error.jsp";
            }

    }
}