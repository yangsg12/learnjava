package think_in_java.generic;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Yang on 2015/4/3.
 */
public class Log4jTest3 {
    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    public static void main(String[] args)
    {
        add(1, 2);
    }

    public static int add(int a , int b)
    {
        logger.entry(a, b);
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");
        logger.printf(Level.TRACE, "%d+%d=%d", a, b, a + b);
        logger.exit(a + b);
        return a + b;
    }
}
