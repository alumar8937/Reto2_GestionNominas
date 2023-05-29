package view.payrollPreview;

import programLanguage.ProgramLanguageProperties;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

/**
 * @Author Pedro Marín Sanchis
 * Preview of a payroll
 */
public class PayrollPreviewPanel extends JPanel {
    private GridBagConstraints constraints = new GridBagConstraints();
    private HeaderPanel headerPanel = new HeaderPanel();
    private CenterPanel centerPanel = new CenterPanel();
    private CompanyPanel companyPanel = new CompanyPanel();

    public PayrollPreviewPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), ProgramLanguageProperties.getProperty("pp_title")));
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        add(headerPanel, constraints);

        constraints.insets.set(5, 0, 0, 0);
        constraints.weighty = 4;
        constraints.gridy = 1;
        add(centerPanel, constraints);

        constraints.weighty = 6;
        constraints.gridy = 2;
        add(companyPanel, constraints);
    }
    class HeaderPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        private JLabel companyLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_company"));
        private JLabel domicileLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_domicile"));
        private JLabel cifLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_cif"));
        private JLabel cccLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ccc"));
        private JLabel workerLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_worker"));
        private JLabel nifLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_nif"));
        private JLabel ssNumberLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ssnumber"));
        private JLabel professionalGroupLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_professionalgroup"));
        private JLabel taxGroupLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_taxgroup"));

        private HeaderPanel() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLayout(new GridBagLayout());
            constraints.insets.set(5, 5, 5, 5);
            constraints.anchor = GridBagConstraints.WEST; // Alignment within the cell
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridy = 0;
            constraints.gridx = 0;
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
            add(taxGroupLabel, constraints);
        }
    }

    class CenterPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        private JLabel liquidationperiodLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_liquidationperiod"));
        private JLabel numberofdaysLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_numberofdays"));
        private JLabel earningsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_earnings"));
        private JLabel salaryperceptionLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_salaryperception"));
        private JLabel basesalaryLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_basesalary"));
        private JLabel salarycomplementsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_salarycomplements"));
        private JLabel overtimeLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_overtime"));
        private JLabel extrapayLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_extrapay"));
        private JLabel nosalaryperceptionLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_nosalaryperception"));
        private JLabel indemnitiesLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_indemnities"));
        private JLabel socialsecurityindemnitiesLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_socialsecurityindemnities"));
        private JLabel indemnitiesformovingorfiringsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_indemnitiesformovingorfirings"));
        private JLabel othernonsalarialperceptionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_othernonsalarialperceptions"));
        private JLabel totalearnedLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totalearned"));
        private JLabel deductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_deductions"));
        private JLabel ssprovisionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ssprovisions"));
        private JLabel commoncontingenciesLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_commoncontingencies"));
        private JLabel unemploymentLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_unemployment"));
        private JLabel fpLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_fp"));
        private JLabel standardextrahoursLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_standardextrahours"));
        private JLabel greaterforceextrahoursLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_greaterforceextrahours"));
        private JLabel totalprovisionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totalprovisions"));
        private JLabel irpfLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_irpf"));
        private JLabel payadvancesLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_payadvances"));
        private JLabel spicevalueLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_spicevalue"));
        private JLabel otherdeductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_otherdeductions"));
        private JLabel totaldeductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totaldeductions"));
        private JLabel totalperceptionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totalperceptions"));
        private JLabel companysignatureLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_companysignature"));
        private JLabel timeLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_time"));
        private JLabel irecievedLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_irecieved"));

        private CenterPanel() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets.set(5, 5, 5, 5);
            constraints.anchor = GridBagConstraints.WEST; // Alignment within the cell
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridy = 0;
            constraints.gridx = 0;
            add(liquidationperiodLabel, constraints);

            constraints.gridx += 1;
            add(numberofdaysLabel, constraints);

            constraints.gridx -= 1;
            constraints.gridy += 1;
            add(earningsLabel, constraints);

            constraints.gridy += 1;
            add(salaryperceptionLabel, constraints);

            constraints.gridy += 1;
            add(basesalaryLabel, constraints);

            constraints.gridy += 1;
            add(salarycomplementsLabel, constraints);

            constraints.gridy += 1;
            add(overtimeLabel, constraints);

            constraints.gridy += 1;
            add(extrapayLabel, constraints);

            constraints.gridy += 1;
            add(nosalaryperceptionLabel, constraints);

            constraints.gridy += 1;
            add(indemnitiesLabel, constraints);

            constraints.gridy += 1;
            add(socialsecurityindemnitiesLabel, constraints);

            constraints.gridy += 1;
            add(indemnitiesformovingorfiringsLabel, constraints);

            constraints.gridy += 1;
            add(othernonsalarialperceptionsLabel, constraints);

            constraints.gridy += 1;
            add(totalearnedLabel, constraints);

            constraints.gridy += 1;
            add(deductionsLabel, constraints);

            constraints.gridy += 1;
            add(ssprovisionsLabel, constraints);

            constraints.gridy += 1;
            add(commoncontingenciesLabel, constraints);

            constraints.gridy += 1;
            add(unemploymentLabel, constraints);

            constraints.gridy += 1;
            add(fpLabel, constraints);

            constraints.gridy += 1;
            add(standardextrahoursLabel, constraints);

            constraints.gridy += 1;
            add(greaterforceextrahoursLabel, constraints);

            constraints.gridy += 1;
            add(totalprovisionsLabel, constraints);

            constraints.gridy += 1;
            add(irpfLabel, constraints);

            constraints.gridy += 1;
            add(payadvancesLabel, constraints);

            constraints.gridy += 1;
            add(spicevalueLabel, constraints);

            constraints.gridy += 1;
            add(otherdeductionsLabel, constraints);

            constraints.gridy += 1;
            add(totaldeductionsLabel, constraints);

            constraints.gridy += 1;
            add(totalperceptionsLabel, constraints);

            constraints.gridy += 1;
            add(companysignatureLabel, constraints);

            constraints.gridy += 1;
            add(timeLabel, constraints);

            constraints.gridy += 1;
            add(irecievedLabel, constraints);
        }
    }

    class CompanyPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        JLabel pp_companyheader = new JLabel(ProgramLanguageProperties.getProperty("pp_companyheader"));
        JLabel pp_companycommoncontingencies = new JLabel(ProgramLanguageProperties.getProperty("pp_companycommoncontingencies"));
        JLabel pp_companymonthlyremuneration = new JLabel(ProgramLanguageProperties.getProperty("pp_companymonthlyremuneration"));
        JLabel pp_companyspreadextrasalary = new JLabel(ProgramLanguageProperties.getProperty("pp_companyspreadextrasalary"));
        JLabel pp_companytotal = new JLabel(ProgramLanguageProperties.getProperty("pp_companytotal"));
        JLabel pp_companyprofessionalcontingencybase = new JLabel(ProgramLanguageProperties.getProperty("pp_companyprofessionalcontingencybase"));
        JLabel pp_companyatandep = new JLabel(ProgramLanguageProperties.getProperty("pp_companyatandep"));
        JLabel pp_companyunemployment = new JLabel(ProgramLanguageProperties.getProperty("pp_companyunemployment"));
        JLabel pp_compamnyfp = new JLabel(ProgramLanguageProperties.getProperty("pp_compamnyfp"));
        JLabel pp_companystandardextrahours = new JLabel(ProgramLanguageProperties.getProperty("pp_companystandardextrahours"));
        JLabel pp_companygreaterforceextrahours = new JLabel(ProgramLanguageProperties.getProperty("pp_companygreaterforceextrahours"));
        JLabel pp_companyirpfbase = new JLabel(ProgramLanguageProperties.getProperty("pp_companyirpfbase"));
        JLabel pp_companybase = new JLabel(ProgramLanguageProperties.getProperty("pp_companybase"));
        JLabel pp_companytype = new JLabel(ProgramLanguageProperties.getProperty("pp_companytype"));
        JLabel pp_companygrant = new JLabel(ProgramLanguageProperties.getProperty("pp_companygrant"));
        private CompanyPanel() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLayout(new GridBagLayout());
            constraints.insets.set(5, 5, 5, 5);
            constraints.anchor = GridBagConstraints.WEST; // Alignment within the cell
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridy = 0;
            constraints.gridx = 0;

            constraints.gridy += 1;
            add(pp_companyheader, constraints);

            constraints.gridy += 1;
            add(pp_companycommoncontingencies, constraints);

            constraints.gridy += 1;
            add(pp_companymonthlyremuneration, constraints);

            constraints.gridy += 1;
            add(pp_companyspreadextrasalary, constraints);

            constraints.gridx += 1;
            add(pp_companygrant, constraints);
            constraints.gridx -= 1;

            constraints.gridy += 1;
            add(pp_companytotal, constraints);

            constraints.gridy += 1;
            add(pp_companyprofessionalcontingencybase, constraints);

            constraints.gridy += 1;
            add(pp_companyatandep, constraints);

            constraints.gridy += 1;
            add(pp_companyunemployment, constraints);

            constraints.gridy += 1;
            add(pp_compamnyfp, constraints);

            constraints.gridy += 1;
            add(pp_companystandardextrahours, constraints);

            constraints.gridy += 1;
            add(pp_companygreaterforceextrahours, constraints);

            constraints.gridy += 1;
            add(pp_companyirpfbase, constraints);

            constraints.gridy += 1;
            add(pp_companybase, constraints);

            constraints.gridy += 1;
            add(pp_companytype, constraints);

        }
    }

}
