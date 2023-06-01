package view.mainWindow.payroll.preview;

import model.*;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        if (payroll == null) {return;}
        setHeaderPanelLabels(": "+payroll.getCompany(), ": "+payroll.getAddress(), ": "+payroll.getCif(), ": "+payroll.getCcc(), ": "+payroll.getEmp_name(), ": "+payroll.getNif(), ": "+payroll.getNum_ss(), ": "+payroll.getProf_group());
        setCenterPanelLabels(": "+payroll.getIRPF(),": "+payroll.getTotal_dev(),": "+payroll.getTotal_deduc(),": "+payroll.getCompany(),": "+payroll.getDay()+"/"+payroll.getMonth()+"/"+payroll.getYear(), ": "+payroll.getTotal_net());
        setCompanyPanelLabels(": "+payroll.getATEP(),": "+payroll.getAp_company());
        fillPerceptionList(payroll.getPerceptions());
        fillCompanyContingenciesList(payroll.getContingencies_Com());
        fillEmployeeContingenciesList(payroll.getContingencies_Emp());
    };

    public void clearData() {
        setHeaderPanelLabels("", "", "", "", "", "", "", "");
        setCenterPanelLabels("", "", "", "", "", "");
        setCompanyPanelLabels("", "");
        fillPerceptionList(null);
        fillCompanyContingenciesList(null);
        fillEmployeeContingenciesList(null);
    }

    private void fillPerceptionList(ArrayList<Perception> perceptions) {
        DefaultListModel<Perception> model = new DefaultListModel<Perception>();
        if (perceptions != null) {
            for (Perception p: perceptions) {
                model.addElement(p);
            }
        }
        centerPanel.perceptions.setModel(model);
    }

    private void fillEmployeeContingenciesList(ArrayList<Contingency> contingencies) {
        DefaultListModel<Contingency> model = new DefaultListModel<Contingency>();
        if (contingencies != null) {
            for (Contingency c: contingencies) {
                model.addElement(c);
            }
        }
        centerPanel.contingencies.setModel(model);
    }

    private void fillCompanyContingenciesList(ArrayList<Contingency> contingencies) {
        DefaultListModel<Contingency> model = new DefaultListModel<Contingency>();
        if (contingencies != null) {
            for (Contingency c: contingencies) {
                model.addElement(c);
            }
        }
        companyPanel.contingencies.setModel(model);
    }

    private void fillRetentionList(ArrayList<Retention> retentions) {
        DefaultListModel<Retention> model = new DefaultListModel<Retention>();
        for (Retention r: retentions) {
            model.addElement(r);
        }
        companyPanel.retentions.setModel(model);
    }

    private void setHeaderPanelLabels(String pp_company, String pp_domicile, String pp_cif, String pp_ccc, String pp_worker, String pp_nif, String pp_ssnumber, String pp_professionalgroup) {
        headerPanel.companyLabel.setText(ProgramLanguageProperties.getProperty("pp_company") + pp_company);
        headerPanel.domicileLabel.setText(ProgramLanguageProperties.getProperty("pp_domicile") + pp_domicile);
        headerPanel.cifLabel.setText(ProgramLanguageProperties.getProperty("pp_cif") + pp_cif);
        headerPanel.cccLabel.setText(ProgramLanguageProperties.getProperty("pp_ccc") + pp_ccc);
        headerPanel.workerLabel.setText(ProgramLanguageProperties.getProperty("pp_worker") + pp_worker);
        headerPanel.nifLabel.setText(ProgramLanguageProperties.getProperty("pp_nif") + pp_nif);
        headerPanel.ssNumberLabel.setText(ProgramLanguageProperties.getProperty("pp_ssnumber") + pp_ssnumber);
        headerPanel.professionalGroupLabel.setText(ProgramLanguageProperties.getProperty("pp_professionalgroup") + pp_professionalgroup);
    }

    private void setCenterPanelLabels(String IRPF, String pp_totalearned, String pp_totaldeductions, String pp_companysignature, String pp_time, String pp_irecieved) {
        centerPanel.pp_IRPF.setText(ProgramLanguageProperties.getProperty("pp_IRPF") + IRPF + "%");
        centerPanel.totalearnedLabel.setText(ProgramLanguageProperties.getProperty("pp_totalearned") + pp_totalearned);
        centerPanel.totaldeductionsLabel.setText(ProgramLanguageProperties.getProperty("pp_totaldeductions") + pp_totaldeductions);
        centerPanel.companysignatureLabel.setText(ProgramLanguageProperties.getProperty("pp_companysignature") + pp_companysignature);
        centerPanel.timeLabel.setText(ProgramLanguageProperties.getProperty("pp_time") + pp_time);
        centerPanel.irecievedLabel.setText(ProgramLanguageProperties.getProperty("pp_irecieved") + pp_irecieved);
    }

    private void setCompanyPanelLabels(String ATEP, String pp_companytotal) {
        companyPanel.pp_companytotal.setText(ProgramLanguageProperties.getProperty("pp_companytotal") + pp_companytotal);
        companyPanel.pp_ATEP.setText(ProgramLanguageProperties.getProperty("pp_ATEP") + ATEP + "%");
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
        }
    }

    class CenterPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
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

    class CompanyPanel extends JPanel {
        private GridBagConstraints constraints = new GridBagConstraints();
        public JLabel pp_companyheader = new JLabel(ProgramLanguageProperties.getProperty("pp_companyheader"));
        public JList<Contingency> contingencies = new JList<Contingency>();
        public JList<Retention> retentions = new JList<Retention>();
        public JLabel pp_companytotal = new JLabel(ProgramLanguageProperties.getProperty("pp_companytotal"));
        public JLabel pp_ATEP = new JLabel(ProgramLanguageProperties.getProperty("pp_ATEP"));

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

}
