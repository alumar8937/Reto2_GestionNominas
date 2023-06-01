package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import controller.database.PayrollDBController;
import model.Department;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DepartmentSelectionPanel extends SelectionPanel {
    private final JLabel departmentLabel = new JLabel(ProgramLanguageProperties.getProperty("departmentLabel"));
    private final JComboBox<Department> payrollDepartmentComboBox = new JComboBox<Department>();
    private final JCheckBox displayHistoryOnlyCheckBox = new JCheckBox(ProgramLanguageProperties.getProperty("displayHistoryOnly"));

    public DepartmentSelectionPanel() {
        super();
        placeComponents();
        addActionListeners();
        fillPayrollBatchesComboBox();
    }

    @Override
    public ArrayList<Payroll> getPayrollList() {
        if (payrollDepartmentComboBox.getSelectedItem() == null) {return null;}
        String ID = ( (Department) payrollDepartmentComboBox.getSelectedItem()).getID();
        return PayrollDBController.getPayrollsByDepartmentID( ID, displayHistoryOnlyCheckBox.isSelected());
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
        add(departmentLabel, constraints);

        constraints.gridy += 1;
        add(payrollDepartmentComboBox, constraints);

        constraints.gridy += 1;
        add(displayHistoryOnlyCheckBox, constraints);
    }

    private void fillPayrollBatchesComboBox() {
        payrollDepartmentComboBox.removeAllItems();
        for (Department d: PayrollDBController.getDepartments()) {
            payrollDepartmentComboBox.addItem(d);
        }
    }

    private void addActionListeners() {
        payrollDepartmentComboBox.addActionListener((e)->sendUpdateActionEvent());
        displayHistoryOnlyCheckBox.addActionListener((e)->fillPayrollBatchesComboBox());
    }
}