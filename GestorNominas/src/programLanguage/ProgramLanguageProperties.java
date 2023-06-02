package programLanguage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
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
     * @return true if the language is set successfully, false otherwise
     */
    public static boolean setLanguage(SupportedLanguage language) {
        if (language == null) {return false;}
        try {
            programLanguageProperties.load(new BufferedReader(new FileReader("." + File.separator + "GestorNominas" + File.separator + "src" + File.separator + "programLanguage" + File.separator + "dictionary" + File.separator + language.getDictionaryFileName())));
        } catch (FileNotFoundException f) {
            return false;
        } catch (IOException i) {
            return false;
        }
        return true;
    }

}
