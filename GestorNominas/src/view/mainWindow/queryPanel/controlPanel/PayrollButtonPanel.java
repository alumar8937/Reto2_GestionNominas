package view.mainWindow.queryPanel.controlPanel;

import controller.database.PayrollDBController;
import model.Payroll;
import programLanguage.ProgramLanguageProperties;
import view.mainWindow.payroll.EditPayrollWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class PayrollButtonPanel extends JPanel {
    private final JLabel payrollsLabel = new JLabel(ProgramLanguageProperties.getProperty("payrollsLabel"));
    private final JButton newPayrollButton = new JButton(ProgramLanguageProperties.getProperty("newPayrollButton"));
    private final JButton editPayrollButton = new JButton(ProgramLanguageProperties.getProperty("editPayrollButton"));
    private final JButton deletePayrollButton = new JButton(ProgramLanguageProperties.getProperty("deletePayrollButton"));

    private JList<Payroll> payrollList = null;
    protected ActionListener listener;

    public PayrollButtonPanel(JList<Payroll> payrollList) {
        this.payrollList = payrollList;
        placeComponents();
    }

    private void placeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(payrollsLabel, constraints);

        constraints.gridy += 1;
        add(newPayrollButton, constraints);
        newPayrollButton.addActionListener((e) -> {});

        constraints.gridy += 1;
        add(editPayrollButton, constraints);
        editPayrollButton.addActionListener((e) -> setDataEditPayroll());

        constraints.gridy += 1;
        add(deletePayrollButton, constraints);
        deletePayrollButton.addActionListener((e) -> deletePayrollButtonAction());
    }

    private void setDataEditPayroll() { // Author: Javier Blasco Gómez
        EditPayrollWindow editPayrollWindow = new EditPayrollWindow(this);
        if(payrollList.getSelectedIndex() == -1){
            return;
        }
        editPayrollWindow.editData((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex()));
        sendUpdateActionEvent();
    }

    private void deletePayrollButtonAction(){ // Author: David Serna Mateu
        if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deletePayrollDialog"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(payrollList.getSelectedIndex() == -1){return;}
            if(payrollList.getModel().getElementAt(payrollList.getSelectedIndex()) == null){return;}
            PayrollDBController.deletePayroll(((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex())).getId_name());
            sendUpdateActionEvent();
            //updatePayrollListAction();
            //PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);
        }
    }

    public void sendUpdateActionEvent() {
        if (listener == null) {return;}
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update");
        listener.actionPerformed(event);
    }

    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

}
