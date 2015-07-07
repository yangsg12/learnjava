package com.java.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Yang on 2015/5/17.
 */
public class HelloWorld {
    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main(String[] args) {
        logger.info("Hello World!");
    }
}
