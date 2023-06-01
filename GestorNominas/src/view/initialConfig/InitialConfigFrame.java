package view.initialConfig;

import controller.database.PayrollDBController;
import programLanguage.ProgramLanguageProperties;
import programLanguage.SupportedLanguage;
import userconfig.UserconfigManager;
import view.FrameUtils;
import view.mainWindow.MainWindowFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @Author Raúl Simarro Navarro
 * Sets and retireves user configuration for language and database connection.
 */
public class InitialConfigFrame extends JFrame {
    private JPanel marginPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    public InitialConfigFrame() {
        FrameUtils.setWindowIcon(this);
        setTitle(ProgramLanguageProperties.getProperty("initialConfig"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initialConfigJPanel panel = new initialConfigJPanel();

        // Finally, we add the JPanel to the JFrame.
        constraints.insets.set(20, 20, 20, 20); // Space between components.
        marginPanel.add(panel, constraints);
        add(marginPanel);
        pack();
        //FrameUtils.adjustWindowToFitScreen(this, 50);
        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }

    class initialConfigJPanel extends JPanel {
        JLabel IPLabel = new JLabel(ProgramLanguageProperties.getProperty("ip"));
        JLabel PortLabel = new JLabel(ProgramLanguageProperties.getProperty("port"));
        JLabel UserLabel = new JLabel(ProgramLanguageProperties.getProperty("user"));
        JLabel PasswordLabel = new JLabel(ProgramLanguageProperties.getProperty("password"));
        JLabel DatabaseLabel = new JLabel(ProgramLanguageProperties.getProperty("database"));
        JLabel LanguageLabel = new JLabel(ProgramLanguageProperties.getProperty("language"));
        JTextField IPTextField = new JTextField(10);
        JTextField PortTextField = new JTextField(10);
        JTextField UserTextField = new JTextField(10);
        JTextField PasswordTextField = new JTextField(10);
        JTextField DatabaseTextField = new JTextField(10);
        JComboBox<SupportedLanguage> LanguageComboBox = new JComboBox<SupportedLanguage>();

        JButton editButton = new JButton(ProgramLanguageProperties.getProperty("edit"));
        JButton okButton = new JButton(ProgramLanguageProperties.getProperty("ok"));

        private initialConfigJPanel() {
            // Use GridBagLayout.
            setLayout(new GridBagLayout());

            // Configure component constraints.
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.insets.set(5, 5, 5, 5); // Space between components.

            // Add components.
            add(IPLabel, constraints);

            constraints.gridy = 1;
            add(PortLabel, constraints);

            constraints.gridy = 2;
            add(UserLabel, constraints);

            constraints.gridy = 3;
            add(PasswordLabel, constraints);

            constraints.gridy = 4;
            add(DatabaseLabel, constraints);

            constraints.gridy = 5;
            add(LanguageLabel, constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            add(IPTextField, constraints);

            constraints.gridy = 1;
            add(PortTextField, constraints);

            constraints.gridy = 2;
            add(UserTextField, constraints);

            constraints.gridy = 3;
            add(PasswordTextField, constraints);

            constraints.gridy = 4;
            add(DatabaseTextField, constraints);

            constraints.gridy = 5;
            add(LanguageComboBox, constraints);

            constraints.gridx = 0;
            constraints.gridy = 6;
            add(editButton, constraints);

            constraints.gridx = 1;
            constraints.gridy = 6;
            add(okButton, constraints);

            fillLanguageComboBox();

            LanguageComboBox.addActionListener((e) -> updateLang()); // Author: Pedro Marín Sanchis
            okButton.addActionListener((e) -> {
                SwingWorker<Void, Void> loadingWindowWorker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        LoadingWindow.getINSTANCE();
                        PayrollDBController.establishConnection();
                        return null;
                    }

                    @Override
                    protected void done() {
                        LoadingWindow.getINSTANCE().dispose();
                        MainWindowFrame.getINSTANCE();
                        super.done();
                    }
                };
                SwingUtilities.getWindowAncestor(this).dispose();
                loadingWindowWorker.execute();
            });

            editButton.addActionListener((e) -> toggleFieldEdit());
            PasswordTextField.setDisabledTextColor(Color.WHITE);

            LanguageComboBox.setSelectedItem(UserconfigManager.getINSTANCE().getLanguage());
            ProgramLanguageProperties.setLanguage(UserconfigManager.getINSTANCE().getLanguage());

            fillFields();
            toggleFieldEdit();

            setVisible(true);
        }

        private void okButtonAction() { // Author: Pedro Marín Sanchis
            UserconfigManager.getINSTANCE().setIP(IPTextField.getText());
            UserconfigManager.getINSTANCE().setPort(PortTextField.getText());
            UserconfigManager.getINSTANCE().setUser(UserTextField.getText());
            UserconfigManager.getINSTANCE().setPassword(PasswordTextField.getText());
            UserconfigManager.getINSTANCE().setDatabase(DatabaseTextField.getText());
            UserconfigManager.getINSTANCE().setLanguage((SupportedLanguage) LanguageComboBox.getSelectedItem());
            UserconfigManager.getINSTANCE().store();
        }

        private void toggleFieldEdit() { // Author: Pedro Marín Sanchis
            IPTextField.setEnabled(!IPTextField.isEnabled());
            PortTextField.setEnabled(!PortTextField.isEnabled());
            UserTextField.setEnabled(!UserTextField.isEnabled());
            PasswordTextField.setEnabled(!PasswordTextField.isEnabled());
            DatabaseTextField.setEnabled(!DatabaseTextField.isEnabled());
        }

        private void updateLang() { // Author: Pedro Marín Sanchis
            ProgramLanguageProperties.setLanguage((SupportedLanguage) LanguageComboBox.getSelectedItem());
            UserconfigManager.getINSTANCE().setLanguage((SupportedLanguage) LanguageComboBox.getSelectedItem());
            IPLabel.setText(ProgramLanguageProperties.getProperty("IP"));
            PortLabel.setText(ProgramLanguageProperties.getProperty("port"));
            UserLabel.setText(ProgramLanguageProperties.getProperty("user"));
            PasswordLabel.setText(ProgramLanguageProperties.getProperty("password"));
            DatabaseLabel.setText(ProgramLanguageProperties.getProperty("database"));
            LanguageLabel.setText(ProgramLanguageProperties.getProperty("language"));
            okButton.setText(ProgramLanguageProperties.getProperty("ok"));
            editButton.setText(ProgramLanguageProperties.getProperty("edit"));
            setTitle(ProgramLanguageProperties.getProperty("initialConfig"));
            pack();
        }

        private void fillLanguageComboBox() {
            if (LanguageComboBox == null) {return;}
            LanguageComboBox.removeAllItems();
            for (SupportedLanguage sl: SupportedLanguage.values()) {
                LanguageComboBox.addItem(sl);
            }
        }

        private void fillFields() {
            IPTextField.setText(UserconfigManager.getINSTANCE().getIP());
            PortTextField.setText(UserconfigManager.getINSTANCE().getPort());
            UserTextField.setText(UserconfigManager.getINSTANCE().getUser());
            PasswordTextField.setText(UserconfigManager.getINSTANCE().getPassword());
            DatabaseTextField.setText(UserconfigManager.getINSTANCE().getDatabase());
            LanguageComboBox.setSelectedItem(UserconfigManager.getINSTANCE().getLanguage());
        }

    }

}
