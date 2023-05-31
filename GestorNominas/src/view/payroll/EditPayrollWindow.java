package view.payroll;

import controller.database.PayrollDBController;
import model.Payroll;
import model.PayrollBatch;
import view.FrameUtils;
import view.mainWindow.MainWindowFrame;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @Author Raúl Simarro Navarro
 *
 */
public class EditPayrollWindow extends JFrame{
    private JPanel marginPanel = new JPanel(new GridBagLayout());
    private WindowPayroll windowPayroll = new WindowPayroll();
    private GridBagConstraints constraints = new GridBagConstraints();
    private Payroll payroll;
    private static EditPayrollWindow INSTANCE = null;

    public EditPayrollWindow(){
        setTitle("Edit Payroll");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        constraints.insets.set(20,20,20,20);

        marginPanel.add(windowPayroll, constraints);
        add(marginPanel);

        pack();

        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }

    public static EditPayrollWindow getINSTANCE() { // Author: Javier Blasco Gómez
        if (INSTANCE == null) {
            INSTANCE = new EditPayrollWindow();
        }
        INSTANCE.setVisible(true);
        return INSTANCE;
    }

    public void editData(Payroll payroll1) { // Author: Javier Blasco Gómez
        payroll = payroll1;
        windowPayroll.companyText.setText(payroll1.getCompany());
        windowPayroll.cifText.setText(payroll1.getCif());
        windowPayroll.adressText.setText(payroll1.getAddress());
        windowPayroll.cccText.setText(String.valueOf(payroll1.getCcc()));
        windowPayroll.emp_nameText.setText(payroll1.getEmp_name());
        windowPayroll.num_ssText.setText(String.valueOf(payroll1.getNum_ss()));
        windowPayroll.id_nametext.setText(String.valueOf(payroll1.getId_name()));
        windowPayroll.id_batchtext.setText(String.valueOf(payroll1.getId_batch()));
        windowPayroll.niftext.setText(payroll1.getNif());
        windowPayroll.yeartext.setText(String.valueOf(payroll1.getYear()));
        windowPayroll.monthtext.setText(String.valueOf(payroll1.getMonth()));
        windowPayroll.dayText.setText(String.valueOf(payroll1.getDay()));
        windowPayroll.total_devtext.setText(String.valueOf(payroll1.getTotal_dev()));
        windowPayroll.total_deducText.setText(String.valueOf(payroll1.getTotal_deduc()));
        windowPayroll.total_nettext.setText(String.valueOf(payroll1.getTotal_net()));
        windowPayroll.ap_companytext.setText(String.valueOf(payroll1.getAp_company()));
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
        payroll.setId_batch(Integer.parseInt(windowPayroll.id_batchtext.getText()));
        payroll.setNif(windowPayroll.niftext.getText());
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
        this.setVisible(false);
    }

    private void addPayroll() {
        //Payroll payroll1 = new Payroll();
    }

    class WindowPayroll extends JPanel {
        JButton saveButton = new JButton("Save");

        JLabel companyLabel = new JLabel("Company");
        JLabel cifLabel = new JLabel("CIF");
        JLabel adressLabel = new JLabel("Adress");
        JLabel cccLabel = new JLabel("CCC");
        JLabel emp_nameLabel = new JLabel("Employ Name");
        JLabel num_ssLabel = new JLabel("SS Number");
        JLabel prof_GroupLabel = new JLabel("Professional Group");
        JLabel id_namelabel = new JLabel("ID Payroll");
        JLabel id_batchlabel = new JLabel("ID Batch");
        JLabel niflabel = new JLabel("NIF");
        JLabel yearlabel = new JLabel("Year");
        JLabel monthlabel = new JLabel("Month");
        JLabel dayLabel = new JLabel("Day");
        JLabel total_devlabel = new JLabel ("Total Dev");
        JLabel total_deducLabel = new JLabel("Total Deduction");
        JLabel total_netlabel = new JLabel("Total Net");
        JLabel ap_companylabel = new JLabel("Aport Company");

        public JTextField companyText = new JTextField(10);
        public JTextField cifText = new JTextField(10);
        public JTextField adressText = new JTextField(10);
        public JTextField cccText = new JTextField(10);
        public JTextField emp_nameText = new JTextField(10);
        public JTextField num_ssText = new JTextField(10);
        public JComboBox prof_GroupBox = new JComboBox<>();
        public JTextField id_nametext = new JTextField(10);
        public JTextField id_batchtext = new JTextField(10);
        public JTextField niftext = new JTextField(10);
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
            add(emp_nameText, constraints);

            constraints.gridy = 5;
            num_ssText.setEnabled(false);
            add(num_ssText, constraints);

            constraints.gridy = 6;
            PayrollDBController.setComboProfesionalGroup(prof_GroupBox);
            add(prof_GroupBox, constraints);

            constraints.gridy = 7;
            id_nametext.setEnabled(false);
            add(id_nametext, constraints);

            constraints.gridy = 8;
            id_batchtext.setEnabled(false);
            add(id_batchtext, constraints);

            constraints.gridy = 9;
            add(niftext, constraints);

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
            add(total_nettext, constraints);

            constraints.gridy = 16;
            add(ap_companytext, constraints);

            constraints.gridx = 1;
            constraints.gridy = 17;
            saveButton.addActionListener((e) -> {editPayroll(payroll); MainWindowFrame.getINSTANCE().getPanel().updatePayrollListAction();});
            add(saveButton, constraints);

            setVisible(true);
        }
    }
}