package View;

import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePanel extends JPanel {
    // attributes
    private Database database;
    private final Color backGroundColor = new Color(245, 245, 251);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    private JButton btnSua, btnThemMoi, btnXoa;
    private static DefaultTableModel dtmDsNhanVien;
    private JRadioButton rbtnMaNhanVien, rbtnTenNhanVien, rbtnDiaChi, rbtnDienThoai, rbtnChucVu, rbtnNgaySinh, rbtnNgayBatDauLam, rbtnGioiTinh;
    private JTable tbDsNhanVien;

    public EmployeePanel(){

    }
    // constructor
    public EmployeePanel(Database database) {
        this.database = database;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 251));
        initComponents();
        addEvents();

    }

    private void initComponents() {
        // implementation the top panel
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

        tbDsNhanVien = new JTable();
        tbDsNhanVien.setBackground(backGroundColor);
        tbDsNhanVien.setForeground(Color.BLACK);
        tbDsNhanVien.setDefaultEditor(Object.class, null);
        tbDsNhanVien.setRowHeight(25);
        tbDsNhanVien.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        tbDsNhanVien.setShowGrid(true);

        JTableHeader tableHeader = tbDsNhanVien.getTableHeader();
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        tableHeader.setBackground(new Color(79, 138, 201));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(true);
        tableHeader.setReorderingAllowed(true);
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        dtmDsNhanVien = new DefaultTableModel();
        dtmDsNhanVien.addColumn("Mã nhân viên");
        dtmDsNhanVien.addColumn("Tên nhân viên");
        dtmDsNhanVien.addColumn("Địa chỉ");
        dtmDsNhanVien.addColumn("Điện thoại");
        dtmDsNhanVien.addColumn("Chức vụ");
        dtmDsNhanVien.addColumn("Ngày sinh");
        dtmDsNhanVien.addColumn("Ngày bắt đầu làm");
        dtmDsNhanVien.addColumn("Giới tính");
        tbDsNhanVien.setModel(dtmDsNhanVien);
        JScrollPane scrollDanhSachKH = new JScrollPane(tbDsNhanVien, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollDanhSachNV.setBorder(BorderFactory.createEmptyBorder(4 , 10,4 ,10));
        scrollDanhSachKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.BLACK)));

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tbDsNhanVien.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(4).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(5).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(6).setCellRenderer(cellRendererCenter);
        tbDsNhanVien.getColumnModel().getColumn(7).setCellRenderer(cellRendererCenter);
        pnCenter.add(scrollDanhSachKH, BorderLayout.CENTER);

        //Panel bottom

        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnSouth.setBackground(backGroundColor);
        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(new Dimension(200, 30));
        btnThemMoi = new JButton("Thêm Mới");
        btnThemMoi.setPreferredSize(new Dimension(200, 30));
        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(new Dimension(200, 30));
        pnSouth.add(Box.createHorizontalGlue());
        pnSouth.add(btnSua);
        pnSouth.add(btnThemMoi);
        pnSouth.add(btnXoa);
        pnSouth.add(Box.createHorizontalGlue());
        pnSouth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0), BorderFactory.createLineBorder(backGroundBlue, 3)));

        // East panel
        JPanel pnEast = new JPanel();
        pnEast.setPreferredSize(new Dimension(240, 0));
        pnEast.setLayout(new BorderLayout());
        pnEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 1)));
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
        pnTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 1)));


        rbtnMaNhanVien = new JRadioButton("Mã nhân viên");
        rbtnMaNhanVien.setFont(font);
        rbtnTenNhanVien = new JRadioButton("Tên nhân viên");
        rbtnTenNhanVien.setFont(font);
        rbtnDiaChi = new JRadioButton("Địa chỉ");
        rbtnDiaChi.setFont(font);
        rbtnDienThoai = new JRadioButton("Điện Thoại");
        rbtnDienThoai.setFont(font);
        rbtnChucVu = new JRadioButton("Chức vụ");
        rbtnChucVu.setFont(font);
        rbtnNgaySinh = new JRadioButton("Ngày sinh");
        rbtnNgaySinh.setFont(font);
        rbtnNgayBatDauLam = new JRadioButton("Ngày bắt đầu làm");
        rbtnNgayBatDauLam.setFont(font);
        rbtnGioiTinh = new JRadioButton("Giới tính");
        rbtnGioiTinh.setFont(font);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtnMaNhanVien);
        buttonGroup.add(rbtnTenNhanVien);
        buttonGroup.add(rbtnDiaChi);
        buttonGroup.add(rbtnDienThoai);
        buttonGroup.add(rbtnChucVu);
        buttonGroup.add(rbtnNgaySinh);
        buttonGroup.add(rbtnNgayBatDauLam);
        buttonGroup.add(rbtnGioiTinh);

        JTextField txtTimKiem = new JTextField(20);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm");

        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(rbtnMaNhanVien);
        pnTimKiem.add(rbtnTenNhanVien);
        pnTimKiem.add(rbtnDiaChi);
        pnTimKiem.add(rbtnDienThoai);
        pnTimKiem.add(rbtnChucVu);
        pnTimKiem.add(rbtnNgaySinh);
        pnTimKiem.add(rbtnNgayBatDauLam);
        pnTimKiem.add(rbtnGioiTinh);

        JPanel pnLoc = new JPanel();
        pnLoc.setLayout(new BoxLayout(pnLoc, BoxLayout.Y_AXIS));
        pnLoc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 1)));
        JCheckBox chka = new JCheckBox();
        JCheckBox chkb = new JCheckBox();
        JCheckBox chkc = new JCheckBox();
        JButton btnLoc = new JButton("Lọc");
        btnLoc.setFont(font);

        pnLoc.add(chka);
        pnLoc.add(chkb);
        pnLoc.add(chkc);
        pnLoc.add(btnLoc);

        pnEast.add(pnTimKiem, BorderLayout.NORTH);
        pnEast.add(pnLoc, BorderLayout.CENTER);

        JPanel pnCenterMain = new JPanel();
        pnCenterMain.setLayout(new BorderLayout());
        pnCenterMain.add(pnCenter, BorderLayout.CENTER);
        pnCenterMain.add(pnSouth, BorderLayout.SOUTH);

        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnCenterMain, BorderLayout.CENTER);
        this.add(pnEast, BorderLayout.EAST);
    }

    public void addEvents() {
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAndChangeEmployeeDialog(MainUI.frame, "Sửa thông tin nhân viên", database);
            }
        });
        btnThemMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAndChangeEmployeeDialog(MainUI.frame, "Thêm nhân viên", database);
            }
        });
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý nhân viên");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(new EmployeePanel(new Database()));
        frame.setVisible(true);
    }
}
