package users.controllerusers;
import users.dao.UserDao;
import users.dao.UserDaoJdbcImpl;
import users.dao.entity.Adress;
import users.dao.entity.Sex;
import users.dao.entity.User;
import users.dbserviceusers.DbConfiguratorUsers;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class App {
    private static final UserDao USER_DAO = new UserDaoJdbcImpl();
    public static final String INSERT_ADRESS = "INSERT INTO adress (country, city, street, strNum, apart) VALUES(?,?,?,?,?)";

    public static void main(String[] args) throws Exception {

        //USER_DAO.getAllUser().forEach(System.out::println);

        Adress adress = new Adress();
        adress.setCountry("USA");
        adress.setCity("OHIO");
        adress.setStreet("Jefferson str.");
        adress.setStrNum(12);
        adress.setApart(1408);

        User user = new User();
        user.setLogin("costa");
        user.setEmail("xkostax@gmail.com");
        user.setPassword("7654321");
        user.setFirstName("mike");
        user.setLastName("Jonson");
        user.setAdress(adress);
        user.setSex(Sex.MAN);
        user.setTelNum("+375(29)3214567");




        //USER_DAO.createUser(user);
        USER_DAO.updateUser(user);

        //System.out.println(sexId(user));

    }


    }

