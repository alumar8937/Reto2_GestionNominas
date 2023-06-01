package view.mainWindow.queryPanel;

import javax.swing.*;

public class QueryByDepartmentPanel extends JPanel {
    private static QueryByDepartmentPanel INSTANCE = null;
    private QueryByDepartmentPanel() {

    }

    public static QueryByDepartmentPanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByDepartmentPanel();
        }
        return INSTANCE;
    }
}