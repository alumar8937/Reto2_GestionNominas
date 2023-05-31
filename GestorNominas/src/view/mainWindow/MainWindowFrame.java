package view.mainWindow;

import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.historyWindow.HistoryWindowFrame;
import view.payroll.EditPayrollWindow;
import view.payrollPreview.PayrollPreviewPanel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @Author David Serna Mateu
 * Displays the main panel with most of the info
 */

public class MainWindowFrame extends JFrame {

    private static MainWindowFrame INSTANCE = null;

    private JPanel marginPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private mainWindowJPanel panel = new mainWindowJPanel();

    private MainWindowFrame() {
        //setResizable(false);

        setTitle(ProgramLanguageProperties.getProperty("mainWindow"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        constraints.insets.set(20, 20, 20, 20); // Space between components.
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        marginPanel.add(panel, constraints);
        add(marginPanel);

        pack();
        setMinimumSize(new Dimension(1000, 600));
        setSize(1200,600);
        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }

    public static MainWindowFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MainWindowFrame();
        }
        return INSTANCE;
    }

    public mainWindowJPanel getPanel() {
        return panel;
    }

    public class mainWindowJPanel extends JPanel {
        PayrollPreviewPanel previewPayroll = new PayrollPreviewPanel();

        JPanel panelButtonsLabels = new JPanel();

        JList payrollList = new JList();

        JScrollPane previewPayrollScrollPane = new JScrollPane(previewPayroll);
        JScrollPane payrollListScroll = new JScrollPane(payrollList);

        JLabel batchesLabel = new JLabel(ProgramLanguageProperties.getProperty("batchesLabel"));
        JLabel payrollsLabel = new JLabel(ProgramLanguageProperties.getProperty("payrollsLabel"));

        JComboBox<PayrollBatch> payrollBatchesComboBox = new JComboBox<PayrollBatch>();

        JButton newBatchButton = new JButton(ProgramLanguageProperties.getProperty("newBatchButton"));
        JButton deleteBatchButton = new JButton(ProgramLanguageProperties.getProperty("deleteBatchButton"));
        JButton sendToHistoryButton = new JButton(ProgramLanguageProperties.getProperty("sendToHistoryButton"));
        JButton historyButton = new JButton(ProgramLanguageProperties.getProperty("historyButton"));
        JButton newPayrollButton = new JButton(ProgramLanguageProperties.getProperty("newPayrollButton"));
        JButton editPayrollButton = new JButton(ProgramLanguageProperties.getProperty("editPayrollButton"));
        JButton deletePayrollButton = new JButton(ProgramLanguageProperties.getProperty("deletePayrollButton"));

        private mainWindowJPanel() {

            setLayout(new GridBagLayout());
            payrollListScroll.setMinimumSize(new Dimension(280,100));
            previewPayrollScrollPane.setMinimumSize(new Dimension(500,800));
            previewPayrollScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            GridBagConstraints gcbPreview = new GridBagConstraints();
            gcbPreview.gridx = 0; // Columna 0
            gcbPreview.gridy = 0; // Fila 0
            gcbPreview.weightx = 1;
            gcbPreview.weighty = 1;
            gcbPreview.gridheight = 9; // Ocupa 9 filas
            gcbPreview.anchor = GridBagConstraints.NORTH;
            gcbPreview.fill = GridBagConstraints.BOTH; // Rellena el espacio horizontal y verticalmente

            add(previewPayrollScrollPane, gcbPreview);
            previewPayrollScrollPane.revalidate();
            payrollListScroll.revalidate();

            gcbPreview.gridx = 1;
            add(payrollListScroll, gcbPreview);
            payrollList.addListSelectionListener((e) -> setDataPayrollPreview());

            gcbPreview.gridx = 2;
            gcbPreview.anchor = GridBagConstraints.FIRST_LINE_START;
            gcbPreview.weighty = 0;
            add(panelButtonsLabels, gcbPreview);
            panelButtonsLabels.setLayout(new GridBagLayout());
            GridBagConstraints gcbPanelButtonsLabels = new GridBagConstraints();
            gcbPanelButtonsLabels.gridx = 0; // Columna 0
            gcbPanelButtonsLabels.gridy = 0; // Fila 0
            gcbPanelButtonsLabels.weightx = 1;
            gcbPanelButtonsLabels.weighty = 0;
            gcbPanelButtonsLabels.fill = GridBagConstraints.HORIZONTAL;
            gcbPanelButtonsLabels.anchor = GridBagConstraints.FIRST_LINE_START;
            gcbPanelButtonsLabels.insets.set(5, 5, 5, 5);

            panelButtonsLabels.add(batchesLabel, gcbPanelButtonsLabels);

            gcbPanelButtonsLabels.gridy = 1;
            PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);
            panelButtonsLabels.add(payrollBatchesComboBox, gcbPanelButtonsLabels);
            payrollBatchesComboBox.addActionListener((e) -> updatePayrollListAction());

            gcbPanelButtonsLabels.gridy = 2;
            panelButtonsLabels.add(newBatchButton, gcbPanelButtonsLabels);
            newBatchButton.addActionListener((e) -> {PayrollDBController.createBatch(); PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);});
            updatePayrollListAction();

            gcbPanelButtonsLabels.gridy = 3;
            panelButtonsLabels.add(deleteBatchButton, gcbPanelButtonsLabels);
            deleteBatchButton.addActionListener((e) -> deleteBatchButtonAction());

            gcbPanelButtonsLabels.gridy = 4;
            panelButtonsLabels.add(sendToHistoryButton, gcbPanelButtonsLabels);
            sendToHistoryButton.addActionListener((e) ->  setBatchAcceptedButtonAction());

            gcbPanelButtonsLabels.gridy = 5;
            panelButtonsLabels.add(historyButton, gcbPanelButtonsLabels);
            historyButton.addActionListener((e) -> HistoryWindowFrame.getINSTANCE().setVisible(true));

            gcbPanelButtonsLabels.gridy = 6;
            panelButtonsLabels.add(payrollsLabel, gcbPanelButtonsLabels);

            gcbPanelButtonsLabels.gridy = 7;
            panelButtonsLabels.add(newPayrollButton, gcbPanelButtonsLabels);
            newPayrollButton.addActionListener((e) -> EditPayrollWindow.getINSTANCE().setVisible(true));

            gcbPanelButtonsLabels.gridy = 8;
            panelButtonsLabels.add(editPayrollButton, gcbPanelButtonsLabels);
            editPayrollButton.addActionListener((e) -> setDataEditPayroll());

            gcbPanelButtonsLabels.gridy = 9;
            panelButtonsLabels.add(deletePayrollButton, gcbPanelButtonsLabels);
            deletePayrollButton.addActionListener((e) -> deletePayrollButtonAction());

        }

        private void setDataPayrollPreview() {
            if(payrollList.getSelectedIndex() == -1){
                return;
            }
            previewPayroll.setData((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex()));
        }
        
        private void setDataEditPayroll() { // Author: Javier Blasco Gómez
            EditPayrollWindow editPayrollWindow = new EditPayrollWindow();
            if(payrollList.getSelectedIndex() == -1){
                return;
            }
            editPayrollWindow.editData((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex()));
        }

        public JComboBox<PayrollBatch> getPayrollBatchesComboBox() {
            return payrollBatchesComboBox;
        }

        public void updatePayrollListAction(){
            PayrollBatch batch = (PayrollBatch) payrollBatchesComboBox.getSelectedItem();
            if (batch == null) {return;}
            PayrollDBController.getPayrollsByBatchId(batch);
            payrollList.setModel(PayrollDBController.getListOfPayrolls(batch));
        }

        private void deleteBatchButtonAction(){
            if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deleteBatchDialog"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(payrollBatchesComboBox.getSelectedItem() == null){
                    return;
                }
                PayrollDBController.deletePayrollsOfBatch(((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID());
                PayrollDBController.deleteBatch(((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID());
                updatePayrollListAction();
                PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);
            }
        }

        private void deletePayrollButtonAction(){
            if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deletePayrollDialog"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(payrollList.getSelectedIndex() == -1){return;}
                if(payrollList.getModel().getElementAt(payrollList.getSelectedIndex()) == null){return;}
                PayrollDBController.deletePayroll(((Payroll) payrollList.getModel().getElementAt(payrollList.getSelectedIndex())).getId_name());
                updatePayrollListAction();
                PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);
            }
        }

        private void  setBatchAcceptedButtonAction(){ // Raul Simarro Navarro
            if (payrollBatchesComboBox.getSelectedItem() == null) {return;}
            int batchID = ((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID();
            PayrollDBController.setBatchAccepted(batchID, true);
            PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);
            PayrollDBController.setComboBatchItems(HistoryWindowFrame.getINSTANCE().getPanel().getPayrollBatchesComboBox(), true);
        }
    }
}