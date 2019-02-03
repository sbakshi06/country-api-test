package com.hellofresh.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.hellofresh.constants.TestConstant.*;

public class TestBase {

    public static Properties PROPS;
    public static Logger LOGGER = Logger.getLogger(TestBase.class.getName());

    public TestBase() {
        try {
            ClassLoader classLoader = TestBase.class.getClassLoader();
            PropertyConfigurator.configure(classLoader.getResource(LOG4J_PROPS_PATH));
            PROPS = new Properties();
            FileInputStream ip = new FileInputStream(classLoader.getResource(TEST_PROPS_PATH).getFile());
            PROPS.load(ip);
            LOGGER.info("Test properties loaded!");
        } catch (IOException e) {
            LOGGER.error("Failed to load Test Properties", e);
        }

    }
}