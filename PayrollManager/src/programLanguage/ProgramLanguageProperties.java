package programLanguage;

import constants.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Retrieves program language keys depending on the configured language.
 *
 * @author Pedro Marín Sanchis
 */
public class ProgramLanguageProperties {

    private static final Properties programLanguageProperties = new Properties();

    /**
     * Retrieves the value associated with the specified key from the program language properties.
     * If the key is not found, returns "missing_value".
     *
     * @param key the key to retrieve the value for
     * @return the value associated with the key, or the key itself if the key is not found
     */
    public static String getProperty(String key) {
        String value = programLanguageProperties.getProperty(key, key);
        if (value.equalsIgnoreCase("")) {
            return key;
        }
        return value;
    }

    /**
     * Sets the language for retrieving program language keys.
     *
     * @param language the supported language to set
     */
    public static void setLanguage(SupportedLanguage language) {
        if (language == null) {return;}
        try {
            programLanguageProperties.load(new BufferedReader(new FileReader(Constants.DICTIONARY_FILE_PATH + language.getDictionaryFileName())));
        } catch (IOException ignored) {}
    }

}