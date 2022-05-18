package users.dao;

import users.dao.entity.Adress;
import users.dao.entity.Sex;
import users.dao.entity.User;
import users.dbserviceusers.DbConfiguratorUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao{
    public static final String SELECT_USERS = "SELECT * FROM users WHERE deleted = false";
    public static final String SELECT_SEX = "SELECT * FROM sex WHERE  sex_id = ? ";
    public static final String SELECT_ADRESS_ID = "SELECT * FROM adress WHERE adress_id = ?";
    public static final String SELECT_ADRESS_ID_2 = "SELECT adress_id FROM users WHERE login = ?";
    public static final String SELECT_MAX_ADRESS_ID = "SELECT MAX(adress_id) FROM adress";
    public static final String SELECT_ID = "SELECT * FROM users WHERE id = ? AND deleted = false";
    public static final String SELECT_EMAIL = "SELECT * FROM users WHERE email = ? AND deleted = false";
    public static final String UPDATE_USER =
            "UPDATE users SET pass = ?, firstname=?, lastname=?, telnumber=?, sex_id=? WHERE login=?";
    public static final String UPDATE_ADRESS =
            "UPDATE adress SET country = ?, city=?, street=?, strNum=?, apart=? WHERE adress_id=?";
    public static final String SELECT_LOGIN = "SELECT * FROM users WHERE login = ? AND deleted = false";
    public static final String INSERT_ADRESS = "INSERT INTO adress (country, city, street, strNum, apart) VALUES(?,?,?,?,?)";
    public static final String CREATE_USER =
            "INSERT INTO users (login, email, pass, firstname, lastname, adress_id, telnumber, sex_id) VALUES(?,?,?,?,?,?,?,?)";
    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        int sexId = 0;
        long adressId = 0;
        try{
            Statement statement = DbConfiguratorUsers.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_USERS);
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("pass"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setTelNum(resultSet.getString("telnumber"));
                sexId = resultSet.getInt("sex_id");
                user.setSex(getSexUser(sexId));
                adressId = resultSet.getLong("adress_id");
                user.setAdress(getAdressUser(adressId));
                users.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }
    private Sex getSexUser(int sexId) throws SQLException {
        String sexName = "";
        Sex sex;
        PreparedStatement statement = DbConfiguratorUsers.getConnection().prepareStatement(SELECT_SEX);
        statement.setLong(1, sexId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            sexName = resultSet.getString("sex_name");
        }
        switch (sexName.toUpperCase()) {
            case("MAN"):
                sex = Sex.MAN;
                break;
            default:
                sex = Sex.WOMAN;
                break;
        }
        return sex;
    }
    private Adress getAdressUser(long adressId) throws SQLException {
        Adress adress = new Adress();
        PreparedStatement statement = DbConfiguratorUsers.getConnection().prepareStatement(SELECT_ADRESS_ID);
        statement.setLong(1, adressId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            adress.setCountry(resultSet.getString("country"));
            adress.setCity(resultSet.getString("city"));
            adress.setStreet(resultSet.getString("street"));
            adress.setStrNum(resultSet.getInt("strNum"));
            adress.setApart(resultSet.getInt("apart"));
        }
        return adress;
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        int sexId = 0;
        int adressId = 0;
        User user = new User();
        PreparedStatement statement = DbConfiguratorUsers.getConnection().prepareStatement(SELECT_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            user.setId(resultSet.getLong("id"));
            System.out.println(user.getId());
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("pass"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setTelNum(resultSet.getString("telnumber"));
            sexId = resultSet.getInt("sex_id");
            user.setSex(getSexUser(sexId));
            adressId = resultSet.getInt("adress_id");
            user.setAdress(getAdressUser(adressId));
        }
        return user;
    }

    @Override
    public User createUser(User user) throws SQLException {
        int adressId = 0;
        PreparedStatement preparedStatement = DbConfiguratorUsers.getConnection().prepareStatement(SELECT_EMAIL);
        preparedStatement.setString(1, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("sorry, user with email in db or user deleted");
        } else {
            // Insert SQL adress
            extracted(user);
            // select SQL adress return max adress_id
            Statement statement = DbConfiguratorUsers.getConnection().createStatement();
            ResultSet resultSet1 = statement.executeQuery(SELECT_MAX_ADRESS_ID);
            if(resultSet1.next()){
                adressId = resultSet1.getInt("max");
                System.out.println(adressId);
            }
            // insert new user
            PreparedStatement preparedStatement1 = DbConfiguratorUsers.getConnection().prepareStatement(CREATE_USER);
            preparedStatement1.setString(1, user.getLogin());
            preparedStatement1.setString(3, user.getPassword());
            preparedStatement1.setString(2, user.getEmail());
            preparedStatement1.setString(4, user.getFirstName());
            preparedStatement1.setString(5, user.getLastName());
            preparedStatement1.setInt(6, adressId);
            preparedStatement1.setString(7, user.getTelNum());
            preparedStatement1.setInt(8, sexId(user));
            preparedStatement1.execute();
            System.out.println("congratulation, user is create");
        }
        return user;
    }
    private void extracted(User user) throws SQLException {
        PreparedStatement preparedStatementAdress = DbConfiguratorUsers.getConnection().prepareStatement(INSERT_ADRESS);
        preparedStatementAdress.setString(1, user.getAdress().getCountry());
        preparedStatementAdress.setString(2, user.getAdress().getCity());
        preparedStatementAdress.setString(3, user.getAdress().getStreet());
        preparedStatementAdress.setInt(4, user.getAdress().getStrNum());
        preparedStatementAdress.setInt(5, user.getAdress().getApart());
        preparedStatementAdress.execute();
    }
    private int sexId(User user) {
        int sexId = user.getSex() == "MAN" ? 1 : 2;
        return sexId;
    }

    @Override
    public User updateUser(User user) throws SQLException {
        int adressId=0;
        PreparedStatement preparedStatement = DbConfiguratorUsers.getConnection().prepareStatement(SELECT_LOGIN);
        preparedStatement.setString(1, user.getLogin());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            PreparedStatement preparedStatement2 = DbConfiguratorUsers.getConnection().prepareStatement(UPDATE_USER);
            preparedStatement2.setString(1, user.getPassword());
            preparedStatement2.setString(2, user.getFirstName());
            preparedStatement2.setString(3, user.getLastName());
            preparedStatement2.setString(4, user.getTelNum());
            preparedStatement2.setInt(5, sexId(user));
            preparedStatement2.setString(6, user.getLogin());
            preparedStatement2.executeUpdate();

            Statement statement = DbConfiguratorUsers.getConnection().createStatement();
            ResultSet resultSet1 = statement.executeQuery(SELECT_ADRESS_ID_2);
            if(resultSet1.next()) {
                adressId = resultSet1.getInt("adress_id");
            }
            PreparedStatement preparedStatementAdress = DbConfiguratorUsers.getConnection().prepareStatement(UPDATE_ADRESS);
            preparedStatementAdress.setString(1, user.getAdress().getCountry());
            preparedStatementAdress.setString(2, user.getAdress().getCity());
            preparedStatementAdress.setString(3, user.getAdress().getStreet());
            preparedStatementAdress.setInt(4, user.getAdress().getStrNum());
            preparedStatementAdress.setInt(5, user.getAdress().getApart());
            preparedStatementAdress.setInt(6, adressId);
            preparedStatementAdress.execute();
        } else {
            System.out.println("user is not found or deleted");
        }

        return user;
    }

    @Override
    public boolean deleteUser(Long id) throws SQLException {
        return false;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public List<User> getUsersByLastName(String firstName) {
        return null;
    }

    @Override
    public int countAllUsers() throws SQLException {
        return 0;
    }
}
