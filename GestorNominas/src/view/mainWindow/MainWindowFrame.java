package view.mainWindow;

import com.sun.tools.javac.Main;
import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.historyWindow.HistoryWindowFrame;
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

    private MainWindowFrame() {
        setResizable(false);
        setTitle(ProgramLanguageProperties.getProperty("mainWindow"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainWindowJPanel panel = new mainWindowJPanel();

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

        JList payrollList = new JList();

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

            GridBagConstraints gcbPreview = new GridBagConstraints();
            gcbPreview.gridx = 0; // Columna 0
            gcbPreview.gridy = 0; // Fila 0
            gcbPreview.gridwidth = 1; // Ocupa 1 columna
            gcbPreview.gridheight = 9; // Ocupa 9 filas
            gcbPreview.fill = GridBagConstraints.BOTH; // Rellena el espacio horizontal y verticalmente

            add(previewPayrollScrollPane, gcbPreview);

            gcbPreview.gridx = 1;
            add(payrollListScroll, gcbPreview);

            GridBagConstraints gcbLabelsButtons = new GridBagConstraints();
            gcbLabelsButtons.gridx = 2;
            gcbLabelsButtons.gridy = 0;
            gcbLabelsButtons.gridwidth = 1; // Ocupa 1 columna
            gcbLabelsButtons.gridheight = 1; // Ocupa 1 filas
            gcbLabelsButtons.fill = GridBagConstraints.HORIZONTAL; // Rellena el espacio horizontal
            gcbLabelsButtons.insets.set(5, 5, 5, 5);

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

            gcbLabelsButtons.gridy = 4;
            add(sendToHistoryButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 5;
            add(historyButton, gcbLabelsButtons);
            historyButton.addActionListener((e) -> new HistoryWindowFrame());

            gcbLabelsButtons.gridy = 6;
            add(payrollsLabel, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 7;
            add(newPayrollButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 8;
            add(editPayrollButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 9;
            add(deletePayrollButton, gcbLabelsButtons);
            deletePayrollButton.addActionListener((e) -> deletePayrollButtonAction());

        }

        public JComboBox<PayrollBatch> getPayrollBatchesComboBox() {
            return payrollBatchesComboBox;
        }

        private void updatePayrollListAction(){
            PayrollBatch batch = (PayrollBatch) payrollBatchesComboBox.getSelectedItem();
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