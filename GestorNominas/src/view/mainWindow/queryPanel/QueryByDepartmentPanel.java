package view.mainWindow.queryPanel;

import view.mainWindow.payroll.preview.PayrollPreviewPanel;
import view.mainWindow.queryPanel.controlPanel.ControlPanel;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.DepartmentSelectionPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Pedro Marín Sanchis
 *
 */
public class QueryByDepartmentPanel extends JPanel {
    private static QueryByDepartmentPanel INSTANCE = null;
    private final ControlPanel controlPanel = new ControlPanel(new DepartmentSelectionPanel());
    private final PayrollPreviewPanel payrollPreviewPanel = new PayrollPreviewPanel();
    private final JScrollPane previewPayrollScrollPane = new JScrollPane(payrollPreviewPanel);

    private QueryByDepartmentPanel() {
        placeComponents();
        controlPanel.addActionListener((e) -> updatePayrollPreviewPanel());
    }

    private void placeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        previewPayrollScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        previewPayrollScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(previewPayrollScrollPane, constraints);

        constraints.weightx = 0;
        constraints.gridx += 1;
        add(controlPanel, constraints);

        revalidate();
        repaint();
    }

    public static QueryByDepartmentPanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByDepartmentPanel();
        }
        return INSTANCE;
    }

    public void updatePayrollPreviewPanel() {
        if (controlPanel.getPayrollJList().getSelectedValue() != null) {
            payrollPreviewPanel.setData(controlPanel.getPayrollJList().getSelectedValue());
        } else {
            payrollPreviewPanel.clearData();
        }
        revalidate();
        repaint();
    }

}