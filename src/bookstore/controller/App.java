package bookstore.controller;

import bookstore.dao.BookDaoJdbcImpl;
import bookstore.dao.entity.Book;
import bookstore.dao.BookDao;

import java.util.Scanner;

public class App {
    private static final BookDao BOOK_DAO = new BookDaoJdbcImpl();

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        boolean process = true;
        while(process) {
            System.out.println("conditon : all, id, create, update, delete, isbn, author, count or exit (for exit program)");

            String input = in.next().toLowerCase().trim();
            switch (input) {
                case "all":
                    System.out.println("list books: ");
                    BOOK_DAO.getAllBooks().forEach(System.out::println);
                    break;

                case "id":
                    System.out.println("please input book id");
                    Scanner in1 = new Scanner(System.in);
                    Long id = in.nextLong();
                    System.out.println("result");
                    System.out.println(BOOK_DAO.getBookById(id));
                    break;

                case "create":
                    Book book = getBook();
                    BOOK_DAO.createBook(book);
                break;

                case "update":
                    System.out.println("please input book isbn for update");
                    Scanner bookIsbn = new Scanner(System.in);
                    Book book4Update = BOOK_DAO.getBookByIsbn(bookIsbn.nextLine());
                    BOOK_DAO.updateBook(setBook(book4Update));
                    break;

                case "delete":
                    System.out.println("please input book id for delete");
                    Scanner in2 = new Scanner(System.in);
                    BOOK_DAO.deleteBook(in.nextLong());
                    break;

                case "isbn":
                    System.out.println("please input isbn book(for example: 1234-1234)");
                    Scanner in3 = new Scanner(System.in);
                    Book book1 = BOOK_DAO.getBookByIsbn(in3.nextLine());
                    System.out.println(book1);
                    break;

                case "author":
                    System.out.println("please input book author when you find");
                    Scanner in4 = new Scanner(System.in);
                    BOOK_DAO.getBookByAuthor(in.next());
                    break;

                case "count":
                    System.out.print("count books in db: ");
                    System.out.println(BOOK_DAO.countAllBooks());
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
    private static Book setBook(Book book){
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
}

