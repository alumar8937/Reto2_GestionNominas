package view;

import programLanguage.ProgramLanguageProperties;
import userconfig.UserconfigManager;
import view.initialConfig.InitialConfigFrame;

/**
 * @author Pedro Marín Sanchis
 * Main class, runs the InitialConfigFrame
 */
public class GestorNominas {
    public static void main(String[] args) {
        ProgramLanguageProperties.setLanguage(UserconfigManager.getINSTANCE().getLanguage());
        new InitialConfigFrame();
    }
}

