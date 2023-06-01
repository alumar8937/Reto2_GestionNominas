package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Collection of methods related to JFrames.
 *
 * @author Pedro Marín Sanchis, David Serna Mateu
 */
public class FrameUtils {

    private static Toolkit toolkit = Toolkit.getDefaultToolkit();

    /**
     * The dimension of the screen.
     */
    public final static Dimension screenDimension = toolkit.getScreenSize();

    /**
     * Centers the specified window on the screen.
     *
     * @param window the JFrame window to center
     */
    public static void centerWindowOnScreen(JFrame window) {
        window.setLocation((int) screenDimension.getWidth() / 2 - window.getWidth() / 2,
                (int) screenDimension.getHeight() / 2 - window.getHeight() / 2);
    }

    /**
     * Adjusts the size of the specified window to fit the screen by the specified percentage.
     *
     * @param window          the JFrame window to adjust
     * @param reduceByPercent the percentage to reduce the screen size by
     */
    public static void adjustWindowToFitScreen(JFrame window, double reduceByPercent) {
        window.setSize(new Dimension((int) (screenDimension.getWidth() * (reduceByPercent / 100)),
                (int) (screenDimension.getHeight() * (reduceByPercent / 100))));
    }

    /**
     * Sets the window icon of the specified JFrame window.
     *
     * @param window the JFrame window to set the icon for
     */
    public static void setWindowIcon(JFrame window) {
        ImageIcon icon = new ImageIcon(
                "." + File.separator + "GestorNominas" + File.separator + "src" + File.separator + "media"
                        + File.separator + "GestorNominasIcon.png");
        window.setIconImage(icon.getImage());
    }
}
