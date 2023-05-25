package view.mainWindow;

import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @Author David Serna Mateu
 * Displays the main panel with most of the info
 */

public class MainWindowFrame extends JFrame {

    private JPanel marginPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public MainWindowFrame() {
        setTitle(ProgramLanguageProperties.getProperty("mainWindow"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mainWindowJPanel panel = new mainWindowJPanel();

        constraints.insets.set(20, 20, 20, 20); // Space between components.
        marginPanel.add(panel, constraints);
        add(marginPanel);

        pack();
        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }

    class mainWindowJPanel extends JPanel {
        JPanel previewPayroll = new JPanel();

        JList payrollList = new JList();

        JScrollPane payrollListScroll = new JScrollPane(payrollList);

        JLabel batchesLabel = new JLabel(ProgramLanguageProperties.getProperty("batchesLabel"));
        JLabel payrollsLabel = new JLabel(ProgramLanguageProperties.getProperty("payrollsLabel"));

        JComboBox<PayrollBatch> payrollBatchesComboBox = new JComboBox<PayrollBatch>();

        JButton newBatchButton = new JButton(ProgramLanguageProperties.getProperty("newBatchButton"));
        JButton deleteBatchButton = new JButton(ProgramLanguageProperties.getProperty("deleteBatchButton"));
        JButton saveBatchButton = new JButton(ProgramLanguageProperties.getProperty("saveBatchButton"));
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
            gcbPreview.gridheight = 11; // Ocupa 11 filas
            gcbPreview.fill = GridBagConstraints.BOTH; // Rellena el espacio horizontal y verticalmente

            add(previewPayroll, gcbPreview);

            gcbPreview.gridx = 1;
            add(payrollListScroll, gcbPreview);

            GridBagConstraints gcbLabelsButtons = new GridBagConstraints();
            gcbLabelsButtons.gridx = 2;
            gcbLabelsButtons.gridy = 0;
            gcbLabelsButtons.gridwidth = 1; // Ocupa 1 columna
            gcbLabelsButtons.gridheight = 1; // Ocupa 1 filas
            gcbLabelsButtons.fill = GridBagConstraints.BOTH; // Rellena el espacio horizontal y verticalmente
            gcbLabelsButtons.insets.set(5, 5, 5, 5);

            add(batchesLabel, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 1;
            add(payrollBatchesComboBox, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 2;
            add(newBatchButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 3;
            add(deleteBatchButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 4;
            add(saveBatchButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 5;
            add(sendToHistoryButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 6;
            add(historyButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 7;
            add(payrollsLabel, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 8;
            add(newPayrollButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 9;
            add(editPayrollButton, gcbLabelsButtons);

            gcbLabelsButtons.gridy = 10;
            add(deletePayrollButton, gcbLabelsButtons);

            previewPayroll.add(new JButton("Payroll Preview Goes Here"));

        }
    }
}