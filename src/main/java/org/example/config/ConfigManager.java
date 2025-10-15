package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    public static final String BASE_API_URL = "base.api.url";
    public static final String BASE_UI_URL = "base.ui.url";
    public static final String PW_TIMEOUT = "playwright.timeout.ms";
    public static final String PAGE_TIMEOUT = "page.timeout.ms";
    public static final String API_TIMEOUT = "api.timeout.ms";
    public static final String BROWSER = "browser";
    public static final String HEADLESS = "headless";

    //Singleton
    private static final class Holder {
        static final ConfigManager INSTANCE = new ConfigManager();

    }

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    private static Properties props = new Properties();//storing of all the key-values from the .properties file

    private ConfigManager() {
        props = loadFromClassPathOrThrow("application.properties");

    }

    //returns Properties object that contains all key-value pairs inside ".properties" file
    private static Properties loadFromClassPathOrThrow(String resourceName) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null)
            cl = ConfigManager.class.getClassLoader();//in case if there is no default classloader - get the one from ConfigManager
        try (InputStream in = cl.getResourceAsStream(resourceName)) {
            if (in == null) {//in case if the file isn't found
                throw new IllegalStateException("Missing " + resourceName + " on class path");
            }
            //Properties p = new Properties();
            props.load(in);//read all the input lines from the stream
            return props;//returns an object with all the properties read
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + resourceName + " " + e.getMessage(), e);

        }
    }

    //getters
    public String baseApiUrl() {
        return props.getProperty(BASE_API_URL).trim();
    }

    public String baseUiUrl() {
        return props.getProperty(BASE_UI_URL).trim();
    }

    public String playwrightTimeoutMs() {
        return props.getProperty(PW_TIMEOUT).trim();
    }

    public String pageTimeoutMs() {
        return props.getProperty(PAGE_TIMEOUT).trim();
    }

    public String apiTimeoutMs() {
        return props.getProperty(API_TIMEOUT).trim();
    }


    public String browser() {
        return props.getProperty(BROWSER).trim().toLowerCase();
    }

    public boolean headless() {
        String value = props.getProperty(HEADLESS);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing value for " + HEADLESS);
        }

        String trimmed = value.trim().toLowerCase();
        if (trimmed.equals("true")) {
            return true;
        } else if (trimmed.equals("false")) {
            return false;
        } else {
            throw new IllegalStateException(
                    "Invalid boolean value for " + HEADLESS
            );
        }
    }


}
