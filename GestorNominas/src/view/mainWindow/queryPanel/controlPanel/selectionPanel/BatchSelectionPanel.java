package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class BatchSelectionPanel extends SelectionPanel {
    private JLabel batchesLabel = new JLabel(ProgramLanguageProperties.getProperty("batchesLabel"));
    private JComboBox<PayrollBatch> payrollBatchesComboBox = new JComboBox<PayrollBatch>();
    private JCheckBox displayHistoryOnlyCheckBox = new JCheckBox("Display History only //REPLACE");

    private JButton newBatchButton = new JButton(ProgramLanguageProperties.getProperty("newBatchButton"));
    private JButton deleteBatchButton = new JButton(ProgramLanguageProperties.getProperty("deleteBatchButton"));
    private JButton sendToHistoryButton = new JButton(ProgramLanguageProperties.getProperty("sendToHistoryButton"));

    public BatchSelectionPanel() {
        super();
        placeComponents();
        addActionListeners();
        fillPayrollBatchesComboBox();
    }

    @Override
    public ArrayList<Payroll> getPayrollList() {
        PayrollBatch batch = (PayrollBatch) payrollBatchesComboBox.getSelectedItem();
        if (batch == null) {return null;}
        return batch.getPayrolls();
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
        add(batchesLabel, constraints);

        constraints.gridy += 1;
        add(payrollBatchesComboBox, constraints);

        constraints.gridy += 1;
        add(displayHistoryOnlyCheckBox, constraints);

        constraints.gridy += 1;
        add(newBatchButton, constraints);

        constraints.gridy += 1;
        add(deleteBatchButton, constraints);

        constraints.gridy += 1;
        add(sendToHistoryButton, constraints);
    }

    private void fillPayrollBatchesComboBox() {
        payrollBatchesComboBox.removeAllItems();
        for (PayrollBatch p: PayrollDBController.getBatches(displayHistoryOnlyCheckBox.isSelected())) {
            payrollBatchesComboBox.addItem(p);
        }
    }

    private void addActionListeners() {
        payrollBatchesComboBox.addActionListener((e) -> sendUpdateActionEvent());
        newBatchButton.addActionListener((e) -> {PayrollDBController.createNewBatch(); fillPayrollBatchesComboBox();});
        deleteBatchButton.addActionListener((e) -> deleteBatchButtonAction());
        displayHistoryOnlyCheckBox.addActionListener((e) -> fillPayrollBatchesComboBox());
        sendToHistoryButton.addActionListener((e) -> setBatchAcceptedButtonAction());
    }

    private void deleteBatchButtonAction(){ // Author: David Serna Mateu
        if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deleteBatchDialog"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(payrollBatchesComboBox.getSelectedItem() == null){return;}
            PayrollDBController.deletePayrollsOfBatch(((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID());
            PayrollDBController.deleteBatch(((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID());
        }
        fillPayrollBatchesComboBox();
    }

    private void  setBatchAcceptedButtonAction(){ // Author: Raul Simarro Navarro
        if (payrollBatchesComboBox.getSelectedItem() == null) {return;}
        PayrollDBController.setBatchAccepted(
                ((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID(),
                !displayHistoryOnlyCheckBox.isSelected()
                );
        fillPayrollBatchesComboBox();
    }

}
