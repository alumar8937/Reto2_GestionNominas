package view.mainWindow.payroll.preview;

import programLanguage.ProgramLanguageProperties;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

class HeaderPanel extends JPanel {
    public JLabel companyLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_company"));
    public JLabel domicileLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_domicile"));
    public JLabel cifLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_cif"));
    public JLabel cccLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ccc"));
    public JLabel workerLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_worker"));
    public JLabel nifLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_nif"));
    public JLabel ssNumberLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ssnumber"));
    public JLabel professionalGroupLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_professionalgroup"));

    /**
     * Constructs a new HeaderPanel object.
     * Initializes and configures the components within the panel.
     */
    HeaderPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(companyLabel, constraints);

        constraints.gridy = 1;
        add(domicileLabel, constraints);

        constraints.gridy = 2;
        add(cifLabel, constraints);

        constraints.gridy = 3;
        add(cccLabel, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        add(workerLabel, constraints);

        constraints.gridy = 1;
        add(nifLabel, constraints);

        constraints.gridy = 2;
        add(ssNumberLabel, constraints);

        constraints.gridy = 3;
        add(professionalGroupLabel, constraints);

        constraints.gridy = 4;
    }
}
