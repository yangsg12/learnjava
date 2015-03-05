package book_manage.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yang on 2015/2/4.
 */
public class PropertiesUtil {
    private static Properties properties = new Properties();
    private static String CONFIG = "/jdbc.properties";
    private static InputStream is = PropertiesUtil.class.getResourceAsStream(CONFIG);
    public static String JDBC_DRIVER;
    public static String JDBC_URL;
    public static String JDBC_USER;
    public static String JDBC_PASS;
    static {
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = properties.getProperty("jdbc.driver");
        JDBC_URL = properties.getProperty("jdbc.url");
        JDBC_USER = properties.getProperty("jdbc.user");
        JDBC_PASS = properties.getProperty("jdbc.pass");
    }
}
