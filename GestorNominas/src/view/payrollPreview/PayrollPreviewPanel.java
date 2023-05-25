package view.payrollPreview;

import javax.swing.JPanel;

/**
 * @Author Pedro Marín Sanchis
 * Preview of a payroll
 */
public class PayrollPreviewPanel extends JPanel {
    private HeaderPanel headerPanel = new HeaderPanel();
    public PayrollPreviewPanel() {
        add(headerPanel);

    }
    class HeaderPanel extends JPanel{

    }
}
