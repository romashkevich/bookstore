package bookstore;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbConfigurator {
    private static Connection connection;
    //private static String url = "jdbc:postgresql://127.0.0.1:5432/bh";

    public static void initDbConnection() throws SQLException {  // метод подключения к базе данных
        String host = getUrl().get(0);
        String user = getUrl().get(1);
        String pass = getUrl().get(2);
        String url = getUrl().get(3);
        String local = getUrl().get(4);
        connection = DriverManager.getConnection(host,user,pass);
    }

    public static Connection getConnection() {
        if(connection == null){
            try{
                initDbConnection();
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    public static List<String> getUrl(){
        Properties properties = new Properties();
        List<String>inter = new ArrayList<String>();
        String local = "";
        String host = "";
        String user="";
        String pass = "";
        String url="";
        try{
        InputStream fis = DbConfigurator.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(fis);
        host = properties.getProperty("db.host.remove.url");
        inter.add(host);
        user = properties.getProperty("db.host.remove.user");
        inter.add(user);
        pass = properties.getProperty("db.host.remove.password");
        inter.add(pass);
        url = properties.getProperty("db.host.remove.fullurl");
        inter.add(url);
        local = properties.getProperty("db.host.local");
        inter.add(local);
        } catch (IOException e){
            System.out.println("");
        }
        return inter;
    }
}
