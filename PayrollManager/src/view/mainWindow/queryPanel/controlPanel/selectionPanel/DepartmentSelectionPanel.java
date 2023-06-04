package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import controller.database.PayrollDBController;
import model.Department;
import model.Payroll;
import programLanguage.ProgramLanguageProperties;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

/**
 * Represents a panel for selecting payrolls by department.
 *
 * @author Javier Blasco Gómez
 */
public class DepartmentSelectionPanel extends SelectionPanel {
    private final JLabel departmentLabel = new JLabel(ProgramLanguageProperties.getProperty("departmentLabel"));
    private final JComboBox<Department> payrollDepartmentComboBox = new JComboBox<Department>();
    private final JCheckBox displayHistoryOnlyCheckBox = new JCheckBox(ProgramLanguageProperties.getProperty("displayHistoryOnly"));

    /**
     * Constructs a new instance of DepartmentSelectionPanel.
     */
    public DepartmentSelectionPanel() {
        super();
        placeComponents();
        addActionListeners();
        fillDepartmentComboBox();
    }

    /**
     * Retrieves the list of payrolls selected in the panel.
     *
     * @return The list of selected payrolls.
     */
    @Override
    public ArrayList<Payroll> getPayrollList() {
        if (payrollDepartmentComboBox.getSelectedItem() == null) {return null;}
        String ID = ( (Department) payrollDepartmentComboBox.getSelectedItem()).getID();
        return PayrollDBController.getPayrollsByDepartmentID( ID, displayHistoryOnlyCheckBox.isSelected());
    }

    /**
     * Places the components in the panel using GridBagLayout.
     */
    private void placeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(5, 0, 5, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(departmentLabel, constraints);

        constraints.gridy += 1;
        add(payrollDepartmentComboBox, constraints);

        constraints.gridy += 1;
        add(displayHistoryOnlyCheckBox, constraints);
    }

    /**
     * Fills the department combo box with available departments from the database.
     */
    private void fillDepartmentComboBox() {
        payrollDepartmentComboBox.removeAllItems();
        for (Department d: PayrollDBController.getDepartments()) {
            payrollDepartmentComboBox.addItem(d);
        }
    }

    /**
     * Adds action listeners to the components in the panel.
     */
    private void addActionListeners() {
        payrollDepartmentComboBox.addActionListener((e)->sendUpdateActionEvent());
        displayHistoryOnlyCheckBox.addActionListener((e)->fillDepartmentComboBox());
    }
}