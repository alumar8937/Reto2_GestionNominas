package view.initialConfig;

import javax.swing.*;

public class InitialConfigFrame extends JFrame {
    JPanel initialConfigPanel = new JPanel();

    JLabel ipLabel = new JLabel("IP");
    JLabel portLabel = new JLabel("Port");
    JLabel userLabel = new JLabel("User");
    JButton okButton = new JButton("OK");
    public InitialConfigFrame() {
        setSize(400,300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Configuración Inicial");

        initialConfigPanel.add(okButton);

        add(initialConfigPanel);


        setVisible(true);
    }
}
