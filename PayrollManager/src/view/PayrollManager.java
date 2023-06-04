package view;

import programLanguage.ProgramLanguageProperties;
import userconfig.UserConfigManager;
import view.initialConfig.InitialConfigFrame;

/**
 * @author Pedro Marín Sanchis
 * Main class, runs the InitialConfigFrame
 */
public class PayrollManager {
    public static void main(String[] args) {
        ProgramLanguageProperties.setLanguage(UserConfigManager.getINSTANCE().getLanguage());
        new InitialConfigFrame();
    }
}

