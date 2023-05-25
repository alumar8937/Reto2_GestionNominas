package view;

import controller.database.PayrollDBController;
import programLanguage.ProgramLanguageProperties;
import programLanguage.SupportedLanguage;
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
        ProgramLanguageProperties.setLanguage(SupportedLanguage.EN);
        new InitialConfigFrame();
        //new HistoryWindowFrame();
        //new EditPayrollWindow();
    }
}

