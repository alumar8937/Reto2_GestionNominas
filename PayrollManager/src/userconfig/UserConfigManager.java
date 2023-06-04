package userconfig;

import constants.Constants;
import programLanguage.SupportedLanguage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Manages user configuration for language and database connection settings.
 * This class provides methods to set and retrieve user configuration values such as IP, port, user, password,
 * database, and language.
 * User configuration values are stored and retrieved from a properties file.
 * @author Pedro Marín Sanchis
 */
public class UserConfigManager {

    private static UserConfigManager INSTANCE = null;

    private final Properties userconfigProperties = new Properties();

    /**
     * Private constructor to enforce singleton pattern and load user configuration properties from file.
     */
    private UserConfigManager() {
        try {
            userconfigProperties.load(new BufferedReader(new FileReader(Constants.USERCONFIG_PATH)));
        } catch (IOException ignored) {
        }
    }

    /**
     * Returns the singleton instance of the UserConfigManager.
     *
     * @return The UserConfigManager instance.
     */
    public static UserConfigManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserConfigManager();
        }
        return INSTANCE;
    }

    /**
     * Sets the IP address in the user configuration.
     *
     * @param ip The IP address to set.
     */
    public void setIP(String ip) {
        userconfigProperties.setProperty("ip", ip);
    }

    /**
     * Retrieves the IP address from the user configuration.
     *
     * @return The IP address.
     */
    public String getIP() {
        return userconfigProperties.getProperty("ip");
    }

    /**
     * Sets the port number in the user configuration.
     *
     * @param port The port number to set.
     */
    public void setPort(String port) {
        userconfigProperties.setProperty("port", port);
    }

    /**
     * Retrieves the port number from the user configuration.
     *
     * @return The port number.
     */
    public String getPort() {
        return userconfigProperties.getProperty("port");
    }

    /**
     * Sets the username in the user configuration.
     *
     * @param user The username to set.
     */
    public void setUser(String user) {
        userconfigProperties.setProperty("user", user);
    }

    /**
     * Retrieves the username from the user configuration.
     *
     * @return The username.
     */
    public String getUser() {
        return userconfigProperties.getProperty("user");
    }

    /**
     * Sets the password in the user configuration.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        userconfigProperties.setProperty("password", password);
    }

    /**
     * Retrieves the password from the user configuration.
     *
     * @return The password.
     */
    public String getPassword() {
        return userconfigProperties.getProperty("password");
    }

    /**
     * Sets the database name in the user configuration.
     *
     * @param database The database name to set.
     */
    public void setDatabase(String database) {
        userconfigProperties.setProperty("database", database);
    }

    /**
     * Retrieves the database name from the user configuration.
     *
     * @return The database name.
     */
    public String getDatabase() {
        return userconfigProperties.getProperty("database");
    }

    /**
     * Sets the selected language in the user configuration.
     *
     * @param language The language to set.
     */
    public void setLanguage(SupportedLanguage language) {
        if (language == null) {
            return;
        }
        userconfigProperties.setProperty("selectedLanguage", language.name());
    }

    /**
     * Retrieves the selected language from the user configuration.
     *
     * @return The selected language.
     */
    public SupportedLanguage getLanguage() {
        String languageShortName = userconfigProperties.getProperty("selectedLanguage");
        for (SupportedLanguage sl : SupportedLanguage.values()) {
            if (sl.name().equalsIgnoreCase(languageShortName)) {
                return sl;
            }
        }
        return null;
    }

    /**
     * Stores the user configuration properties to a file.
     */
    public void store() {
        try {
            userconfigProperties.store(new FileOutputStream(Constants.USERCONFIG_PATH), null);
        } catch (IOException ignored) {
        }
    }

    /**
     * Sets the default initial values for user configuration settings.
     * This method can be called to set default values when no user configuration file is found.
     */
    public void setDefaultInitialValues() {
        setIP(Constants.DEFAULT_DB_IP);
        setPort(Constants.DEFAULT_DB_PORT);
        setUser(Constants.DEFAULT_DB_USER);
        setPassword(Constants.DEFAULT_DB_PASSWORD);
        setLanguage(Constants.DEFAULT_LANGUAGE);
    }
}