package programLanguage;

public enum SupportedLanguage {
    EN,
    ES,
    VAL;

    public String toString() {
        return this.name()+".lang";
    }
}
