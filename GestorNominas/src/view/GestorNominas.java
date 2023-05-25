package view;

import controller.database.PayrollDBController;
import programLanguage.ProgramLanguageProperties;
import programLanguage.SupportedLanguage;
import userconfig.UserconfigManager;
import view.historyWindow.HistoryWindowFrame;
import view.initialConfig.InitialConfigFrame;
import view.mainWindow.MainWindowFrame;
import view.payroll.EditPayrollWindow;

/**
 * @Author Pedro Marín Sanchis
 * Main class, runs the InitialConfigFrame
 */
public class GestorNominas {
    public static void main(String[] args) {
        ProgramLanguageProperties.setLanguage(UserconfigManager.getINSTANCE().getLanguage());
        new InitialConfigFrame();
    }
}

