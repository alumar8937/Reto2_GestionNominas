package userconfig;

import constants.Constants;
import programLanguage.SupportedLanguage;

import java.io.*;
import java.util.Properties;

/**
 * @Author Pedro Marín Sanchis
 * Sets and retireves user configuration for language and database connection.
 */
public class UserconfigManager {

    private static UserconfigManager INSTANCE = null;

    private final Properties userconfigProperties = new Properties();

    private UserconfigManager() {
        try {
            userconfigProperties.load(new BufferedReader(new FileReader("."+ File.separator+"GestorNominas"+File.separator+"src"+File.separator+"userconfig"+File.separator+"userconfig.properties")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static UserconfigManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserconfigManager();
        }
        return INSTANCE;
    }

    public void setIP(String ip) {
        userconfigProperties.setProperty("ip",ip);
    }

    public void setPort(String port) {
        userconfigProperties.setProperty("port",port);
    }

    public void setUser(String user) {
        userconfigProperties.setProperty("user",user);
    }

    public void setPassword(String password) {
        userconfigProperties.setProperty("password",password);
    }

    public void setDatabase(String database) {
        userconfigProperties.setProperty("database",database);
    }

    public void setLanguage(SupportedLanguage language) {
        if (language == null) {return;}
        userconfigProperties.setProperty("selectedLanguage", language.name());
    }

    public void store() {
        try {
            userconfigProperties.store(new FileOutputStream("."+ File.separator+"GestorNominas"+File.separator+"src"+File.separator+"userconfig"+File.separator+"userconfig.properties"), null);
        } catch (IOException e) {
            //TODO: Handle exception.
        }
    }

    public void setDefaultInitialValues() {
        setIP(Constants.DEFAULT_DB_IP);
        setPort(Constants.DEFAULT_DB_PORT);
        setUser(Constants.DEFAULT_DB_USER);
        setPassword(Constants.DEFAULT_DB_PASSWORD);
        setLanguage(Constants.DEFAULT_LANGUAGE);
    }

}
