package view.mainWindow.queryPanel;

import javax.swing.*;

public class QueryByEmployeePanel extends JPanel {
    private static QueryByEmployeePanel INSTANCE = null;
    private QueryByEmployeePanel() {

    }

    public static QueryByEmployeePanel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new QueryByEmployeePanel();
        }
        return INSTANCE;
    }
}