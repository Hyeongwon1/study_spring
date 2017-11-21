package co.kr.ucs.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logTest {
    private static final Logger logger = LoggerFactory.getLogger(logTest.class);
 
    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}