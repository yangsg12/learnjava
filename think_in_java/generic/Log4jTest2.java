package think_in_java.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Yang on 2015/4/3.
 */
public class Log4jTest2 {
    public static Logger logger = LogManager.getLogger("hello log");

    public static void main(String[] args) {
        logger.debug("will not show");
        logger.error("hello world");
    }
}
