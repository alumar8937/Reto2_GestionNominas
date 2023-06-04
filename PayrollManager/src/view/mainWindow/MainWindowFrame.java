package view.mainWindow;

import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.mainWindow.queryPanel.QueryByBatchesPanel;
import view.mainWindow.queryPanel.QueryByDepartmentPanel;
import view.mainWindow.queryPanel.QueryByEmployeePanel;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;

/**
 * Represents the main window frame of the application.
 * Contains multiple tabs for different query panels.
 *
 * @author Pedro Marín Sanchis
 */
public class MainWindowFrame extends JFrame {
    private static MainWindowFrame INSTANCE = null;

    /**
     * Constructs a new instance of MainWindowFrame.
     * Private constructor to enforce singleton pattern.
     */
    private MainWindowFrame() {
        setTitle(ProgramLanguageProperties.getProperty("mainWindowTitle"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        setSize(1200, 600);
        FrameUtils.centerWindowOnScreen(this);
        FrameUtils.setWindowIcon(this);

        placeComponents();
        pack();

        setVisible(true);
    }

    /**
     * Places necessary components.
     */
    private void placeComponents() {
        // Customize the appearance of the tabbed pane
        JTabbedPane mainWindowTabbedPane = new JTabbedPane();
        setTabbedPaneUI(mainWindowTabbedPane);

        // Add query panels as tabs
        mainWindowTabbedPane.add(QueryByBatchesPanel.getINSTANCE(), ProgramLanguageProperties.getProperty("batchTab"));
        mainWindowTabbedPane.add(QueryByDepartmentPanel.getINSTANCE(), ProgramLanguageProperties.getProperty("departmentTab"));
        mainWindowTabbedPane.add(QueryByEmployeePanel.getINSTANCE(), ProgramLanguageProperties.getProperty("employeeTab"));
        add(mainWindowTabbedPane);
    }

    /**
     * Establishes tabbedPane style.
     * @param tabbedPane
     */
    private void setTabbedPaneUI(JTabbedPane tabbedPane) {
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10; // Increase the tab height
            }

            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 70; // Increase the tab width
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabPane.setBackground(Color.WHITE); // Set the background color of the tabbed pane
            }
        });
    }

    /**
     * Returns the singleton instance of MainWindowFrame.
     *
     * @return The singleton instance of MainWindowFrame.
     */
    public static MainWindowFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MainWindowFrame();
        }
        return INSTANCE;
    }
}