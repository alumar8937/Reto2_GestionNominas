package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import controller.database.PayrollDBController;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a panel for selecting payrolls by Employee.
 *
 * @author David Serna Mateu
 */
public class EmployeeSelectionPanel extends SelectionPanel {
    private final JLabel employeesLabel = new JLabel(ProgramLanguageProperties.getProperty("employeesLabel"));
    private final JComboBox<Employee> payrollEmployeesComboBox = new JComboBox<Employee>();
    private final JCheckBox displayHistoryOnlyCheckBox = new JCheckBox(ProgramLanguageProperties.getProperty("displayHistoryOnly"));

    /**
     * Constructs a new instance of EmployeeSelectionPanel.
     */
    public EmployeeSelectionPanel() {
        super();
        placeComponents();
        addActionListeners();
        fillEmployeesComboBox();
    }

    /**
     * Retrieves the list of payrolls selected in the panel.
     *
     * @return The list of selected payrolls.
     */
    @Override
    public ArrayList<Payroll> getPayrollList() {
        if (payrollEmployeesComboBox.getSelectedItem() == null) {return null;}
        String NIF = ( (Employee) payrollEmployeesComboBox.getSelectedItem()).getNIF();
        return PayrollDBController.getPayrollsByEmployeeNIF( NIF, displayHistoryOnlyCheckBox.isSelected());
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
        add(employeesLabel, constraints);

        constraints.gridy += 1;
        add(payrollEmployeesComboBox, constraints);

        constraints.gridy += 1;
        add(displayHistoryOnlyCheckBox, constraints);
    }

    /**
     * Fills the employee batches combo box with available employees from the database.
     */
    private void fillEmployeesComboBox() {
        employeesComboBox.removeAllItems();
        for(Employee e : PayrollDBController.getEmployees()) {
            payrollEmployeesComboBox.addItem(e);
        }
    }

    /**
     * Adds action listeners to the components in the panel.
     */
    private void addActionListeners() {
        payrollEmployeesComboBox.addActionListener((e) -> sendUpdateActionEvent());
        displayHistoryOnlyCheckBox.addActionListener((e) -> fillPayrollEmployeesComboBox());
    }
}
