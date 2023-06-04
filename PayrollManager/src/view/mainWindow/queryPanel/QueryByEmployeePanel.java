package view.mainWindow.queryPanel;

import view.mainWindow.payroll.preview.PayrollPreviewPanel;
import view.mainWindow.queryPanel.controlPanel.ControlPanel;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.EmployeeSelectionPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @author Pedro Marín Sanchis
 *
 */
public class QueryByEmployeePanel extends JPanel {
    private static QueryByEmployeePanel INSTANCE = null;
    private final ControlPanel controlPanel = new ControlPanel(new EmployeeSelectionPanel());
    private final PayrollPreviewPanel payrollPreviewPanel = new PayrollPreviewPanel();
    private final JScrollPane previewPayrollScrollPane = new JScrollPane(payrollPreviewPanel);

    /**
     * Constructs a new instance of QueryByEmployeePanel.
     */
    private QueryByEmployeePanel() {
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
     * Retrieves the singleton instance of QueryByEmployeePanel.
     *
     * @return The singleton instance of QueryByEmployeePanel.
     */
    public static QueryByEmployeePanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByEmployeePanel();
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