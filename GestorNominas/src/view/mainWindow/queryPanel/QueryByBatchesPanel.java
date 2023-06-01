package view.mainWindow.queryPanel;

import model.Payroll;
import view.mainWindow.payroll.preview.PayrollPreviewPanel;
import view.mainWindow.queryPanel.controlPanel.ControlPanel;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.BatchSelectionPanel;

import javax.swing.*;
import java.awt.*;

public class QueryByBatchesPanel extends JPanel {
    private static QueryByBatchesPanel INSTANCE = null;
    private static ControlPanel controlPanel = new ControlPanel(new BatchSelectionPanel());
    private static PayrollPreviewPanel payrollPreviewPanel = new PayrollPreviewPanel();
    private static JScrollPane previewPayrollScrollPane = new JScrollPane(payrollPreviewPanel);

    private QueryByBatchesPanel() {
        placeComponents();
        controlPanel.addActionListener((e) -> updatePayrollPreviewPanel());
    }

    private void placeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        previewPayrollScrollPane.setMinimumSize(new Dimension(500,800));
        previewPayrollScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(previewPayrollScrollPane, constraints);

        constraints.gridx += 1;
        add(controlPanel, constraints);
    }

    public static QueryByBatchesPanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByBatchesPanel();
        }
        return INSTANCE;
    }

    public static void updatePayrollPreviewPanel() {
        payrollPreviewPanel.setData(controlPanel.getPayrollJList().getSelectedValue());
    }

}