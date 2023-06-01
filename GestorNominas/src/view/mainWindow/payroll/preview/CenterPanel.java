package view.mainWindow.payroll.preview;

import model.Contingency;
import model.Perception;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;

/**
 * @author Pedro Marín Sanchis
 * A custom JPanel used as the center panel in the payroll preview window.
 */
class CenterPanel extends JPanel {
    private final GridBagConstraints constraints = new GridBagConstraints();
    public JLabel earningsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_earnings"));
    public JLabel salaryperceptionLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_salaryperception"));
    public JList<Perception> perceptions = new JList<Perception>();
    public JLabel totalearnedLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totalearned"));
    public JLabel deductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_deductions"));
    public JList<Contingency> contingencies = new JList<Contingency>();
    public JLabel totaldeductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totaldeductions"));
    public JLabel companysignatureLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_companysignature"));
    public JLabel timeLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_time"));
    public JLabel irecievedLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_irecieved"));
    public JLabel pp_IRPF = new JLabel(ProgramLanguageProperties.getProperty("pp_IRPF"));

    /**
     * Constructs a new CenterPanel object.
     * Initializes and configures the components within the panel.
     */
    CenterPanel() {
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
        add(earningsLabel, constraints);

        constraints.gridy += 1;
        add(salaryperceptionLabel, constraints);

        constraints.gridy += 1;
        constraints.gridwidth = 2;
        add(perceptions, constraints);
        perceptions.setEnabled(false);

        constraints.gridwidth = 1;
        constraints.gridy += 1;
        constraints.gridx += 1;
        add(totalearnedLabel, constraints);
        constraints.gridx -= 1;

        constraints.gridy += 1;
        add(deductionsLabel, constraints);

        constraints.gridy += 1;
        constraints.gridwidth = 2;
        add(contingencies, constraints);
        contingencies.setEnabled(false);

        constraints.gridwidth = 1;

        constraints.gridy += 1;
        add(pp_IRPF, constraints);

        constraints.gridy += 1;
        constraints.gridx += 1;
        add(totaldeductionsLabel, constraints);
        constraints.gridx -= 1;

        constraints.gridy += 1;
        add(companysignatureLabel, constraints);

        constraints.gridy += 1;
        add(timeLabel, constraints);

        constraints.gridx += 1;
        add(irecievedLabel, constraints);
    }
}