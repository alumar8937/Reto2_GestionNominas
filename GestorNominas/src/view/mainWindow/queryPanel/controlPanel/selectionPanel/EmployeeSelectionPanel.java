package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import model.Payroll;

import java.util.ArrayList;

public class EmployeeSelectionPanel extends SelectionPanel {
    @Override
    public ArrayList<Payroll> getPayrollList() {
        if (payrollEmployeesComboBox.getSelectedItem() == null) {return null;}
        String NIF = ( (Employee) payrollEmployeesComboBox.getSelectedItem()).getNIF();
        return PayrollDBController.getPayrollsByEmployeeNIF( NIF, displayHistoryOnlyCheckBox.isSelected());
    }
}
