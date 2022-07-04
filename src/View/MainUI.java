package View;

import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class MainUI extends JFrame {
    // attributes
    private final Database database;
    // components
    private JLabel lblBtnLogout;
    private CustomJLabel lblTrangChu, lblNhanVien, lblSanPham, lblKhachHang, lblNhaCungCap;
    private JMenuItem menuItemGithub, menuItemInformation, menuItemExit;
    private JPanel pnCenter;

    public static Frame frame;

    // constructor
    public MainUI(String title, Database database) {
        super(title);
        MainUI.frame = this;
        this.database = database;
        initComponents();
        addEvents();
        showWindow();
    }

    private void initComponents() {
        // ==================== Menu bar ====================
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        menuItemExit = new JMenuItem("Exit");
        menuItemExit.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/Images" +
                "/24x24/ic_close_24px.svg"))));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('Q',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menuFile.add(menuItemExit);

        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('H');
        menuItemInformation = new JMenuItem("Information");
        menuItemInformation.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/Images" +
                "/24x24/info_24px.svg"))));
        menuItemInformation.setAccelerator(KeyStroke.getKeyStroke('I',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menuItemGithub = new JMenuItem("Github");
        menuItemGithub.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/Images" +
                "/24x24/github_24px.svg"))));
        menuItemGithub.setAccelerator(KeyStroke.getKeyStroke('G',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menuHelp.add(menuItemGithub);
        menuHelp.add(menuItemInformation);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);

        // ==================== Left Panel ====================
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(new BorderLayout());
        pnLeft.setBackground(Color.WHITE);
        pnLeft.setPreferredSize(new Dimension(200, 0));

        JLabel lblImage = new JLabel();
        lblImage.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/sales_manager.svg"))));
        lblImage.setBorder(BorderFactory.createLineBorder(new Color(75, 102, 147), 2));
        JPanel pnLblImage = new JPanel();
        pnLblImage.setBackground(new Color(244, 247, 254));
        pnLblImage.setLayout(new BorderLayout());
        pnLblImage.add(lblImage, BorderLayout.CENTER);

        lblTrangChu = new CustomJLabel("Trang chủ");
        lblTrangChu.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/home_48px.svg"))));
        lblTrangChu.setBackground(new Color(78, 138, 201));
        lblTrangChu.setForeground(Color.WHITE);
        lblTrangChu.setSelected(true);
        JPanel pnLblTrangChu = new JPanel();
        pnLblTrangChu.setLayout(new BorderLayout());
        pnLblTrangChu.add(lblTrangChu, BorderLayout.CENTER);

        lblSanPham = new CustomJLabel("Sản phẩm");
        lblSanPham.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/product_48px.svg"))));
        JPanel pnLblSanPham = new JPanel();
        pnLblSanPham.setLayout(new BorderLayout());
        pnLblSanPham.add(lblSanPham, BorderLayout.CENTER);

        lblNhanVien = new CustomJLabel("Nhân viên");
        lblNhanVien.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/employee_48px.svg"))));
        JPanel pnLblNhanVien = new JPanel();
        pnLblNhanVien.setLayout(new BorderLayout());
        pnLblNhanVien.add(lblNhanVien, BorderLayout.CENTER);

        lblKhachHang = new CustomJLabel("Khách hàng");
        lblKhachHang.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/customer_48px.svg"))));
        JPanel pnLblKhachHang = new JPanel();
        pnLblKhachHang.setLayout(new BorderLayout());
        pnLblKhachHang.add(lblKhachHang, BorderLayout.CENTER);

        lblNhaCungCap = new CustomJLabel("Nhà cung cấp");
        lblNhaCungCap.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/48x48/supplier_48px.svg"))));
        JPanel pnLblNhaCungCap = new JPanel();
        pnLblNhaCungCap.setLayout(new BorderLayout());
        pnLblNhaCungCap.add(lblNhaCungCap, BorderLayout.CENTER);

        JPanel pnOption = new JPanel();
        pnOption.setLayout(new BoxLayout(pnOption, BoxLayout.Y_AXIS));
        pnOption.add(Box.createVerticalGlue());
        pnOption.add(pnLblTrangChu);
        pnOption.add(pnLblSanPham);
        pnOption.add(pnLblNhanVien);
        pnOption.add(pnLblKhachHang);
        pnOption.add(pnLblNhaCungCap);
        pnOption.add(Box.createVerticalGlue());

        pnLeft.add(pnLblImage, BorderLayout.NORTH);
        pnLeft.add(pnOption, BorderLayout.CENTER);

        // ==================== Tool Panel ====================
        lblBtnLogout = new JLabel("Đăng xuất");
        lblBtnLogout.setForeground(Color.WHITE);
        lblBtnLogout.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblBtnLogout.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource(
                "/Images/32x32/Logout_32px.svg"))));
        lblBtnLogout.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(1, 2, 1, 10)));
        lblBtnLogout.setBackground(new Color(78, 138, 201));
        lblBtnLogout.setVerticalAlignment(JLabel.CENTER);
        lblBtnLogout.setOpaque(true);

        JPanel pnTop = new JPanel();
        pnTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(0, 45));
        pnTop.add(lblBtnLogout);

        // ==================== Center Panel ====================
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setBackground(new Color(245, 245, 251));
        pnCenter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 4)));
        pnCenter.add(new HomePanel(), BorderLayout.CENTER);

        // ==================== Status bar ====================
        JPanel pnStatusBar = new JPanel();
        pnStatusBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED));


        // ==================== Add Main Panel to Content panel ====================
        Container con = this.getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnLeft, BorderLayout.WEST);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnStatusBar, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    private void addEvents() {
        lblBtnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainUI.this.setVisible(false);
                new LoginView();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblBtnLogout.setBackground(Color.YELLOW);
                lblBtnLogout.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblBtnLogout.setBackground(new Color(78, 138, 201));
                lblBtnLogout.setForeground(Color.WHITE);
            }
        });

        lblTrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lblTrangChu.setSelected(true);
                lblNhaCungCap.setSelected(false);
                lblNhanVien.setSelected(false);
                lblKhachHang.setSelected(false);
                lblSanPham.setSelected(false);
                pnCenter.removeAll();
                pnCenter.add(new HomePanel(), BorderLayout.CENTER);
                pnCenter.updateUI();
            }
        });

        lblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lblTrangChu.setSelected(false);
                lblNhaCungCap.setSelected(false);
                lblNhanVien.setSelected(true);
                lblKhachHang.setSelected(false);
                lblSanPham.setSelected(false);
                pnCenter.removeAll();
                pnCenter.add(new EmployeePanel(database), BorderLayout.CENTER);
                pnCenter.updateUI();
            }
        });

        lblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lblTrangChu.setSelected(false);
                lblNhaCungCap.setSelected(false);
                lblNhanVien.setSelected(false);
                lblKhachHang.setSelected(false);
                lblSanPham.setSelected(true);
                pnCenter.removeAll();
                pnCenter.add(new ProductPanel(database), BorderLayout.CENTER);
                pnCenter.updateUI();
            }
        });

        lblKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lblTrangChu.setSelected(false);
                lblNhaCungCap.setSelected(false);
                lblNhanVien.setSelected(false);
                lblKhachHang.setSelected(true);
                lblSanPham.setSelected(false);
                pnCenter.removeAll();
                pnCenter.add(new CustomerPanel(database), BorderLayout.CENTER);
                pnCenter.updateUI();
            }
        });

        lblNhaCungCap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lblTrangChu.setSelected(false);
                lblNhaCungCap.setSelected(true);
                lblNhanVien.setSelected(false);
                lblKhachHang.setSelected(false);
                lblSanPham.setSelected(false);
                pnCenter.removeAll();
                pnCenter.add(new SupplierPanel(database), BorderLayout.CENTER);
                pnCenter.updateUI();
            }
        });

        menuItemGithub.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(URI.create(
                        "https://github.com/ngochai285bkit/Sales-Management"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuItemExit.addActionListener(e -> System.exit(0));

        menuItemInformation.addActionListener(e -> new InformationDialog(MainUI.this));
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
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(() -> new MainUI("Quản lý bán hàng", new Database()));
    }
}
