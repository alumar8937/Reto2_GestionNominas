package view.mainWindow.queryPanel.controlPanel;

import model.Payroll;
import view.mainWindow.queryPanel.controlPanel.selectionPanel.SelectionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private SelectionPanel selectionPanel = null;
    private JList<Payroll> payrollJList = new JList<Payroll>();
    private JScrollPane payrollJListScrollPane = new JScrollPane(payrollJList);
    private PayrollButtonPanel payrollButtonPanel = new PayrollButtonPanel(payrollJList);
    protected ActionListener listener;

    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public ControlPanel(SelectionPanel selectionPanel) {
        this.selectionPanel = selectionPanel;
        placeComponents();
        addActionListeners();
    }

    private void placeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.BOTH;
        add(payrollJListScrollPane, constraints);
        payrollJListScrollPane.setMinimumSize(new Dimension(300,100));

        constraints.gridx += 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(selectionPanel, constraints);

        constraints.gridy += 1;
        add(payrollButtonPanel, constraints);
    }

    private void addActionListeners() {
        selectionPanel.addActionListener((e) -> updatePayrollList());
        payrollJList.addListSelectionListener((e) -> sendUpdateActionEvent());
        payrollButtonPanel.addActionListener((e) -> updatePayrollList());
    }

    public void updatePayrollList() {
        if (selectionPanel.getPayrollList() == null) {return;}
        DefaultListModel<Payroll> model = new DefaultListModel<Payroll>();
        for(Payroll p : selectionPanel.getPayrollList()){
            model.addElement(p);
        }
        payrollJList.setModel(model);
    }

    public void sendUpdateActionEvent() {
        if (listener == null) {return;}
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update");
        listener.actionPerformed(event);
    }

    public JList<Payroll> getPayrollJList() {
        return payrollJList;
    }
}
