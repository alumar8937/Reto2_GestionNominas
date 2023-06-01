package view.mainWindow.queryPanel;

import view.mainWindow.payroll.preview.PayrollPreviewPanel;
import view.mainWindow.queryPanel.controlPanel.ControlPanel;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.EmployeeSelectionPanel;

import javax.swing.*;

public class QueryByEmployeePanel extends JPanel {
    private static QueryByEmployeePanel INSTANCE = null;
    private ControlPanel controlPanel = new ControlPanel(new EmployeeSelectionPanel());
    private PayrollPreviewPanel payrollPreviewPanel = new PayrollPreviewPanel();
    private JScrollPane previewPayrollScrollPane = new JScrollPane(payrollPreviewPanel);

    private QueryByEmployeePanel() {

    }

    public void updatePayrollPreviewPanel() {
        payrollPreviewPanel.setData(controlPanel.getPayrollJList().getSelectedValue());
        revalidate();
        repaint();
    }

    public static QueryByEmployeePanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByEmployeePanel();
        }
        return INSTANCE;
    }
}