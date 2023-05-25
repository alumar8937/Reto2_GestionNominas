package view.payroll;

import view.FrameUtils;

import javax.swing.*;
import java.awt.*;
/**
 * @Author Raúl Simarro Navarro
 *
 */
public class EditPayrollWindow extends JFrame{
    private JPanel marginPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    public EditPayrollWindow(){
        setTitle("Edit Payroll");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        constraints.insets.set(20,20,20,20);

        WindowPayroll windowPayroll = new WindowPayroll();

        marginPanel.add(windowPayroll, constraints);
        add(marginPanel);

        pack();

        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }
    class WindowPayroll extends JPanel{
        JLabel id_namelabel = new JLabel("Name");
        JLabel id_batchlabel = new JLabel("Batch");
        JLabel niflabel = new JLabel("NIF");
        JLabel yearlabel = new JLabel("Year");
        JLabel monthlabel = new JLabel("Month");
        JLabel total_devlabel = new JLabel ("Total Dev");
        JLabel total_netlabel = new JLabel("Total Net");
        JLabel ap_companylabel = new JLabel("Aport Company");
        JTextField id_nametext = new JTextField(10);
        JTextField id_batchtext = new JTextField(10);
        JTextField niftext = new JTextField(10);
        JTextField yeartext = new JTextField(10);
        JTextField monthtext = new JTextField(10);
        JTextField total_devtext = new JTextField(10);
        JTextField total_nettext = new JTextField(10);
        JTextField ap_companytext = new JTextField(10);

        public WindowPayroll() {

            setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // Columna 0
            constraints.gridy = 0; // Fila 0
            constraints.gridwidth = 1; // Ocupa 1 columna
            constraints.gridheight = 1; // Ocupa 1 filas
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets.set(5, 5, 5, 5);

            add(id_namelabel, constraints);

            constraints.gridy = 1;
            add(id_batchlabel, constraints);

            constraints.gridy = 2;
            add(niflabel, constraints);

            constraints.gridy = 3;
            add(yearlabel, constraints);

            constraints.gridy = 4;
            add(monthlabel, constraints);

            constraints.gridy = 5;
            add(total_devlabel, constraints);

            constraints.gridy = 6;
            add(total_netlabel, constraints);

            constraints.gridy = 7;
            add(ap_companylabel, constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            add(id_nametext, constraints);

            constraints.gridy = 1;
            add(id_batchtext, constraints);

            constraints.gridy = 2;
            add(niftext, constraints);

            constraints.gridy = 3;
            add(yeartext, constraints);

            constraints.gridy = 4;
            add(monthtext, constraints);

            constraints.gridy = 5;
            add(total_devtext, constraints);

            constraints.gridy = 6;
            add(total_nettext, constraints);

            constraints.gridy = 7;
            add(ap_companytext, constraints);

            setVisible(true);
        }
    }

}