package view.initialConfig;

import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * @author Pedro Marín Sanchis
 * A window that indicates that the program is loading.
 */
public class LoadingWindow extends JFrame {
    private static LoadingWindow INSTANCE = null;
    private static final JProgressBar progressBar = new JProgressBar();
    private static final JPanel loadingWindowPanel = new JPanel();
    private static final GridBagConstraints constraints = new GridBagConstraints();

    public LoadingWindow() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(200,50);
        setResizable(false);
        FrameUtils.centerWindowOnScreen(this);

        setUndecorated(true);

        loadingWindowPanel.setLayout(new GridBagLayout());
        constraints.insets = new Insets(15,25,5,25);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        loadingWindowPanel.add(new JLabel(ProgramLanguageProperties.getProperty("connecting")), constraints);

        progressBar.setIndeterminate(true); // Set the progress bar to indeterminate mode for an animated loading circle
        constraints.insets = new Insets(5,25,25,25);
        constraints.gridy += 1;
        loadingWindowPanel.add(progressBar, constraints);

        add(loadingWindowPanel);
        pack();
        setVisible(true);
    }

    public static LoadingWindow getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new LoadingWindow();
        }
        return INSTANCE;
    }

}
