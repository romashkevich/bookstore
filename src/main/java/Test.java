import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
        properties.load(fis);
        String host = properties.getProperty("db.host.local.books");
        System.out.println(host);
    }
}
