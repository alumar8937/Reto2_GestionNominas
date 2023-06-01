package view.mainWindow.queryPanel;

import view.mainWindow.payroll.preview.PayrollPreviewPanel;
import view.mainWindow.queryPanel.controlPanel.ControlPanel;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.DepartmentSelectionPanel;

import javax.swing.*;

public class QueryByDepartmentPanel extends JPanel {
    private static QueryByDepartmentPanel INSTANCE = null;
    private ControlPanel controlPanel = new ControlPanel(new DepartmentSelectionPanel());
    private PayrollPreviewPanel payrollPreviewPanel = new PayrollPreviewPanel();
    private JScrollPane previewPayrollScrollPane = new JScrollPane(payrollPreviewPanel);

    private QueryByDepartmentPanel() {

    }

    public void updatePayrollPreviewPanel() {
        payrollPreviewPanel.setData(controlPanel.getPayrollJList().getSelectedValue());
        revalidate();
        repaint();
    }

    public static QueryByDepartmentPanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByDepartmentPanel();
        }
        return INSTANCE;
    }
}