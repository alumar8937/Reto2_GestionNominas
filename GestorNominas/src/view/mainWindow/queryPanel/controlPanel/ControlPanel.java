package view.mainWindow.queryPanel.controlPanel;

import model.Payroll;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.SelectionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private void addActionListeners() {
        selectionPanel.addActionListener((e) -> {
            updatePayrollList();
            sendUpdateActionEvent();
        });
        payrollJList.addListSelectionListener((e) -> sendUpdateActionEvent());
        payrollButtonPanel.addActionListener((e) -> updatePayrollList());
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
