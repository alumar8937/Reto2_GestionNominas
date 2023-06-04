package constants;

import programLanguage.SupportedLanguage;

import java.io.File;

/**
 * @author Pedro Marín Sanchis
 * This class defines all program constants and default values.
 */
public class Constants {
    public static final SupportedLanguage DEFAULT_LANGUAGE = SupportedLanguage.EN;
    public static final String ICON_PATH = "." + File.separator + "Media" + File.separator + "GestorNominasIcon.png";

    // Database defaults
    public static final String DEFAULT_DB_IP = "127.0.0.1";
    public static final String DEFAULT_DB_PORT = "5432";
    public static final String DEFAULT_DB_USER = "database";
    public static final String DEFAULT_DB_PASSWORD = "database";

    // Properties classes paths
    public static final String DICTIONARY_FILE_PATH = "." + File.separator + "LanguageDictionaries" + File.separator;
    public static final String USERCONFIG_PATH = "."+ File.separator+"UserConfig" + File.separator + "UserConfig.properties";
}