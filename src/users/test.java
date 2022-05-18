package users;

import users.dao.entity.Sex;
import users.dbserviceusers.DbConfiguratorUsers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test {
    public static final String SELECT_ADRESS = "SELECT count(*) FROM adress";

    public static void main(String[] args) throws SQLException {
        int adressId;

        Statement statement = DbConfiguratorUsers.getConnection().createStatement();
        ResultSet resultSet1 = statement.executeQuery(SELECT_ADRESS);
        if (resultSet1.next()) {
            adressId = resultSet1.getInt("count");
            System.out.println(adressId);
        }
    }

}
