package view.payroll;

import model.Payroll;
import view.FrameUtils;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    public static EditPayrollWindow getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new EditPayrollWindow();
        }
        INSTANCE.setVisible(true);
        return INSTANCE;
    }

    public void editData(Payroll payroll) {
        windowPayroll.companyText.setText(payroll.getCompany());
        windowPayroll.cifText.setText(payroll.getCif());
        windowPayroll.adressText.setText(payroll.getAddress());
        windowPayroll.cccText.setText(String.valueOf(payroll.getCcc()));
        windowPayroll.emp_nameText.setText(payroll.getEmp_name());
        windowPayroll.num_ssText.setText(String.valueOf(payroll.getNum_ss()));
        windowPayroll.prof_GroupText.setText(payroll.getProf_group());
        windowPayroll.id_nametext.setText(String.valueOf(payroll.getId_name()));
        windowPayroll.id_batchtext.setText(String.valueOf(payroll.getId_batch()));
        windowPayroll.niftext.setText(payroll.getNif());
        windowPayroll.yeartext.setText(String.valueOf(payroll.getYear()));
        windowPayroll.monthtext.setText(String.valueOf(payroll.getMonth()));
        windowPayroll.dayText.setText(String.valueOf(payroll.getDay()));
        windowPayroll.total_devtext.setText(String.valueOf(payroll.getTotal_dev()));
        windowPayroll.total_deducLabel.setText(String.valueOf(payroll.getTotal_deduc()));
        windowPayroll.total_nettext.setText(String.valueOf(payroll.getTotal_net()));
        windowPayroll.ap_companytext.setText(String.valueOf(payroll.getAp_company()));
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
        JLabel id_namelabel = new JLabel("Name");
        JLabel id_batchlabel = new JLabel("Batch");
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
        public JTextField prof_GroupText = new JTextField(10);
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
            add(companyText, constraints);

            constraints.gridy = 1;
            add(cifText, constraints);

            constraints.gridy = 2;
            add(adressText, constraints);

            constraints.gridy = 3;
            add(cccText, constraints);

            constraints.gridy = 4;
            add(emp_nameText, constraints);

            constraints.gridy = 5;
            add(num_ssText, constraints);

            constraints.gridy = 6;
            add(prof_GroupText, constraints);

            constraints.gridy = 7;
            add(id_nametext, constraints);

            constraints.gridy = 8;
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
            add(saveButton, constraints);

            setVisible(true);
        }
    }
}