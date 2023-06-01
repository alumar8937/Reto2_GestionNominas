package view.mainWindow;

import programLanguage.ProgramLanguageProperties;
import view.FrameUtils;
import view.mainWindow.queryPanel.QueryByBatchesPanel;
import view.mainWindow.queryPanel.QueryByDepartmentPanel;
import view.mainWindow.queryPanel.QueryByEmployeePanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class MainWindowFrame extends JFrame {
    private static MainWindowFrame INSTANCE = null;
    private JTabbedPane mainWindowTabbedPane = new JTabbedPane();

    private MainWindowFrame() {
        FrameUtils.setWindowIcon(this);

        setTitle(ProgramLanguageProperties.getProperty("mainWindow"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainWindowTabbedPane.setUI(new BasicTabbedPaneUI() {
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

        mainWindowTabbedPane.add(QueryByBatchesPanel.getINSTANCE(), ProgramLanguageProperties.getProperty("batchTab"));
        mainWindowTabbedPane.add(QueryByDepartmentPanel.getINSTANCE(), ProgramLanguageProperties.getProperty("departmentTab"));
        mainWindowTabbedPane.add(QueryByEmployeePanel.getINSTANCE(), ProgramLanguageProperties.getProperty("employeeTab"));
        add(mainWindowTabbedPane);

        pack();
        setMinimumSize(new Dimension(1000, 600));
        setSize(1200,600);
        FrameUtils.centerWindowOnScreen(this);

        setVisible(true);
    }

    public static MainWindowFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MainWindowFrame();
        }
        return INSTANCE;
    }
}
