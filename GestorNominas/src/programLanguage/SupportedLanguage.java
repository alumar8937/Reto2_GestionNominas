package programLanguage;

/**
 * @Author Pedro Marín Sanchis
 * Enum of supported program languages with a dictionary file.
 */
public enum SupportedLanguage {
    EN("English"),
    ES("Español"),
    VAL("Valencià");

    private String longName;

    private SupportedLanguage(String longName) {
        this.longName=longName;
    }

    public String toString() {
        return longName;
    }

    public String getDictionaryFileName() {
        return this.name()+".lang";
    }

}
