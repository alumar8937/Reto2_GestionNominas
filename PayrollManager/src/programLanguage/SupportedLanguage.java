package programLanguage;

/**
 * Enum of supported program languages with a dictionary file.
 *
 * @author Pedro Marín Sanchis
 */
public enum SupportedLanguage {
    EN("English"),
    ES("Español"),
    VAL("Valencià"),
    RUS("Русский"),
    JAP("日本語"),
    HEB("עברית"),
    GER("Deutsch"),
    ROM("Română");

    private final String longName;

    /**
     * Constructs a supported language enum with the specified long name.
     *
     * @param longName the long name of the supported language
     */
    private SupportedLanguage(String longName) {
        this.longName = longName;
    }

    /**
     * Returns the long name of the supported language.
     *
     * @return the long name of the supported language
     */
    public String toString() {
        return longName;
    }

    /**
     * Returns the dictionary file name associated with the supported language.
     *
     * @return the dictionary file name
     */
    public String getDictionaryFileName() {
        return this.name() + ".lang";
    }
}