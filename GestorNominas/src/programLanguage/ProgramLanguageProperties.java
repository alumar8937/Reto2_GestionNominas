package programLanguage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author Pedro Marín Sanchis
 * Retrieves program language keys depending on the configured language.
 */
public class ProgramLanguageProperties {

    private static final Properties programLanguageProperties = new Properties();

    public static String getProperty(String key) {
        return programLanguageProperties.getProperty(key);
    }

    public static boolean setLanguage(SupportedLanguage language) {
        if (language == null) {return false;}
        try {
            programLanguageProperties.load(new BufferedReader(new FileReader("."+File.separator+"GestorNominas"+File.separator+"src"+File.separator+"programLanguage"+File.separator+"dictionary"+File.separator+language.getDictionaryFileName())));
        } catch (FileNotFoundException f) {
            return false;
        } catch (IOException i) {
            return false;
        }
        return true;
    }

}
