package View;

import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class SupplierPanel extends JPanel {
    // attributes
    private Database database;

    // constructor
    public SupplierPanel(Database database){
        this.database = database;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245,245,251));
        initComponents();
    }

    private void initComponents() {
        // implementation the top panel
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245,245,251));
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78,138,211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        this.add(pnTop, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý nhà cung cấp");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(new SupplierPanel(new Database()));
        frame.setVisible(true);
    }
}
