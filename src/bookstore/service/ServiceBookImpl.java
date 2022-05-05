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

    private BookDto toBookDto(Book entity) {
        BookDto bookDto = new BookDto();
        bookDto.setId(entity.getId());
        bookDto.setAuthor(entity.getAuthor());
        bookDto.setIsbn(entity.getIsbn());
        bookDto.setTitle(entity.getTitle());
        bookDto.setCover(entity.getCover());
        bookDto.setPrice(entity.getPrice());
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
        Book bookExist = bookDao.getBookByIsbn(book.getIsbn());
        if (bookExist != null) {
            throw new RuntimeException("invalid: book in db");
        } else {
            bookDto = toBookDto(bookDao.createBook(book));
        }
        return bookDto;
    }

    @Override
    public BookDto updateBookDto(BookDto bDto) throws SQLException {
        BookDto bookDto = bDto;
        Book book = toBook(bDto);
        Book bookExist = bookDao.getBookByIsbn(book.getIsbn());
        if (bookExist != null && book.getId().equals(bDto.getId())) {
            bookDto = toBookDto(bookDao.updateBook(book));
        } else {
            throw new RuntimeException("invalid transfer data");
        }
        return bookDto;
    }

    @Override
    public void deleteBook(Long id) throws SQLException {
        Book book = bookDao.getBookById(id);
        BookDto bookDto = toBookDto(book);

        if (bookDao.deleteBook(id)) {
            System.out.println("book is delete");
        } else {
            System.out.println("book is not found");
        }
    }

    @Override
    public BookDto getBookDtoByIsbn(String isbn) throws SQLException {
        Book book = bookDao.getBookByIsbn(isbn);
        BookDto bookDto = new BookDto();
        if (book!= null) {
            bookDto = toBookDto(book);
        }else{
            throw new RuntimeException("book with ISBN is not found");
        }
       return bookDto ;
    }

    @Override
    public List<BookDto> getBookDtoByAuthor(String author) {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> books = bookDao.getBookByAuthor(author);
        if(!books.isEmpty()){
           bookDtos = books.stream().map(this::toBookDto).collect(Collectors.toList());
        }
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
