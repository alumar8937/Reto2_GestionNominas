package view.mainWindow.queryPanel;

import model.Payroll;
import view.mainWindow.payroll.preview.PayrollPreviewPanel;
import view.mainWindow.queryPanel.controlPanel.ControlPanel;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.BatchSelectionPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a panel for querying payrolls by batches.
 *
 * @author Pedro Marín Sanchis
 */
public class QueryByBatchesPanel extends JPanel {
    private static QueryByBatchesPanel INSTANCE = null;
    private final ControlPanel controlPanel = new ControlPanel(new BatchSelectionPanel());
    private final PayrollPreviewPanel payrollPreviewPanel = new PayrollPreviewPanel();
    private final JScrollPane previewPayrollScrollPane = new JScrollPane(payrollPreviewPanel);

    /**
     * Constructs a new instance of QueryByBatchesPanel.
     */
    private QueryByBatchesPanel() {
        placeComponents();
        controlPanel.addActionListener((e) -> updatePayrollPreviewPanel());
    }

    /**
     * Places the components in the panel using GridBagLayout.
     */
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

    /**
     * Retrieves the singleton instance of QueryByBatchesPanel.
     *
     * @return The singleton instance of QueryByBatchesPanel.
     */
    public static QueryByBatchesPanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByBatchesPanel();
        }
        return INSTANCE;
    }

    /**
     * Updates the payroll preview panel based on the selected payroll in the control panel.
     */
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