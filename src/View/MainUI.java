package View;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import java.sql.Connection;
import java.util.Objects;

public class MainUI extends JFrame {
    // attributes
    private Connection conn;

    // constructor
    public MainUI(String title, Connection conn) {
        super(title);
        this.conn = conn;
        initComponents();
        addEvents();
        showWindow();
    }

    private void initComponents() {

    }

    private void addEvents() {

    }

    private void showWindow() {
        this.setSize(900, 650);
        this.setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource(
                "/Images/icon.svg"))));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(() -> new MainUI("Quản lý bán hàng", null));
    }
}
