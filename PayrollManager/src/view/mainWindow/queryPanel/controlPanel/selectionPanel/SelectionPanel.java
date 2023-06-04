package view.mainWindow.queryPanel.controlPanel.selectionPanel;

import model.Payroll;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public abstract class SelectionPanel extends JPanel {
    protected ActionListener listener;

    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public void sendUpdateActionEvent() {
        if (listener == null) {return;}
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update");
        listener.actionPerformed(event);
    }

    public abstract ArrayList<Payroll> getPayrollList();
}
