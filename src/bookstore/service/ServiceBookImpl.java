package bookstore.service;

import bookstore.dao.BookDao;
import bookstore.dao.BookDaoJdbcImpl;
import bookstore.dao.entity.Book;
import bookstore.service.dto.BookDto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceBookImpl implements ServiceBook {
    private final BookDao bookDao = new BookDaoJdbcImpl();


    @Override
    public List<BookDto> getAllBooksDto() {
        List<BookDto> bookDtos = bookDao.getAllBooks().stream()
                .map(entity -> toBookDto(entity))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public BookDto getBookDtoById(Long id) throws SQLException {
        Book book = bookDao.getBookById(id);
        BookDto bookDto = toBookDto(book);
        return bookDto;
    }

    private BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setCover(book.getCover());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    private Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(bookDto.getAuthor());
        book.setPages(bookDto.getPages());
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setCover(bookDto.getCover());
        return book;
    }

    @Override
    public BookDto createBookDto(BookDto bDto) throws Exception {
        BookDto bookDto;
        Book book = toBook(bDto);
        Book bookExist = bookDao.createBook(book);
        bookDto = toBookDto(bookExist);
        return bookDto;
    }

    @Override
    public BookDto updateBookDto(BookDto bDto) throws SQLException {
        BookDto bookDto;
        Book book = toBook(bDto);
        Book bookExist = bookDao.updateBook(book);
        bookDto = toBookDto(bookExist);
        return bookDto;
    }

    @Override
    public void deleteBookDto(Long id) throws SQLException {
        bookDao.deleteBook(id);
    }

    @Override
    public BookDto getBookDtoByIsbn(String isbn) throws SQLException {
        Book book = bookDao.getBookByIsbn(isbn);
        BookDto bookDto = toBookDto(book);
        return bookDto ;
    }

    @Override
    public List<BookDto> getBookDtoByAuthor(String author) {
        List<BookDto> bookDtos;
        List<Book> books = bookDao.getBookByAuthor(author);
        bookDtos = books.stream().map(this::toBookDto).collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public int countAllBookDto() throws SQLException {
        int count = bookDao.countAllBooks();
        return count;
    }

    @Override
    public BigDecimal priceBookDtoByAuthors(String author) {
        BigDecimal sumAll = null;
        double sum = 0;
        List<BookDto> books = getBookDtoByAuthor(author);
        if (!books.isEmpty()){
            for (BookDto bDto: books) {
                sum = sum + bDto.getPrice().doubleValue();
            }
            sumAll = new BigDecimal(sum);
            System.out.println(sumAll);
        } else{
            throw new RuntimeException("Author is not found");
        }


        return sumAll;
    }
}
