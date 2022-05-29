package bookstore.controllerbooks;
import bookstore.dao.BookDaoJdbcImpl;
import bookstore.dao.entity.Book;
import bookstore.dao.BookDao;
import bookstore.service.ServiceBook;
import bookstore.service.ServiceBookImpl;
import bookstore.service.dto.BookDto;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class App {
    private static final ServiceBook SERVICE_BOOK = new ServiceBookImpl();

    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String[] args){
        boolean process = true;

        while (process) {
            System.out.println("conditon : all, id, create, update, delete, isbn, author, count or exit (for exit program)");
            Scanner in = new Scanner(System.in);
            String input = in.next().toLowerCase().trim();
            switch (input) {
                case "all":
                    System.out.println("list books: ");
                    try {
                        SERVICE_BOOK.getAllBooksDto().forEach(System.out::println);
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "id":
                    System.out.println("please input book id");
                    Scanner in1 = new Scanner(System.in);
                    Long id = in1.nextLong();
                    System.out.println("result");
                    try {
                        System.out.println(SERVICE_BOOK.getBookDtoById(id));
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "create":
                    try {
                        BookDto book = getBookDto();
                        SERVICE_BOOK.createBookDto(book);
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "update":
                    System.out.println("please input book isbn for update");
                    Scanner bookIsbn = new Scanner(System.in);
                    try {
                        BookDto bookDto4Update = SERVICE_BOOK.getBookDtoByIsbn(bookIsbn.nextLine());
                        SERVICE_BOOK.updateBookDto(setBookDto(bookDto4Update));
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "delete":
                    System.out.println("please input book id for delete");
                    try {
                        Scanner in2 = new Scanner(System.in);
                        SERVICE_BOOK.deleteBookDto(in2.nextLong());
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "isbn":
                    System.out.println("please input isbn book(for example: 1234-1234)");
                    Scanner in3 = new Scanner(System.in);
                    try {
                        BookDto book1 = SERVICE_BOOK.getBookDtoByIsbn(in3.nextLine());
                        System.out.println(book1);
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "author":
                    System.out.println("please input book author when you find");
                    try {
                        Scanner in4 = new Scanner(System.in);
                        SERVICE_BOOK.getBookDtoByAuthor(in4.next());
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "count":
                    System.out.print("count books in db: ");
                    try {
                        System.out.println(SERVICE_BOOK.countAllBookDto());
                    } catch (Exception e) {
                        logger.log(Level.ERROR,e);
                    }
                    break;
                case "exit":
                    System.out.println("program is over");
                    process = false;
                    break;

                default:
                    System.out.println("invalid command, try again");
            }
        }

    }
    private static Book getBook() {
        Book book = new Book();
        System.out.println("input book data");

        System.out.println("isbn(for example: 1234-1234 )");
        Scanner isbn = new Scanner(System.in);
        book.setIsbn(isbn.nextLine());

        System.out.println("title");
        Scanner title= new Scanner(System.in);
        book.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book.setPrice(price.nextBigDecimal());
        return book;
    }
    private static Book setBookDto(Book book){
        Book book1 = book;
        System.out.println("input book data");

        System.out.println("title");
        Scanner title= new Scanner(System.in);
        book1.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book1.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book1.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book1.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book1.setPrice(price.nextBigDecimal());
        return book1;
    }

    private static BookDto getBookDto() {
        BookDto book = new BookDto();
        System.out.println("input book data");

        System.out.println("isbn(for example: 1234-1234 )");
        Scanner isbn = new Scanner(System.in);
        book.setIsbn(isbn.nextLine());

        System.out.println("title");
        Scanner title= new Scanner(System.in);
        book.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book.setPrice(price.nextBigDecimal());
        return book;
    }
    private static BookDto setBookDto(BookDto book){
        BookDto book1 = book;
        System.out.println("input book data");

        System.out.println("title");
        Scanner title= new Scanner(System.in);
        book1.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book1.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book1.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book1.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book1.setPrice(price.nextBigDecimal());
        return book1;
    }
}



