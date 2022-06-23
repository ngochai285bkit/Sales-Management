package App;

import View.LoginView;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class Launch {
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(LoginView::new);
    }
}
