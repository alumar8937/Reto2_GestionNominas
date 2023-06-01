package view.mainWindow.payroll.preview;

import model.*;
import programLanguage.ProgramLanguageProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A JPanel used for displaying a preview of a payroll.
 * The panel contains header information, center panel with earnings, deductions, and contingencies,
 * and a company panel with company-specific information.
 *
 * @author Pedro Marín Sanchis
 */
public class PayrollPreviewPanel extends JPanel {

    private final HeaderPanel headerPanel = new HeaderPanel();
    private final CenterPanel centerPanel = new CenterPanel();
    private final CompanyPanel companyPanel = new CompanyPanel();

    /**
     * Constructs a new PayrollPreviewPanel.
     * Initializes the panel by placing components in their appropriate positions.
     */
    public PayrollPreviewPanel() {
        placeComponents();
    }

    /**
     * Places the components within the panel using the GridBagLayout.
     */
    private void placeComponents() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
                ProgramLanguageProperties.getProperty("pp_title")));
        GridBagConstraints constraints = new GridBagConstraints();
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

    /**
     * Sets the data of the payroll to be displayed in the preview panel.
     *
     * @param payroll The Payroll object containing the data to be displayed.
     */
    public void setData(Payroll payroll) {
        if (payroll == null) {
            return;
        }
        setHeaderPanelLabels(": " + payroll.getCompany(), ": " + payroll.getAddress(), ": " + payroll.getCif(),
                ": " + payroll.getCcc(), ": " + payroll.getEmp_name(), ": " + payroll.getNif(),
                ": " + payroll.getNum_ss(), ": " + payroll.getProf_group());
        setCenterPanelLabels(": " + payroll.getIRPF(), ": " + payroll.getTotal_dev(),
                ": " + payroll.getTotal_deduc(), ": " + payroll.getCompany(),
                ": " + payroll.getDay() + "/" + payroll.getMonth() + "/" + payroll.getYear(),
                ": " + payroll.getTotal_net());
        setCompanyPanelLabels(": " + payroll.getATEP(), ": " + payroll.getAp_company());
        fillPerceptionList(payroll.getPerceptions());
        fillCompanyContingenciesList(payroll.getContingencies_Com());
        fillEmployeeContingenciesList(payroll.getContingencies_Emp());
    }

    /**
     * Clears the data in the preview panel by setting all labels and lists to empty values.
     */
    public void clearData() {
        setHeaderPanelLabels("", "", "", "", "", "", "", "");
        setCenterPanelLabels("", "", "", "", "", "");
        setCompanyPanelLabels("", "");
        fillPerceptionList(null);
        fillCompanyContingenciesList(null);
        fillEmployeeContingenciesList(null);
    }

    /**
     * Fills the perception JList with the given list of perceptions.
     *
     * @param perceptions The list of perceptions to be displayed in the JList.
     */
    private void fillPerceptionList(ArrayList<Perception> perceptions) {
        DefaultListModel<Perception> model = new DefaultListModel<>();
        if (perceptions != null) {
            for (Perception p : perceptions) {
                model.addElement(p);
            }
        }
        centerPanel.perceptions.setModel(model);
    }

    /**
     * Fills the employee contingencies JList with the given list of contingencies.
     *
     * @param contingencies The list of contingencies to be displayed in the JList.
     */
    private void fillEmployeeContingenciesList(ArrayList<Contingency> contingencies) {
        DefaultListModel<Contingency> model = new DefaultListModel<>();
        if (contingencies != null) {
            for (Contingency c : contingencies) {
                model.addElement(c);
            }
        }
        centerPanel.contingencies.setModel(model);
    }

    /**
     * Fills the company contingencies JList with the given list of contingencies.
     *
     * @param contingencies The list of contingencies to be displayed in the JList.
     */
    private void fillCompanyContingenciesList(ArrayList<Contingency> contingencies) {
        DefaultListModel<Contingency> model = new DefaultListModel<>();
        if (contingencies != null) {
            for (Contingency c : contingencies) {
                model.addElement(c);
            }
        }
        companyPanel.contingencies.setModel(model);
    }

    /**
     * Sets the labels in the header panel with the given values.
     *
     * @param pp_company          The company label value.
     * @param pp_domicile         The domicile label value.
     * @param pp_cif              The CIF label value.
     * @param pp_ccc              The CCC label value.
     * @param pp_worker           The worker label value.
     * @param pp_nif              The NIF label value.
     * @param pp_ssnumber         The SS number label value.
     * @param pp_professionalgroup The professional group label value.
     */
    private void setHeaderPanelLabels(String pp_company, String pp_domicile, String pp_cif, String pp_ccc,
                                      String pp_worker, String pp_nif, String pp_ssnumber, String pp_professionalgroup) {
        headerPanel.companyLabel.setText(ProgramLanguageProperties.getProperty("pp_company") + pp_company);
        headerPanel.domicileLabel.setText(ProgramLanguageProperties.getProperty("pp_domicile") + pp_domicile);
        headerPanel.cifLabel.setText(ProgramLanguageProperties.getProperty("pp_cif") + pp_cif);
        headerPanel.cccLabel.setText(ProgramLanguageProperties.getProperty("pp_ccc") + pp_ccc);
        headerPanel.workerLabel.setText(ProgramLanguageProperties.getProperty("pp_worker") + pp_worker);
        headerPanel.nifLabel.setText(ProgramLanguageProperties.getProperty("pp_nif") + pp_nif);
        headerPanel.ssNumberLabel.setText(ProgramLanguageProperties.getProperty("pp_ssnumber") + pp_ssnumber);
        headerPanel.professionalGroupLabel.setText(ProgramLanguageProperties.getProperty("pp_professionalgroup") +
                pp_professionalgroup);
    }

    /**
     * Sets the labels in the center panel with the given values.
     *
     * @param IRPF                The IRPF label value.
     * @param pp_totalearned      The total earned label value.
     * @param pp_totaldeductions  The total deductions label value.
     * @param pp_companysignature The company signature label value.
     * @param pp_time             The time label value.
     * @param pp_irecieved        The "I received" label value.
     */
    private void setCenterPanelLabels(String IRPF, String pp_totalearned, String pp_totaldeductions,
                                      String pp_companysignature, String pp_time, String pp_irecieved) {
        centerPanel.pp_IRPF.setText(ProgramLanguageProperties.getProperty("pp_IRPF") + IRPF + "%");
        centerPanel.totalearnedLabel.setText(ProgramLanguageProperties.getProperty("pp_totalearned") + pp_totalearned);
        centerPanel.totaldeductionsLabel.setText(ProgramLanguageProperties.getProperty("pp_totaldeductions") +
                pp_totaldeductions);
        centerPanel.companysignatureLabel.setText(ProgramLanguageProperties.getProperty("pp_companysignature") +
                pp_companysignature);
        centerPanel.timeLabel.setText(ProgramLanguageProperties.getProperty("pp_time") + pp_time);
        centerPanel.irecievedLabel.setText(ProgramLanguageProperties.getProperty("pp_irecieved") + pp_irecieved);
    }

    /**
     * Sets the labels in the company panel with the given values.
     *
     * @param ATEP            The ATEP label value.
     * @param pp_companytotal The company total label value.
     */
    private void setCompanyPanelLabels(String ATEP, String pp_companytotal) {
        companyPanel.pp_companytotal.setText(ProgramLanguageProperties.getProperty("pp_companytotal") +
                pp_companytotal);
        companyPanel.pp_ATEP.setText(ProgramLanguageProperties.getProperty("pp_ATEP") + ATEP + "%");
    }
}

