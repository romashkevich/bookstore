package users.dbserviceusers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbConfiguratorUsers {
    private static Connection connection;
    private static final Logger loger = LogManager.getLogger("connect db");

    public static void initDbConnectionUsers() throws SQLException {  // метод подключения к базе данных
        String host = getUrl().get(0);
        String user = getUrl().get(1);
        String pass = getUrl().get(2);
        String url = getUrl().get(3);
        String local = getUrl().get(4);
        connection = DriverManager.getConnection(local);
        loger.info("data on connect db --> " + local);
    }

    public static Connection getConnectionUsers() throws SQLException {
        if (connection == null) {
            try {
            initDbConnectionUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static List<String> getUrl() {
        Properties properties = new Properties();
        List<String> inter = new ArrayList<String>();
        String local = "";
        String host = "";
        String user = "";
        String pass = "";
        String url = "";
        try {
            FileInputStream fis = new FileInputStream("src/resources/config.properties");
            properties.load(fis);
            host = properties.getProperty("db.host.remove.user.url");
            inter.add(host);
            user = properties.getProperty("db.host.remove.user.user");
            inter.add(user);
            pass = properties.getProperty("db.host.remove.user.password");
            inter.add(pass);
            url = properties.getProperty("db.host.remove.user.fullurl");
            inter.add(url);
            local = properties.getProperty("db.host.local.user");
            inter.add(local);
        } catch (IOException e) {
            loger.error("not connect with BD", e);
        }
        return inter;
    }
}
