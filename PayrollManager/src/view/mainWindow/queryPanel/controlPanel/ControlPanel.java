package view.mainWindow.queryPanel.controlPanel;

import model.Payroll;
import programLanguage.ProgramLanguageProperties;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.SelectionPanel;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Panel that contains a list of selected payrolls and a SelectionPanel (as a way of getting the desired Payrolls)
 *
 * @author Pedro Marín Sanchis
 */
public class ControlPanel extends JPanel {
    private SelectionPanel selectionPanel = null;
    private final JList<Payroll> payrollJList = new JList<Payroll>();
    private final JScrollPane payrollJListScrollPane = new JScrollPane(payrollJList);
    private final PayrollButtonPanel payrollButtonPanel = new PayrollButtonPanel(payrollJList);
    private final JPanel buttonContainerPanel = new JPanel();
    protected ActionListener listener;

    /**
     * Adds an action listener to the control panel.
     *
     * @param listener the action listener to be added
     */
    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    /**
     * Constructs a control panel with the specified selection panel.
     *
     * @param selectionPanel the selection panel to be used
     */
    public ControlPanel(SelectionPanel selectionPanel) {
        this.selectionPanel = selectionPanel;
        placeComponents();
        addActionListeners();
    }

    /**
     * Places components on the JPanel.
     */
    private void placeComponents() {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(500, 100));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(0, 0, 0, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight += 1;
        add(payrollJListScrollPane, constraints);

        constraints.insets.set(0, 0, 0, 0);
        constraints.gridheight -= 1;
        constraints.gridx += 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        buttonContainerPanel.setLayout(new BoxLayout(buttonContainerPanel, BoxLayout.Y_AXIS));
        buttonContainerPanel.add(selectionPanel);
        buttonContainerPanel.add(payrollButtonPanel);
        add(buttonContainerPanel, constraints);

        revalidate();
        repaint();
    }

    /**
     * Adds respective action listeners.
     */
    private void addActionListeners() {
        selectionPanel.addActionListener((e) -> {
            updatePayrollList();
            sendUpdateActionEvent();
        });
        payrollJList.addListSelectionListener((e) -> sendUpdateActionEvent());
        payrollButtonPanel.addActionListener((e) -> payrollListenerMethod(e));
    }

    /**
     * Defines behaviour based on incoming Action Events from a PayrollButtonPanel.
     * @param e Action Event
     */
    private void payrollListenerMethod(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Update")) {updatePayrollList();}
        if (e.getActionCommand().equalsIgnoreCase("ExportSEPA")) {exportSEPA();}
    }

    /**
     * Updates the payroll list based on the selection panel.
     */
    public void updatePayrollList() {
        DefaultListModel<Payroll> model = new DefaultListModel<Payroll>();
        if (selectionPanel.getPayrollList() != null) {
            for (Payroll p : selectionPanel.getPayrollList()) {
                model.addElement(p);
            }
        }
        payrollJList.setModel(model);
        revalidate();
        repaint();
    }

    /**
     * Exports an XML SEPA document.
     */
    private void exportSEPA() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Check if a directory was chosen
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            controller.sepa.SEPADocumentGenerator.createSEPADocument(selectionPanel.getPayrollList(), fileChooser.getSelectedFile(), "PayrollBatch_ID0000", currentDateTime.format(formatter), selectionPanel.getPayrollList().size(), 100, "Equipo3");
            JOptionPane.showMessageDialog(this, ProgramLanguageProperties.getProperty("operationCompleted"));
        }
    }

    /**
     * Sends an update action event to the registered listener.
     */
    public void sendUpdateActionEvent() {
        if (listener == null) {
            return;
        }
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update");
        listener.actionPerformed(event);
    }

    /**
     * Returns the payroll JList.
     *
     * @return the payroll JList
     */
    public JList<Payroll> getPayrollJList() {
        return payrollJList;
    }
}
