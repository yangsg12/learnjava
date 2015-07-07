package think_in_java.generic;


import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Yang on 2015/4/3.
 */


public class Log4jTest {
    private static final Logger logger = LogManager.getLogManager().getLogger("log");

    public static void main(String[] args) {
        logger.info("hello world");
    }
}


