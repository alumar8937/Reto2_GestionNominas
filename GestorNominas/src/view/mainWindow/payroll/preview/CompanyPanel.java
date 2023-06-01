package view.mainWindow.payroll.preview;

import model.Contingency;
import model.Retention;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;

class CompanyPanel extends JPanel {
    private GridBagConstraints constraints = new GridBagConstraints();
    public JLabel pp_companyheader = new JLabel(ProgramLanguageProperties.getProperty("pp_companyheader"));
    public JList<Contingency> contingencies = new JList<Contingency>();
    public JList<Retention> retentions = new JList<Retention>();
    public JLabel pp_companytotal = new JLabel(ProgramLanguageProperties.getProperty("pp_companytotal"));
    public JLabel pp_ATEP = new JLabel(ProgramLanguageProperties.getProperty("pp_ATEP"));

    /**
     * Constructs a new CompanyPanel object.
     * Initializes and configures the components within the panel.
     */
    CompanyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridBagLayout());
        constraints.insets.set(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        constraints.gridy += 1;
        add(pp_companyheader, constraints);

        constraints.gridy += 1;
        constraints.gridwidth = 2;
        add(contingencies, constraints);
        contingencies.setEnabled(false);

        constraints.gridy += 1;
        add(pp_ATEP, constraints);

        constraints.gridy += 1;
        constraints.gridwidth = 2;
        add(retentions, constraints);
        retentions.setEnabled(false);

        constraints.gridwidth = 1;
        constraints.gridy += 1;
        constraints.gridx += 1;
        add(pp_companytotal, constraints);
    }
}
