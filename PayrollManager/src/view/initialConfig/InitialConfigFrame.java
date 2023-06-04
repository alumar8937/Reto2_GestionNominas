package view.initialConfig;

import controller.database.PayrollDBController;
import programLanguage.ProgramLanguageProperties;
import programLanguage.SupportedLanguage;
import userconfig.UserConfigManager;
import view.FrameUtils;
import view.mainWindow.MainWindowFrame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * @author Raúl Simarro Navarro
 * Sets and retireves user configuration for language and database connection.
 */
public class InitialConfigFrame extends JFrame {
    private final GridBagConstraints constraints = new GridBagConstraints();

    public InitialConfigFrame() {
        FrameUtils.setWindowIcon(this);
        setTitle(ProgramLanguageProperties.getProperty("initialConfig"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initialConfigJPanel panel = new initialConfigJPanel();

        // Finally, we add the JPanel to the JFrame.
        constraints.insets.set(20, 20, 20, 20); // Space between components.
        JPanel marginPanel = new JPanel(new GridBagLayout());
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
                okButtonAction();
                SwingUtilities.getWindowAncestor(this).dispose();
                loadingWindowWorker.execute();
            });

            editButton.addActionListener((e) -> toggleFieldEdit());
            PasswordTextField.setDisabledTextColor(Color.WHITE);

            LanguageComboBox.setSelectedItem(UserConfigManager.getINSTANCE().getLanguage());
            ProgramLanguageProperties.setLanguage(UserConfigManager.getINSTANCE().getLanguage());

            fillFields();
            toggleFieldEdit();

            setVisible(true);
        }

        /**
         * Performs the action when the OK button is clicked.
         * Retrieves the values from the text fields and combo box, sets them in UserconfigManager, and stores the configuration.
         */
        private void okButtonAction() { // Author: Pedro Marín Sanchis
            UserConfigManager.getINSTANCE().setIP(IPTextField.getText());
            UserConfigManager.getINSTANCE().setPort(PortTextField.getText());
            UserConfigManager.getINSTANCE().setUser(UserTextField.getText());
            UserConfigManager.getINSTANCE().setPassword(PasswordTextField.getText());
            UserConfigManager.getINSTANCE().setDatabase(DatabaseTextField.getText());
            UserConfigManager.getINSTANCE().setLanguage((SupportedLanguage) LanguageComboBox.getSelectedItem());
            UserConfigManager.getINSTANCE().store();
        }

        /**
         * Toggles the enabled state of the text fields when the Edit button is clicked.
         */
        private void toggleFieldEdit() { // Author: Pedro Marín Sanchis
            IPTextField.setEnabled(!IPTextField.isEnabled());
            PortTextField.setEnabled(!PortTextField.isEnabled());
            UserTextField.setEnabled(!UserTextField.isEnabled());
            PasswordTextField.setEnabled(!PasswordTextField.isEnabled());
            DatabaseTextField.setEnabled(!DatabaseTextField.isEnabled());
        }

        /**
         * Updates the labels and buttons according to the selected language when the language combo box selection changes.
         */
        private void updateLang() { // Author: Pedro Marín Sanchis
            ProgramLanguageProperties.setLanguage((SupportedLanguage) LanguageComboBox.getSelectedItem());
            UserConfigManager.getINSTANCE().setLanguage((SupportedLanguage) LanguageComboBox.getSelectedItem());
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

        /**
         * Fills the language combo box with the available supported languages.
         */
        private void fillLanguageComboBox() {
            if (LanguageComboBox == null) {return;}
            LanguageComboBox.removeAllItems();
            for (SupportedLanguage sl: SupportedLanguage.values()) {
                LanguageComboBox.addItem(sl);
            }
        }

        /**
         * Fills the text fields and combo box with the values from UserconfigManager.
         */
        private void fillFields() {
            IPTextField.setText(UserConfigManager.getINSTANCE().getIP());
            PortTextField.setText(UserConfigManager.getINSTANCE().getPort());
            UserTextField.setText(UserConfigManager.getINSTANCE().getUser());
            PasswordTextField.setText(UserConfigManager.getINSTANCE().getPassword());
            DatabaseTextField.setText(UserConfigManager.getINSTANCE().getDatabase());
            LanguageComboBox.setSelectedItem(UserConfigManager.getINSTANCE().getLanguage());
        }

    }

}
