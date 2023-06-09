package view.mainWindow.payroll;

import controller.database.PayrollDBController;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.mainWindow.queryPanel.controlPanel.PayrollButtonPanel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

/**
 * @author Javier Blasco Gómez
 *
 */
public class EditPayrollWindow extends JFrame{
    private final WindowPayroll windowPayroll = new WindowPayroll();
    private Payroll payroll;
    private PayrollButtonPanel ancestorPayrollButtonPanel = null;

    public EditPayrollWindow(PayrollButtonPanel ancestorPayrollButtonPanel){ // Author: Raúl Simarro Navarro
        this.ancestorPayrollButtonPanel = ancestorPayrollButtonPanel;
        setTitle(ProgramLanguageProperties.getProperty("editPayrollTitle"));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(20,20,20,20);

        JPanel marginPanel = new JPanel(new GridBagLayout());
        marginPanel.add(windowPayroll, constraints);
        add(marginPanel);

        pack();

        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }

    class WindowPayroll extends JPanel {
        JButton saveButton = new JButton(ProgramLanguageProperties.getProperty("epw_create"));

        JLabel companyLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_company"));
        JLabel cifLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_cif"));
        JLabel adressLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_address"));
        JLabel cccLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_ccc"));
        JLabel emp_nameLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_empName"));
        JLabel num_ssLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_ss"));
        JLabel prof_GroupLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_group"));
        JLabel id_namelabel = new JLabel(ProgramLanguageProperties.getProperty("epw_payrollID"));
        JLabel id_batchlabel = new JLabel(ProgramLanguageProperties.getProperty("epw_payrollBatch"));
        JLabel niflabel = new JLabel(ProgramLanguageProperties.getProperty("epw_nif"));
        JLabel yearlabel = new JLabel(ProgramLanguageProperties.getProperty("epw_year"));
        JLabel monthlabel = new JLabel(ProgramLanguageProperties.getProperty("epw_month"));
        JLabel dayLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_day"));
        JLabel total_devlabel = new JLabel (ProgramLanguageProperties.getProperty("epw_totalDev"));
        JLabel total_deducLabel = new JLabel(ProgramLanguageProperties.getProperty("epw_totalDeduc"));
        JLabel total_netlabel = new JLabel(ProgramLanguageProperties.getProperty("epw_totalNet"));
        JLabel ap_companylabel = new JLabel(ProgramLanguageProperties.getProperty("epw_ap"));

        public JTextField companyText = new JTextField(10);
        public JTextField cifText = new JTextField(10);
        public JTextField adressText = new JTextField(10);
        public JTextField cccText = new JTextField(10);
        public JTextField emp_nameText = new JTextField(10);
        public JTextField num_ssText = new JTextField(10);
        public JComboBox prof_GroupBox = new JComboBox<>();
        public JTextField id_nametext = new JTextField(10);
        public JComboBox<PayrollBatch> id_batchBox = new JComboBox<PayrollBatch>();
        public JComboBox<Employee> nifCombo = new JComboBox<Employee>();
        public JTextField yeartext = new JTextField(10);
        public JTextField monthtext = new JTextField(10);
        public JTextField dayText = new JTextField(10);
        public JTextField total_devtext = new JTextField(10);
        public JTextField total_deducText = new JTextField(10);
        public JTextField total_nettext = new JTextField(10);
        public JTextField ap_companytext = new JTextField(10);

        public WindowPayroll() {

            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // Columna 0
            constraints.gridy = 0; // Fila 0
            constraints.gridwidth = 1; // Ocupa 1 columna
            constraints.gridheight = 1; // Ocupa 1 filas
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets.set(5, 5, 5, 5);

            add(companyLabel, constraints);

            constraints.gridy = 1;
            add(cifLabel, constraints);

            constraints.gridy = 2;
            add(adressLabel, constraints);

            constraints.gridy = 3;
            add(cccLabel, constraints);

            constraints.gridy = 4;
            add(emp_nameLabel, constraints);

            constraints.gridy = 5;
            add(num_ssLabel, constraints);

            constraints.gridy = 6;
            add(prof_GroupLabel, constraints);

            constraints.gridy = 7;
            add(id_namelabel, constraints);

            constraints.gridy = 8;
            add(id_batchlabel, constraints);

            constraints.gridy = 9;
            add(niflabel, constraints);

            constraints.gridy = 10;
            add(yearlabel, constraints);

            constraints.gridy = 11;
            add(monthlabel, constraints);

            constraints.gridy = 12;
            add(dayLabel,constraints);

            constraints.gridy = 13;
            add(total_devlabel, constraints);

            constraints.gridy = 14;
            add(total_deducLabel, constraints);

            constraints.gridy = 15;
            add(total_netlabel, constraints);

            constraints.gridy = 16;
            add(ap_companylabel, constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            companyText.setEnabled(false);
            add(companyText, constraints);

            constraints.gridy = 1;
            cifText.setEnabled(false);
            add(cifText, constraints);

            constraints.gridy = 2;
            adressText.setEnabled(false);
            add(adressText, constraints);

            constraints.gridy = 3;
            cccText.setEnabled(false);
            add(cccText, constraints);

            constraints.gridy = 4;
            emp_nameText.setEnabled(false);
            add(emp_nameText, constraints);

            constraints.gridy = 5;
            num_ssText.setEnabled(false);
            add(num_ssText, constraints);

            constraints.gridy = 6;
            setComboProfesionalGroup(prof_GroupBox);
            add(prof_GroupBox, constraints);

            constraints.gridy = 7;
            id_nametext.setEnabled(false);
            add(id_nametext, constraints);

            constraints.gridy = 8;
            id_batchBox.setEnabled(true);
            setBatchesCombo(id_batchBox);
            add(id_batchBox, constraints);

            constraints.gridy = 9;
            nifCombo.setEnabled(true);
            setEmployeesCombo(nifCombo);
            nifCombo.addActionListener((e) -> setToEmployeeData());
            add(nifCombo, constraints);

            constraints.gridy = 10;
            add(yeartext, constraints);

            constraints.gridy = 11;
            add(monthtext, constraints);

            constraints.gridy = 12;
            add(dayText, constraints);

            constraints.gridy = 13;
            add(total_devtext, constraints);

            constraints.gridy = 14;
            add(total_deducText, constraints);

            constraints.gridy = 15;
            total_nettext.setEnabled(false);
            add(total_nettext, constraints);

            constraints.gridy = 16;
            add(ap_companytext, constraints);

            constraints.gridx = 1;
            constraints.gridy = 17;
            saveButton.addActionListener((e) -> {saveData();  SwingUtilities.getWindowAncestor(this).dispose();} );
            add(saveButton, constraints);

            setVisible(true);
        }
    }

    public void editData(Payroll payroll1) { // Author: Javier Blasco Gómez
        payroll = payroll1;
        windowPayroll.id_batchBox.setEnabled(false);
        windowPayroll.nifCombo.setEnabled(false);
        windowPayroll.companyText.setText(payroll1.getCompany());
        windowPayroll.cifText.setText(payroll1.getCif());
        windowPayroll.adressText.setText(payroll1.getAddress());
        windowPayroll.cccText.setText(String.valueOf(payroll1.getCcc()));
        windowPayroll.emp_nameText.setText(payroll1.getEmp_name());
        windowPayroll.prof_GroupBox.setSelectedItem(payroll1.getProf_group());
        windowPayroll.num_ssText.setText(String.valueOf(payroll1.getNum_ss()));
        windowPayroll.id_nametext.setText(String.valueOf(payroll1.getId_name()));
        windowPayroll.id_batchBox.setSelectedItem(payroll1.getId_batch());
        windowPayroll.nifCombo.setSelectedItem(payroll1.getNif());
        windowPayroll.yeartext.setText(String.valueOf(payroll1.getYear()));
        windowPayroll.monthtext.setText(String.valueOf(payroll1.getMonth()));
        windowPayroll.dayText.setText(String.valueOf(payroll1.getDay()));
        windowPayroll.total_devtext.setText(String.valueOf(payroll1.getTotal_dev()));
        windowPayroll.total_deducText.setText(String.valueOf(payroll1.getTotal_deduc()));
        windowPayroll.total_nettext.setText("");
        windowPayroll.ap_companytext.setText(String.valueOf(payroll1.getAp_company()));
        windowPayroll.saveButton.setText(ProgramLanguageProperties.getProperty("epw_save"));
    }

    private void setToEmployeeData() { // Author: Javier Blasco Gómez / David Serna Mateu
        ArrayList<String> companyData = PayrollDBController.getCompanyData();
        ArrayList<String> employeeData = PayrollDBController.getEmployeeData(((Employee)(windowPayroll.nifCombo.getSelectedItem())).getNIF());
        if((Employee)(windowPayroll.nifCombo.getSelectedItem()) == null){ return; }
        ArrayList <Payroll> payrolls = PayrollDBController.getPayrollsByEmployeeNIF(((Employee)(windowPayroll.nifCombo.getSelectedItem())).getNIF(), false);
        if(payrolls.size() == 0){
            payrolls = PayrollDBController.getPayrollsByEmployeeNIF(((Employee)(windowPayroll.nifCombo.getSelectedItem())).getNIF(), true);
        }
        windowPayroll.companyText.setText(companyData.get(1));
        windowPayroll.cifText.setText(companyData.get(0));
        windowPayroll.adressText.setText(companyData.get(2));
        windowPayroll.cccText.setText(companyData.get(3));
        windowPayroll.emp_nameText.setText(employeeData.get(0));
        windowPayroll.prof_GroupBox.setSelectedItem(employeeData.get(1));
        windowPayroll.num_ssText.setText(String.valueOf(employeeData.get(2)));
        windowPayroll.total_devtext.setText("");
        windowPayroll.total_deducText.setText("");
        windowPayroll.total_nettext.setText("");
        windowPayroll.ap_companytext.setText("");
    }

    private void newPayroll() { // Author: Javier Blasco Gómez
        String company = windowPayroll.companyText.getText();
        String cif = windowPayroll.cifText.getText();
        String address = windowPayroll.adressText.getText();
        long ccc = Long.parseLong(windowPayroll.cccText.getText());
        String emp_name = windowPayroll.emp_nameText.getText();
        int id_name = 0;
        int id_batch = ((PayrollBatch)(windowPayroll.id_batchBox.getSelectedItem())).getID();
        String nif = ((Employee)(windowPayroll.nifCombo.getSelectedItem())).getNIF();
        int year = Integer.parseInt(windowPayroll.yeartext.getText());
        int month = Integer.parseInt(windowPayroll.monthtext.getText());
        int day = Integer.parseInt(windowPayroll.dayText.getText());
        double total_dev = Double.parseDouble(windowPayroll.total_devtext.getText());
        double total_deduc = Double.parseDouble(windowPayroll.total_deducText.getText());
        double total_net = 0.0;
        double ap_company = Double.parseDouble(windowPayroll.ap_companytext.getText());

        Payroll payroll1 = new Payroll(company,cif,address,ccc,id_name,id_batch,nif,year,month,day,total_dev,total_deduc,ap_company);
        payroll1.setEmp_name(emp_name);
        payroll1.setTotal_net(total_net);
        PayrollDBController.createPayroll(payroll1);
        ancestorPayrollButtonPanel.sendUpdateActionEvent();
    }

    private void editPayroll(Payroll payroll) { // Author: Javier Blasco Gómez
        payroll.setCompany(windowPayroll.companyText.getText());
        payroll.setCif(windowPayroll.cifText.getText());
        payroll.setAddress(windowPayroll.adressText.getText());
        payroll.setCcc(Long.parseLong(windowPayroll.cccText.getText()));
        payroll.setEmp_name(windowPayroll.emp_nameText.getText());
        String[] nombre = windowPayroll.emp_nameText.getText().split(" ");
        if(nombre.length < 2 || nombre.length > 4){
            JOptionPane.showMessageDialog(null,"The name is invalid.");
            return;
        }
        payroll.setNum_ss(Long.parseLong(windowPayroll.num_ssText.getText()));
        payroll.setProf_group((String) windowPayroll.prof_GroupBox.getSelectedItem());
        payroll.setId_name(Integer.parseInt(windowPayroll.id_nametext.getText()));
        payroll.setId_batch(((PayrollBatch)(windowPayroll.id_batchBox.getSelectedItem())).getID());
        payroll.setNif(((Employee)(windowPayroll.nifCombo.getSelectedItem())).getNIF());
        payroll.setYear(Integer.parseInt(windowPayroll.yeartext.getText()));
        String year = windowPayroll.yeartext.getText();
        if (year.length() != 4) {
            return;
        }
        payroll.setMonth(Integer.parseInt(windowPayroll.monthtext.getText()));
        String month = windowPayroll.monthtext.getText();
        if (month.length() > 2 || month.length() < 1) {
            if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
                return;
            }
        }
        payroll.setDay(Integer.parseInt(windowPayroll.dayText.getText()));
        String day = windowPayroll.monthtext.getText();
        if (day.length() > 2 || day.length() < 1) {
            if (Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1) {
                return;
            }
        }
        payroll.setTotal_dev(Double.parseDouble(windowPayroll.total_devtext.getText()));
        payroll.setTotal_deduc(Double.parseDouble(windowPayroll.total_deducText.getText()));
        payroll.setTotal_net(Double.parseDouble(windowPayroll.total_nettext.getText()));
        payroll.setAp_company(Double.parseDouble(windowPayroll.ap_companytext.getText()));
        PayrollDBController.modifyPayroll(payroll);
        ancestorPayrollButtonPanel.sendUpdateActionEvent();
    }

    public static void setComboProfesionalGroup(JComboBox comboBox) { // Author: Javier Blasco Gómez
        ArrayList<String> profesionalGroups = PayrollDBController.getProfesionalGroup();
        comboBox.removeAllItems();
        for (String profesionalGroup: profesionalGroups) {
            comboBox.addItem(profesionalGroup);
        }
    }

    public static void setBatchesCombo(JComboBox<PayrollBatch> comboBox) { // Author: Javier Blasco Gómez
        ArrayList<PayrollBatch> payrollBatches = PayrollDBController.getBatches(false);
        comboBox.removeAllItems();
        for (PayrollBatch payrollBatch1:payrollBatches) {
            comboBox.addItem(payrollBatch1);
        }
    }

    public static void setEmployeesCombo(JComboBox<Employee> comboBox) { // Author: David Serna Mateu
        ArrayList<Employee> employees = PayrollDBController.getEmployees();
        comboBox.removeAllItems();
        for (Employee e:employees) {
            comboBox.addItem(e);
        }
    }

    public void saveData() { // Author: David Serna Mateu
        if(windowPayroll.saveButton.getText().equalsIgnoreCase(ProgramLanguageProperties.getProperty("epw_save"))){
            editPayroll(payroll);
        }else if(windowPayroll.saveButton.getText().equalsIgnoreCase(ProgramLanguageProperties.getProperty("epw_create"))){
            newPayroll();
        }
    }
}