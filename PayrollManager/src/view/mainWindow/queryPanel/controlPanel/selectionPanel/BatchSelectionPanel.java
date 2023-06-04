package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

/**
 * Represents a panel for selecting payroll batches.
 *
 * @author Pedro Marín Sanchis
 */
public class BatchSelectionPanel extends SelectionPanel {
    private JLabel batchesLabel = new JLabel(ProgramLanguageProperties.getProperty("batchesLabel"));
    private final JComboBox<PayrollBatch> payrollBatchesComboBox = new JComboBox<PayrollBatch>();
    private final JCheckBox displayHistoryOnlyCheckBox = new JCheckBox(ProgramLanguageProperties.getProperty("displayHistoryOnly"));

    private final JButton newBatchButton = new JButton(ProgramLanguageProperties.getProperty("newBatchButton"));
    private final JButton deleteBatchButton = new JButton(ProgramLanguageProperties.getProperty("deleteBatchButton"));
    private final JButton sendToHistoryButton = new JButton(ProgramLanguageProperties.getProperty("toggleAcceptedButton"));

    /**
     * Constructs a new instance of BatchSelectionPanel.
     */
    public BatchSelectionPanel() {
        super();
        placeComponents();
        addActionListeners();
        fillPayrollBatchesComboBox();
    }

    /**
     * Retrieves the list of payrolls selected in the panel.
     *
     * @return The list of selected payrolls.
     */
    @Override
    public ArrayList<Payroll> getPayrollList() {
        PayrollBatch batch = (PayrollBatch) payrollBatchesComboBox.getSelectedItem();
        if (batch == null) {return null;}
        return batch.getPayrolls();
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

    /**
     * Fills the payroll batches combo box with available batches from the database.
     */
    private void fillPayrollBatchesComboBox() {
        payrollBatchesComboBox.removeAllItems();
        for (PayrollBatch p: PayrollDBController.getBatches(displayHistoryOnlyCheckBox.isSelected())) {
            payrollBatchesComboBox.addItem(p);
        }
    }

    /**
     * Adds action listeners to the components in the panel.
     */
    private void addActionListeners() {
        payrollBatchesComboBox.addActionListener((e) -> sendUpdateActionEvent());
        newBatchButton.addActionListener((e) -> {PayrollDBController.createNewBatch(); fillPayrollBatchesComboBox();});
        deleteBatchButton.addActionListener((e) -> deleteBatchButtonAction());
        displayHistoryOnlyCheckBox.addActionListener((e) -> fillPayrollBatchesComboBox());
        sendToHistoryButton.addActionListener((e) -> setBatchAcceptedButtonAction());
    }

    /**
     * Performs the action when the delete batch button is clicked.
     */
    private void deleteBatchButtonAction(){ // Author: David Serna Mateu
        if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deleteBatchWarning"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(payrollBatchesComboBox.getSelectedItem() == null){return;}
            PayrollDBController.deletePayrollsOfBatch(((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID());
            PayrollDBController.deleteBatch(((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID());
        }
        fillPayrollBatchesComboBox();
    }

    /**
     * Performs the action when the set batch accepted button is clicked.
     */
    private void  setBatchAcceptedButtonAction(){ // Author: Raul Simarro Navarro
        if (payrollBatchesComboBox.getSelectedItem() == null) {return;}
        PayrollDBController.setBatchAccepted(
                ((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID(),
                !displayHistoryOnlyCheckBox.isSelected()
                );
        fillPayrollBatchesComboBox();
    }

}
