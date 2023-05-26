package view.initialConfig;

import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;

import javax.swing.*;

/**
 * @Author Pedro Marín Sanchis
 * A window that indicates that the program is loading.
 */
public class LoadingWindow extends JFrame {
    private static LoadingWindow INSTANCE = null;
    public LoadingWindow() {
        setTitle(ProgramLanguageProperties.getProperty("connecting"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(200,50);
        setResizable(false);
        FrameUtils.centerWindowOnScreen(this);
        setVisible(true);
    }

    public static LoadingWindow getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new LoadingWindow();
        }
        return INSTANCE;
    }
}
