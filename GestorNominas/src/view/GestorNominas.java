package view;

import programLanguage.ProgramLanguageProperties;
import userconfig.UserconfigManager;
import view.historyWindow.HistoryWindowFrame;
import view.initialConfig.InitialConfigFrame;
import view.payroll.EditPayrollWindow;
import view.payrollPreview.PayrollPreviewPanel;

import javax.swing.*;

/**
 * @Author Pedro Marín Sanchis
 * Main class, runs the InitialConfigFrame
 */
public class GestorNominas {
    public static void main(String[] args) {
        ProgramLanguageProperties.setLanguage(UserconfigManager.getINSTANCE().getLanguage());
        new InitialConfigFrame();
        //new HistoryWindowFrame();
        //new EditPayrollWindow();
    }
}

