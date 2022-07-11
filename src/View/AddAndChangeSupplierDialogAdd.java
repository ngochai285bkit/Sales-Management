package View;


import Controller.DatabaseConnection;
import Controller.SupplierController;
import Model.Database;
import Model.SupplierModel;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class AddAndChangeSupplierDialogAdd extends JDialog {
    private JTextField txtMaNhaCungCap, txtDiaChi, txtTenNhaCungCap, txtSDT, txtSoTaiKhoan;
    private JButton btnGhiLai, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Database database;

    // constructor
    public AddAndChangeSupplierDialogAdd(Window owner, String title, Database database) {
        super(owner);
        this.setTitle(title);
        this.setModal(true);
        this.database = database;
        initComponents();

        addEvents();
        showDialog(owner);
    }

    private void initComponents() {
        //The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTieuDe = new JLabel("Thông tin nhân viên");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);


        //The right panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setBackground(new Color(245, 245, 251));


        JPanel pnMaNhaCungCap = new JPanel();
        pnMaNhaCungCap.setBackground(new Color(245, 245, 251));
        txtMaNhaCungCap = new JTextField();

        txtMaNhaCungCap.setPreferredSize(dimenTextField);
<<<<<<< HEAD
        txtMaNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
=======
        txtMaNhaCungCap.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));
>>>>>>> 8031b50 (update giao diện)
        JLabel lblMaNhaCungCap = new JLabel("Mã nhà cung cấp: ");
        lblMaNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaNhaCungCap.setPreferredSize(dimenLabel);
        pnMaNhaCungCap.add(lblMaNhaCungCap);
        pnMaNhaCungCap.add(txtMaNhaCungCap);

        JPanel pnTenNhaCungCap = new JPanel();
        pnTenNhaCungCap.setBackground(new Color(245, 245, 251));
        txtTenNhaCungCap = new JTextField();
        txtTenNhaCungCap.setPreferredSize(dimenTextField);
        txtTenNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        JLabel lblTenNhaCungCap = new JLabel("Tên nhà cung cấp: ");
        lblTenNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenNhaCungCap.setPreferredSize(dimenLabel);
        pnTenNhaCungCap.add(lblTenNhaCungCap);
        pnTenNhaCungCap.add(txtTenNhaCungCap);

        JPanel pnDiaChi = new JPanel();
        pnDiaChi.setBackground(new Color(245, 245, 251));
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(dimenTextField);
        txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDiaChi.setPreferredSize(dimenLabel);
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnSDT = new JPanel();
        pnSDT.setBackground(new Color(245, 245, 251));
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(dimenTextField);
        txtSDT.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSDT.setPreferredSize(dimenLabel);
        pnSDT.add(lblSDT);
        pnSDT.add(txtSDT);

        JPanel pnSoTaiKhoan = new JPanel();
        pnSoTaiKhoan.setBackground(new Color(245, 245, 251));
        txtSoTaiKhoan = new JTextField();
        txtSoTaiKhoan.setPreferredSize(dimenTextField);
        txtSoTaiKhoan.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        JLabel lblSoTaiKhoan = new JLabel("Số Tài Khoản: ");
        lblSoTaiKhoan.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSoTaiKhoan.setPreferredSize(dimenLabel);
        pnSoTaiKhoan.add(lblSoTaiKhoan);
        pnSoTaiKhoan.add(txtSoTaiKhoan);


        pnCenter.add(Box.createVerticalGlue());
        pnCenter.add(pnMaNhaCungCap);
        pnCenter.add(pnTenNhaCungCap);
        pnCenter.add(pnDiaChi);
        pnCenter.add(pnSDT);
        pnCenter.add(pnSoTaiKhoan);
        pnCenter.add(Box.createVerticalGlue());

        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(new Color(245, 245, 251));

        btnGhiLai = new JButton("Xác nhận");
        btnGhiLai.setIcon(new FlatSVGIcon(Objects.requireNonNull(AddAndChangeProductDialogAdd.class.getResource("/Images/24x24/checked_24x24.svg"))));
        btnGhiLai.setBackground(Color.WHITE);
        btnGhiLai.setForeground(Color.BLACK);
        btnGhiLai.setPreferredSize(new Dimension(160, 38));
<<<<<<< HEAD
        btnGhiLai.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
=======
        btnGhiLai.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
>>>>>>> 8031b50 (update giao diện)
        JPanel pnbtnGhiLai = new JPanel();
        pnbtnGhiLai.setBackground(new Color(245, 245, 251));
        pnbtnGhiLai.add(btnGhiLai);

        btnThoat = new JButton("Thoát");
<<<<<<< HEAD
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(AddAndChangeSupplierDialogAdd.class.getResource("/Images/24x24/exitDialog_24x24.svg"))));
        btnThoat.setBackground(Color.WHITE);
        btnThoat.setForeground(Color.BLACK);
        btnThoat.setPreferredSize(new Dimension(160, 38));
        btnThoat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
=======
        btnThoat.setIcon(new FlatSVGIcon(AddAndChangeSupplierDialogAdd.class.getResource("/Images/24x24/exitDialog_24x24.svg")));
        btnThoat.setBackground(Color.WHITE);
        btnThoat.setForeground(Color.BLACK);
        btnThoat.setPreferredSize(new Dimension(160, 38));
        btnThoat.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
>>>>>>> 8031b50 (update giao diện)
        JPanel pnbtnThoat = new JPanel();
        pnbtnThoat.setBackground(new Color(245, 245, 251));
        pnbtnThoat.add(btnThoat);

        pnSouth.add(pnbtnGhiLai);
        pnSouth.add(pnbtnThoat);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnSouth, BorderLayout.SOUTH);

        Container con = this.getContentPane();
        con.add(pnMain);


    }

    private void addEvents() {
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnGhiLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(SupplierController.checkSupplier(database, txtMaNhaCungCap.getText())){
                        JOptionPane.showMessageDialog(MainUI.frame, "Mã đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        SupplierModel supplier = new SupplierModel();
                        supplier.setMaNhaCungCap(txtMaNhaCungCap.getText());
                        supplier.setDiaChi(txtDiaChi.getText());
                        supplier.setSoTaiKhoan(txtSoTaiKhoan.getText());
                        supplier.setSoDienThoai(txtSDT.getText());
                        supplier.setTenNhaCungCap(txtTenNhaCungCap.getText());
                        if(SupplierController.addSupplier(database, supplier)){
                            showListSupplier(SupplierController.getAllSuppliers(database));
                            dispose();
                            JOptionPane.showMessageDialog(MainUI.frame, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(MainUI.frame, "Thêm thất bại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    private void showListSupplier(List<SupplierModel> listSuppliers) {
        SupplierPanel.dtmDanhSachNCC.setRowCount(0);
        for (SupplierModel supplierModel : listSuppliers) {
            Vector<String> vector = new Vector<>();
            vector.add(supplierModel.getMaNhaCungCap());
            vector.add(supplierModel.getTenNhaCungCap());
            vector.add(supplierModel.getDiaChi());
            vector.add(supplierModel.getSoDienThoai());
            vector.add(supplierModel.getSoTaiKhoan());
            SupplierPanel.dtmDanhSachNCC.addRow(vector);
        }
    }

    private void showDialog(Window owner) {
        this.setSize(809, 500);
        this.setLocationRelativeTo(owner);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);


    }
}