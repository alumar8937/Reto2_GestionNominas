package view.payrollPreview;

import model.Payroll;
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
        constraints.anchor = GridBagConstraints.NORTH;
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

    public void setData(Payroll payroll) {
        setHeaderPanelLabels(": "+payroll.getCompany(), ": "+payroll.getAddress(), ": "+payroll.getCif(), ": "+payroll.getCcc(), ": "+payroll.getEmp_name(), ": "+payroll.getNif(), ": "+payroll.getNum_ss(), ": "+payroll.getProf_group());
        setCenterPanelLabels(": "+payroll.getTotal_dev(),": "+payroll.getTotal_deduc(),": "+payroll.getCompany(),": "+payroll.getDay()+"/"+payroll.getMonth()+"/"+payroll.getYear(), ": "+payroll.getTotal_net());
        //setCompanyPanelLabels();
    };

    public void setHeaderPanelLabels(String pp_company, String pp_domicile, String pp_cif, String pp_ccc, String pp_worker, String pp_nif, String pp_ssnumber, String pp_professionalgroup) {
        headerPanel.companyLabel.setText(ProgramLanguageProperties.getProperty("pp_company") + pp_company);
        headerPanel.domicileLabel.setText(ProgramLanguageProperties.getProperty("pp_domicile") + pp_domicile);
        headerPanel.cifLabel.setText(ProgramLanguageProperties.getProperty("pp_cif") + pp_cif);
        headerPanel.cccLabel.setText(ProgramLanguageProperties.getProperty("pp_ccc") + pp_ccc);
        headerPanel.workerLabel.setText(ProgramLanguageProperties.getProperty("pp_worker") + pp_worker);
        headerPanel.nifLabel.setText(ProgramLanguageProperties.getProperty("pp_nif") + pp_nif);
        headerPanel.ssNumberLabel.setText(ProgramLanguageProperties.getProperty("pp_ssnumber") + pp_ssnumber);
        headerPanel.professionalGroupLabel.setText(ProgramLanguageProperties.getProperty("pp_professionalgroup") + pp_professionalgroup);
    }

    public void setCenterPanelLabels(String pp_totalearned, String pp_totaldeductions, String pp_companysignature, String pp_time, String pp_irecieved) {
        centerPanel.totalearnedLabel.setText(ProgramLanguageProperties.getProperty("pp_totalearned") + pp_totalearned);
        centerPanel.totaldeductionsLabel.setText(ProgramLanguageProperties.getProperty("pp_totaldeductions") + pp_totaldeductions);
        centerPanel.companysignatureLabel.setText(ProgramLanguageProperties.getProperty("pp_companysignature") + pp_companysignature);
        centerPanel.timeLabel.setText(ProgramLanguageProperties.getProperty("pp_time") + pp_time);
        centerPanel.irecievedLabel.setText(ProgramLanguageProperties.getProperty("pp_irecieved") + pp_irecieved);
    }

    public void setCompanyPanelLabels(String pp_companycommoncontingencies, String pp_companymonthlyremuneration, String pp_companyspreadextrasalary, String pp_companytotal, String pp_companyprofessionalcontingencybase, String pp_companyatandep, String pp_companyunemployment, String pp_compamnyfp, String pp_companystandardextrahours, String pp_companygreaterforceextrahours, String pp_companyirpfbase, String pp_companybase, String pp_companytype, String pp_companygrant) {
        companyPanel.pp_companycommoncontingencies.setText(ProgramLanguageProperties.getProperty("pp_companycommoncontingencies") + pp_companycommoncontingencies);
        companyPanel.pp_companymonthlyremuneration.setText(ProgramLanguageProperties.getProperty("pp_companymonthlyremuneration") + pp_companymonthlyremuneration);
        companyPanel.pp_companyspreadextrasalary.setText(ProgramLanguageProperties.getProperty("pp_companyspreadextrasalary") + pp_companyspreadextrasalary);
        companyPanel.pp_companytotal.setText(ProgramLanguageProperties.getProperty("pp_companytotal") + pp_companytotal);
        companyPanel.pp_companyprofessionalcontingencybase.setText(ProgramLanguageProperties.getProperty("pp_companyprofessionalcontingencybase") + pp_companyprofessionalcontingencybase);
        companyPanel.pp_companyatandep.setText(ProgramLanguageProperties.getProperty("pp_companyatandep") + pp_companyatandep);
        companyPanel.pp_companyunemployment.setText(ProgramLanguageProperties.getProperty("pp_companyunemployment") + pp_companyunemployment);
        companyPanel.pp_compamnyfp.setText(ProgramLanguageProperties.getProperty("pp_compamnyfp") + pp_compamnyfp);
        companyPanel.pp_companystandardextrahours.setText(ProgramLanguageProperties.getProperty("pp_companystandardextrahours") + pp_companystandardextrahours);
        companyPanel.pp_companygreaterforceextrahours.setText(ProgramLanguageProperties.getProperty("pp_companygreaterforceextrahours") + pp_companygreaterforceextrahours);
        companyPanel.pp_companyirpfbase.setText(ProgramLanguageProperties.getProperty("pp_companyirpfbase") + pp_companyirpfbase);
        companyPanel.pp_companybase.setText(ProgramLanguageProperties.getProperty("pp_companybase") + pp_companybase);
        companyPanel.pp_companytype.setText(ProgramLanguageProperties.getProperty("pp_companytype") + pp_companytype);
        companyPanel.pp_companygrant.setText(ProgramLanguageProperties.getProperty("pp_companygrant") + pp_companygrant);
    }

    class HeaderPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        public JLabel companyLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_company"));
        public JLabel domicileLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_domicile"));
        public JLabel cifLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_cif"));
        public JLabel cccLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ccc"));
        public JLabel workerLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_worker"));
        public JLabel nifLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_nif"));
        public JLabel ssNumberLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_ssnumber"));
        public JLabel professionalGroupLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_professionalgroup"));

        private HeaderPanel() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLayout(new GridBagLayout());
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
            //add(taxGroupLabel, constraints);
        }
    }

    class CenterPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        public JLabel earningsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_earnings"));
        public JLabel salaryperceptionLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_salaryperception"));
        public JLabel totalearnedLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totalearned"));
        public JLabel deductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_deductions"));
        public JLabel totaldeductionsLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_totaldeductions"));
        public JLabel companysignatureLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_companysignature"));
        public JLabel timeLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_time"));
        public JLabel irecievedLabel = new JLabel(ProgramLanguageProperties.getProperty("pp_irecieved"));

        private CenterPanel() {
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
            constraints.gridx += 1;
            add(totalearnedLabel, constraints);
            constraints.gridx -= 1;

            constraints.gridy += 1;
            add(deductionsLabel, constraints);

            constraints.gridy += 1;

            constraints.gridy += 1;
            add(totaldeductionsLabel, constraints);

            constraints.gridy += 1;
            add(companysignatureLabel, constraints);

            constraints.gridy += 1;
            add(timeLabel, constraints);

            constraints.gridx += 1;
            add(irecievedLabel, constraints);
        }
    }

    class CompanyPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        public JLabel pp_companyheader = new JLabel(ProgramLanguageProperties.getProperty("pp_companyheader"));
        public JLabel pp_companycommoncontingencies = new JLabel(ProgramLanguageProperties.getProperty("pp_companycommoncontingencies"));
        public JLabel pp_companymonthlyremuneration = new JLabel(ProgramLanguageProperties.getProperty("pp_companymonthlyremuneration"));
        public JLabel pp_companyspreadextrasalary = new JLabel(ProgramLanguageProperties.getProperty("pp_companyspreadextrasalary"));
        public JLabel pp_companytotal = new JLabel(ProgramLanguageProperties.getProperty("pp_companytotal"));
        public JLabel pp_companyprofessionalcontingencybase = new JLabel(ProgramLanguageProperties.getProperty("pp_companyprofessionalcontingencybase"));
        public JLabel pp_companyatandep = new JLabel(ProgramLanguageProperties.getProperty("pp_companyatandep"));
        public JLabel pp_companyunemployment = new JLabel(ProgramLanguageProperties.getProperty("pp_companyunemployment"));
        public JLabel pp_compamnyfp = new JLabel(ProgramLanguageProperties.getProperty("pp_compamnyfp"));
        public JLabel pp_companystandardextrahours = new JLabel(ProgramLanguageProperties.getProperty("pp_companystandardextrahours"));
        public JLabel pp_companygreaterforceextrahours = new JLabel(ProgramLanguageProperties.getProperty("pp_companygreaterforceextrahours"));
        public JLabel pp_companyirpfbase = new JLabel(ProgramLanguageProperties.getProperty("pp_companyirpfbase"));
        public JLabel pp_companybase = new JLabel(ProgramLanguageProperties.getProperty("pp_companybase"));
        public JLabel pp_companytype = new JLabel(ProgramLanguageProperties.getProperty("pp_companytype"));
        public JLabel pp_companygrant = new JLabel(ProgramLanguageProperties.getProperty("pp_companygrant"));

        private CompanyPanel() {
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
