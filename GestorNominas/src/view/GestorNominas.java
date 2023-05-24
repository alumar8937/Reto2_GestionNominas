package view;

import programLanguage.ProgramLanguageProperties;
import programLanguage.SupportedLanguage;
import view.initialConfig.InitialConfigFrame;
import view.mainWindow.mainWindowFrame;

/**
 * @Author Pedro Marín Sanchis
 * Main class, runs the InitialConfigFrame
 */
public class GestorNominas {
    public static void main(String[] args) {
        ProgramLanguageProperties.setLanguage(SupportedLanguage.EN);
        new InitialConfigFrame();
        new mainWindowFrame();
    }
}

