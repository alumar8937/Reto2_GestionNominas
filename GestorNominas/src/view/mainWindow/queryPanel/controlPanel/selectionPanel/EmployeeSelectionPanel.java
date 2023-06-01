package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import controller.database.PayrollDBController;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EmployeeSelectionPanel extends SelectionPanel {
    private JLabel employeesLabel = new JLabel(ProgramLanguageProperties.getProperty("employeesLabel"));
    private JComboBox<Employee> payrollEmployeesComboBox = new JComboBox<Employee>();
    private JCheckBox displayHistoryOnlyCheckBox = new JCheckBox("Display History only //REPLACE");

    @Override
    public ArrayList<Payroll> getPayrollList() {
        if (payrollEmployeesComboBox.getSelectedItem() == null) {return null;}
        String NIF = ( (Employee) payrollEmployeesComboBox.getSelectedItem()).getNIF();
        return PayrollDBController.getPayrollsByEmployeeNIF( NIF, displayHistoryOnlyCheckBox.isSelected());
    }

    public EmployeeSelectionPanel() {
        super();
        placeComponents();
        addActionListeners();
        fillPayrollEmployeesComboBox();
    }

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

    private void fillPayrollEmployeesComboBox() {
        payrollEmployeesComboBox.removeAllItems();
        for(Employee e : PayrollDBController.getEmployees()) {
            payrollEmployeesComboBox.addItem(e);
        }
    }

    private void addActionListeners() {
        payrollEmployeesComboBox.addActionListener((e) -> sendUpdateActionEvent());
        displayHistoryOnlyCheckBox.addActionListener((e) -> fillPayrollEmployeesComboBox());
    }
}
