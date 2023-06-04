package view.mainWindow.queryPanel.controlPanel;

import controller.database.PayrollDBController;
import model.Payroll;
import programLanguage.ProgramLanguageProperties;
import view.mainWindow.payroll.EditPayrollWindow;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a panel containing buttons for managing payrolls.
 * Allows creating new payrolls, editing existing payrolls, and deleting payrolls.
 *
 * @author Pedro Marín Sanchis
 */
public class PayrollButtonPanel extends JPanel {
    private final JLabel payrollsLabel = new JLabel(ProgramLanguageProperties.getProperty("payrollsLabel"));
    private final JButton newPayrollButton = new JButton(ProgramLanguageProperties.getProperty("newPayrollButton"));
    private final JButton editPayrollButton = new JButton(ProgramLanguageProperties.getProperty("editPayrollButton"));
    private final JButton deletePayrollButton = new JButton(ProgramLanguageProperties.getProperty("deletePayrollButton"));
    private final JButton deleteAllPayrollButton = new JButton(ProgramLanguageProperties.getProperty("deleteAllPayrollButton"));
    private final JButton recalculateAllPayrollsButton = new JButton(ProgramLanguageProperties.getProperty("recalculatePayrolls"));
    private final JButton exportToSEPAButton = new JButton(ProgramLanguageProperties.getProperty("sepaButton"));
    private JList<Payroll> payrollList = null;
    protected ActionListener listener;

    /**
     * Constructs a new instance of PayrollButtonPanel.
     *
     * @param payrollList The JList containing the payrolls.
     */
    public PayrollButtonPanel(JList<Payroll> payrollList) {
        this.payrollList = payrollList;
        placeComponents();
    }

    /**
     * Places the components within the panel and sets up their event listeners.
     */
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
        newPayrollButton.addActionListener((e) -> {new EditPayrollWindow(this);});

        constraints.gridy += 1;
        add(editPayrollButton, constraints);
        editPayrollButton.addActionListener((e) -> setDataEditPayroll());

        constraints.gridy += 1;
        add(deletePayrollButton, constraints);
        deletePayrollButton.addActionListener((e) -> deletePayrollButtonAction());

        constraints.gridy += 1;
        add(deleteAllPayrollButton, constraints);
        deleteAllPayrollButton.addActionListener((e) -> deleteAllPayrollsButtonAction());

        constraints.gridy += 1;
        add(recalculateAllPayrollsButton, constraints);
        recalculateAllPayrollsButton.addActionListener((e) -> recalculateAllPayrollsButtonAction());

        constraints.gridy += 1;
        add(exportToSEPAButton, constraints);
        exportToSEPAButton.addActionListener((e) -> exportToSEPAButtonAction());
    }

    /**
     * Opens the EditPayrollWindow to edit the selected payroll's data.
     * If no payroll is selected, this method has no effect.
     */
    private void setDataEditPayroll() { // Author: Javier Blasco Gómez
        EditPayrollWindow editPayrollWindow = new EditPayrollWindow(this);
        if(payrollList.getSelectedIndex() == -1){
            return;
        }
        editPayrollWindow.editData((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex()));
        sendUpdateActionEvent();
    }

    /**
     * Deletes the selected payroll from the database.
     * If no payroll is selected, this method has no effect.
     * Shows a confirmation dialog before deleting the payroll.
     * After deletion, triggers an update action event.
     */
    private void deletePayrollButtonAction(){ // Author: David Serna Mateu
        if(JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this), ProgramLanguageProperties.getProperty("deletePayrollWarning"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(payrollList.getSelectedIndex() == -1){return;}
            if(payrollList.getModel().getElementAt(payrollList.getSelectedIndex()) == null){return;}
            PayrollDBController.deletePayroll(((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex())).getId_name());
            sendUpdateActionEvent();
        }
    }

    /**
     * Deletes all shown payrolls from the database.
     * If no payroll is selected, this method has no effect.
     * Shows a confirmation dialog before deleting the payroll.
     * After deletion, triggers an update action event.
     */
    private void deleteAllPayrollsButtonAction(){ // Author: Pedro Marín Sanchís
        if(JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this), ProgramLanguageProperties.getProperty("deletePayrollWarning"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            for (int i = 0; i < payrollList.getModel().getSize(); i++) {
                PayrollDBController.deletePayroll(((Payroll) payrollList.getModel().getElementAt(i)).getId_name());
            }
            sendUpdateActionEvent();
        }
    }

    /**
     * Recalculates all payrolls in the database.
     */
    private void recalculateAllPayrollsButtonAction() {
        if (PayrollDBController.calculateAllPayrolls()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), ProgramLanguageProperties.getProperty("recalculatedCorrecly"));
        } else {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), ProgramLanguageProperties.getProperty("errorrecalculating"));
        }
        sendUpdateActionEvent();
    }

    /**
     * Sends an ExportSEPA action event to the registered listener.
     * Notifies the listener that an ExportSEPA action has occurred.
     */
    private void exportToSEPAButtonAction() {
        if (listener == null) {return;}
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "ExportSEPA");
        listener.actionPerformed(event);
    }

    /**
     * Sends an update action event to the registered listener.
     * Notifies the listener that an update action has occurred.
     */
    public void sendUpdateActionEvent() {
        if (listener == null) {return;}
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update");
        listener.actionPerformed(event);
    }

    /**
     * Adds an ActionListener to this PayrollButtonPanel.
     * The listener will be notified when an update action occurs.
     *
     * @param listener The ActionListener to be added.
     */
    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }
}