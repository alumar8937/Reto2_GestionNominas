package view.historyWindow;

import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.mainWindow.MainWindowFrame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author David Serna Mateu
 * Displays the history panel with the info of the history
 */
public class HistoryWindowFrame extends JFrame {
    private JPanel marginPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private static HistoryWindowFrame INSTANCE = null;
    private historyWindowJPanel panel = new historyWindowJPanel();

    public HistoryWindowFrame() {
        setTitle(ProgramLanguageProperties.getProperty("historyBatchesWindow"));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        constraints.insets.set(20, 20, 20, 20); // Space between components.

        marginPanel.add(panel, constraints);
        add(marginPanel);

        pack();
        FrameUtils.centerWindowOnScreen(this);
        revalidate();
        repaint();
        setVisible(true);
    }

    public static HistoryWindowFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new HistoryWindowFrame();
        }
        return INSTANCE;
    }

    public historyWindowJPanel getPanel() {
        return panel;
    }

    public class historyWindowJPanel extends JPanel {

        JPanel previewPayroll = new JPanel();

        JLabel batchLabel = new JLabel(ProgramLanguageProperties.getProperty("batchLabel"));
        JLabel payrollLabel = new JLabel(ProgramLanguageProperties.getProperty("payrollLabel"));

        JButton recoveryBatchButton = new JButton(ProgramLanguageProperties.getProperty("recoveryBatchButton"));

        JComboBox<PayrollBatch> payrollBatchesComboBox = new JComboBox<PayrollBatch>();
        JComboBox<Payroll> payrollComboBox = new JComboBox<Payroll>();

        public historyWindowJPanel() {

            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // Columna 0
            constraints.gridy = 0; // Fila 0
            constraints.gridwidth = 1; // Ocupa 1 columna
            constraints.gridheight = 1; // Ocupa 1 filas
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets.set(5, 5, 5, 5);
            add(batchLabel, constraints);

            constraints.gridy = 1; // Fila 1
            PayrollDBController.setComboBatchItems(payrollBatchesComboBox, true);
            payrollBatchesComboBox.addActionListener((e) -> updatePayrollCombo());
            add(payrollBatchesComboBox, constraints);

            constraints.gridy = 2; // Fila 2
            add(payrollLabel, constraints);

            constraints.gridy = 3; //Fila 3
            add(payrollComboBox, constraints);

            constraints.gridy = 4; //Fila 4
            add(recoveryBatchButton, constraints);
            recoveryBatchButton.addActionListener((e) -> setRecoveryBatchButton());

            constraints.gridy = 5; //Fila 5
            constraints.gridheight = 7; // Ocupa 7 filas
            add(previewPayroll, constraints);

            previewPayroll.add(new JButton("Payroll Preview Goes Here"));

            JButton recoveryBatchButton = new JButton("Recovery Batch");
            recoveryBatchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setRecoveryBatchButton();
                }
            });
        }

        public JComboBox<PayrollBatch> getPayrollBatchesComboBox() {
            return payrollBatchesComboBox;
        }

        private void updatePayrollCombo() { // Javier Blasco Gómez
            PayrollBatch batch = (PayrollBatch) payrollBatchesComboBox.getSelectedItem();
            if (batch == null) {return;}
            PayrollDBController.getPayrollsByBatchId(batch);
            PayrollDBController.setComboPayrollItem(payrollComboBox, batch);
        }

        private void setRecoveryBatchButton() { //Raul Simarro Navarro
            if (payrollBatchesComboBox.getSelectedItem() == null) {return;}
            int batchID = ((PayrollBatch) payrollBatchesComboBox.getSelectedItem()).getID();
            PayrollDBController.setBatchAccepted(batchID, false);
            PayrollDBController.setComboBatchItems(payrollBatchesComboBox, true);
            PayrollDBController.setComboBatchItems(MainWindowFrame.getINSTANCE().getPanel().getPayrollBatchesComboBox(), false);
        }


    }
}