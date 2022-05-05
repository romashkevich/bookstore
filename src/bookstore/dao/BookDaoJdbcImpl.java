package bookstore.dao;

import bookstore.DbConfigurator;
import bookstore.dao.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//dao - data access object / / объект доступа к данным

public class BookDaoJdbcImpl implements BookDao{
    public String jj = "j";
    public static final String SELECT_ISBN = "SELECT * FROM books WHERE isbn =? AND deleted = false";
    public static final String GET_ALL = "SELECT * FROM books WHERE deleted = false";
    public static final String SELECT_GET_ID = "SELECT * FROM books WHERE id =? AND deleted = false";
    public static final String CREATE_BOOK = "INSERT INTO books (isbn, title, author, pages, cover, price) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, author=?, pages=?, cover=?, price=? WHERE isbn=?";
    public static final String SELECT_BY_ID = "SELECT * FROM books WHERE id =? AND deleted = false";
    public static final String DELETE_BY_ID = "UPDATE books SET deleted = true WHERE id =?";
    public static final String GET_BY_ISBN = "SELECT * FROM books WHERE isbn =? AND deleted = false";

    public List<Book> getAllBooks()  {
        List<Book> books = new ArrayList<>();
        try{
            Statement statement = DbConfigurator.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPages(resultSet.getInt("pages"));
                book.setCover(resultSet.getString("cover"));
                book.setPrice(resultSet.getBigDecimal("price"));
                books.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookById(Long id) throws SQLException {
        Book book = null;
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(SELECT_GET_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPages(resultSet.getInt("pages"));
                book.setCover(resultSet.getString("cover"));
                book.setPrice(resultSet.getBigDecimal("price"));
                return book;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new SQLException("book is not found");
    }

    @Override
    public Book createBook(Book book) throws SQLException {
        PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(SELECT_ISBN);
        statement.setString(1, book.getIsbn());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            throw new SQLException("sorry, book with isbn in db");
        } else {
            try {
                PreparedStatement preparedStatement = DbConfigurator.getConnection().prepareStatement(CREATE_BOOK);
                preparedStatement.setString(1, book.getIsbn());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getPages());
                preparedStatement.setString(5, book.getCover());
                preparedStatement.setBigDecimal(6, book.getPrice());
                preparedStatement.executeUpdate();
                System.out.println("congratulation, book is create");
            } catch (SQLException e) {
                e.printStackTrace();
            }return book;
        }

    }

    @Override
    public Book updateBook(Book book) throws SQLException {
        PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(SELECT_ISBN);
        statement.setString(1, book.getIsbn());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            try {
                PreparedStatement preparedStatement = DbConfigurator.getConnection().prepareStatement(UPDATE_BOOK);
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getPages());
                preparedStatement.setString(4, book.getCover());
                preparedStatement.setBigDecimal(5, book.getPrice());
                preparedStatement.setString(6, book.getIsbn());
                preparedStatement.executeUpdate();
                System.out.println("congratulation, book is update");
                return book;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("book is not found");
            }
        return book;
    }

    @Override
    public boolean deleteBook(Long id) throws SQLException {
            boolean statusOperation = false;
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            if (statement.executeQuery().next()) {
                try {
                    PreparedStatement preparedStatement = DbConfigurator.getConnection().prepareStatement(DELETE_BY_ID);
                    preparedStatement.setLong(1,id);
                    preparedStatement.executeUpdate();
                    System.out.println("congratulation, book is deleted");
                    statusOperation = true;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("book is not found");
            }

        return statusOperation;
    }

    @Override
    public Book getBookByIsbn(String isbn) throws SQLException {
        boolean result = isbn.matches("\\d{4}[-]{1}\\d{4}");
        Book book = null;
        if (result) {
            try {
                PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_ISBN);
                statement.setString(1, isbn);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    book = new Book();
                    book.setId(resultSet.getLong("id"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPages(resultSet.getInt("pages"));
                    book.setCover(resultSet.getString("cover"));
                    book.setPrice(resultSet.getBigDecimal("price"));
                    return book;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("isbn is not correct");
        }
        return book;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        List<Book> books = getAllBooks();
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            String input = book.getAuthor().toLowerCase();// строка в которой ищут
            Pattern pattern = Pattern.compile(author.toLowerCase()+"\\s*\\w*"); // поиск совпадений
            Matcher matcher = pattern.matcher(input);
            while(matcher.find()){
                booksByAuthor.add(book);
                System.out.println(book);
            }
        }
        return booksByAuthor;
    }

    @Override
    public int countAllBooks() throws SQLException {
        Statement statement = DbConfigurator.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(id) FROM books WHERE deleted=false");
        int count=0;
        if (resultSet.next())
        count = resultSet.getInt("count");
        return count;
    }


}
