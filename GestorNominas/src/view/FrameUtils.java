package view;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * @Author Pedro Marín Sanchis, David Serna Mateu
 * Collection of methods related to JFrames.
 */
public class FrameUtils {

    private static Toolkit toolkit = Toolkit.getDefaultToolkit();

    public final static Dimension screenDimension = toolkit.getScreenSize();

    public static void centerWindowOnScreen(JFrame window) { // @Author Pedro Marín Sanchis
        window.setLocation((int) screenDimension.getWidth()/2-window.getWidth()/2,
                (int) screenDimension.getHeight()/2-window.getHeight()/2 );
    }

    public static void adjustWindowToFitScreen(JFrame window, double reduceByPercent) { // @Author David Serna Mateu
        window.setSize(new Dimension((int) (screenDimension.getWidth()*(reduceByPercent/100)), (int) (screenDimension.getHeight()*(reduceByPercent/100))));
    }

}