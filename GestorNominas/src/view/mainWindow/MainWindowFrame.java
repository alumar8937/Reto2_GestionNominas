package view.mainWindow;

import com.sun.tools.javac.Main;
import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.historyWindow.HistoryWindowFrame;
import view.payroll.EditPayrollWindow;
import view.payrollPreview.PayrollPreviewPanel;

import javax.swing.*;
import java.awt.*;

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
        setResizable(false);
        setTitle(ProgramLanguageProperties.getProperty("mainWindow"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        constraints.insets.set(20, 20, 20, 20); // Space between components.
        marginPanel.add(panel, constraints);
        add(marginPanel);

        pack();
        setPreferredSize(new Dimension(1000, 800));
        setSize(1000,800);
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
            gcbPreview.gridheight = 9; // Ocupa 9 filas
            gcbPreview.anchor = GridBagConstraints.NORTH;
            gcbPreview.fill = GridBagConstraints.BOTH; // Rellena el espacio horizontal y verticalmente

            add(previewPayrollScrollPane, gcbPreview);

            gcbPreview.gridx = 1;
            add(payrollListScroll, gcbPreview);

            gcbPreview.gridx = 2;
            gcbPreview.anchor = GridBagConstraints.NORTH;
            add(panelButtonsLabels, gcbPreview);
            panelButtonsLabels.setLayout(new GridBagLayout());
            GridBagConstraints gcbPanelButtonsLabels = new GridBagConstraints();
            gcbPanelButtonsLabels.gridx = 0; // Columna 0
            gcbPanelButtonsLabels.gridy = 0; // Fila 0
            gcbPanelButtonsLabels.fill = GridBagConstraints.HORIZONTAL;
            gcbPanelButtonsLabels.anchor = GridBagConstraints.NORTH;
            gcbPanelButtonsLabels.insets.set(5, 5, 5, 5);

            panelButtonsLabels.add(batchesLabel, gcbPanelButtonsLabels);

            gcbPanelButtonsLabels.gridy = 1;
            PayrollDBController.setComboBatchItems(payrollBatchesComboBox, false);
            panelButtonsLabels.add(payrollBatchesComboBox, gcbPanelButtonsLabels);
            payrollBatchesComboBox.addActionListener((e) -> updatePayrollListAction());

            gcbPanelButtonsLabels.gridy = 2;
            panelButtonsLabels.add(newBatchButton, gcbPanelButtonsLabels);

            gcbPanelButtonsLabels.gridy = 3;
            panelButtonsLabels.add(deleteBatchButton, gcbPanelButtonsLabels);
            deleteBatchButton.addActionListener((e) -> deleteBatchButtonAction());

            gcbPanelButtonsLabels.gridy = 4;
            panelButtonsLabels.add(sendToHistoryButton, gcbPanelButtonsLabels);
            sendToHistoryButton.addActionListener((e) ->  setBatchAcceptedButtonAction());

            gcbPanelButtonsLabels.gridy = 5;
            panelButtonsLabels.add(historyButton, gcbPanelButtonsLabels);
            historyButton.addActionListener((e) -> {HistoryWindowFrame.getINSTANCE().setVisible(true);});

            gcbPanelButtonsLabels.gridy = 6;
            panelButtonsLabels.add(payrollsLabel, gcbPanelButtonsLabels);

            gcbPanelButtonsLabels.gridy = 7;
            panelButtonsLabels.add(newPayrollButton, gcbPanelButtonsLabels);

            gcbPanelButtonsLabels.gridy = 8;
            panelButtonsLabels.add(editPayrollButton, gcbPanelButtonsLabels);
            editPayrollButton.addActionListener((e) -> {EditPayrollWindow.getINSTANCE().setVisible(true);});

            gcbPanelButtonsLabels.gridy = 9;
            panelButtonsLabels.add(deletePayrollButton, gcbPanelButtonsLabels);
            deletePayrollButton.addActionListener((e) -> deletePayrollButtonAction());

        }

        public JComboBox<PayrollBatch> getPayrollBatchesComboBox() {
            return payrollBatchesComboBox;
        }

        private void updatePayrollListAction(){
            PayrollBatch batch = (PayrollBatch) payrollBatchesComboBox.getSelectedItem();
            if (batch == null) {return;}
            PayrollDBController.getPayrollsByBatchId(batch);
            payrollList.setModel(PayrollDBController.getListOfPayrolls(batch));
        }

        private void deleteBatchButtonAction(){
            if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deleteBatchDialog"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            }
        }

        private void deletePayrollButtonAction(){
            if(JOptionPane.showConfirmDialog(this, ProgramLanguageProperties.getProperty("deletePayrollDialog"),ProgramLanguageProperties.getProperty("notice"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            }
        }
    }
}