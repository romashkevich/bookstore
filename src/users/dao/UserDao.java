package users.dao;

import bookstore.dao.entity.Book;
import users.dao.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getAllUser();

    User getUserById(Long id) throws SQLException;

    User createUser(User user) throws Exception; // передаем книгу в базу данных и создаем строку с ее данными

    User updateUser(User user) throws SQLException;// замена инфы передаваемой книги и возврат измененных значений

    boolean deleteUser(Long id) throws SQLException;// передача в бд книги ее поиск и дальнейшая пометка удалена

    User getUserByEmail(String email) throws SQLException;

    List<User> getUsersByLastName(String firstName) throws SQLException;

    int countAllUsers() throws SQLException;

    User getUserByLogin (String login) throws SQLException;
}
