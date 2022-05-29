package bookstore.service;

import bookstore.service.dto.BookDto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ServiceBook {

    List<BookDto> getAllBooksDto() throws SQLException;

    BookDto getBookDtoById(Long id) throws SQLException;

    BookDto createBookDto(BookDto bookDto) throws Exception; // передаем книгу в базу данных и создаем строку с ее данными

    BookDto updateBookDto(BookDto bookDto) throws SQLException;// замена инфы передаваемой книги и возврат измененных значений

    void deleteBookDto(Long id) throws SQLException;// передача в бд книги ее поиск и дальнейшая пометка удалена

    BookDto getBookDtoByIsbn(String isbn) throws SQLException;

    List<BookDto> getBookDtoByAuthor(String author) throws SQLException;

    int countAllBookDto() throws SQLException;

    BigDecimal priceBookDtoByAuthors(String author) throws SQLException;
}
