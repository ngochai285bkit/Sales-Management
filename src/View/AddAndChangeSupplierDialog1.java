package View;


import Controller.DatabaseConnection;
import Model.Database;
import Model.SupplierModel;

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
import java.util.Vector;

public class AddAndChangeSupplierDialog1 extends JDialog {
    private JTextField txtMaNhaCungCap, txtDiaChi, txtTenNhaCungCap, txtSDT, txtSoTaiKhoan;
    private JButton btnGhiLai, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 25);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private Database database;

    // constructor
    public AddAndChangeSupplierDialog1(Window owner, String title, Database database) {
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
        pnTop.setBackground(Color.WHITE);
        JLabel lblTieuDe = new JLabel("Thông tin nhân viên");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);


        //The right panel
        JPanel pnEast = new JPanel();
        pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
        pnEast.setBackground(Color.WHITE);
        //pnEast.setPreferredSize(new Dimension(400, 0));

        JPanel pnMaNhaCungCap = new JPanel();
        pnMaNhaCungCap.setBackground(Color.WHITE);
        txtMaNhaCungCap = new JTextField();
        txtMaNhaCungCap.setPreferredSize(dimenTextField);
        JLabel lblMaNhaCungCap = new JLabel("Mã nhà cung cấp: ");
        lblMaNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaNhaCungCap.setPreferredSize(dimenLabel);
        pnMaNhaCungCap.add(lblMaNhaCungCap);
        pnMaNhaCungCap.add(txtMaNhaCungCap);

        JPanel pnTenNhaCungCap = new JPanel();
        pnTenNhaCungCap.setBackground(Color.WHITE);
        txtTenNhaCungCap = new JTextField();
        txtTenNhaCungCap.setPreferredSize(dimenTextField);
        JLabel lblTenNhaCungCap = new JLabel("Tên nhà cung cấp: ");
        lblTenNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenNhaCungCap.setPreferredSize(dimenLabel);
        pnTenNhaCungCap.add(lblTenNhaCungCap);
        pnTenNhaCungCap.add(txtTenNhaCungCap);

        JPanel pnDiaChi = new JPanel();
        pnDiaChi.setBackground(Color.WHITE);
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(dimenTextField);
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDiaChi.setPreferredSize(dimenLabel);
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnSDT = new JPanel();
        pnSDT.setBackground(Color.WHITE);
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(dimenTextField);
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSDT.setPreferredSize(dimenLabel);
        pnSDT.add(lblSDT);
        pnSDT.add(txtSDT);

        JPanel pnSoTaiKhoan = new JPanel();
        pnSoTaiKhoan.setBackground(Color.WHITE);
        txtSoTaiKhoan = new JTextField();
        txtSoTaiKhoan.setPreferredSize(dimenTextField);
        JLabel lblSoTaiKhoan = new JLabel("Số Tài Khoản: ");
        lblSoTaiKhoan.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSoTaiKhoan.setPreferredSize(dimenLabel);
        pnSoTaiKhoan.add(lblSoTaiKhoan);
        pnSoTaiKhoan.add(txtSoTaiKhoan);


        pnEast.add(Box.createVerticalGlue());
        pnEast.add(pnTenNhaCungCap);
        pnEast.add(pnMaNhaCungCap);
        pnEast.add(pnDiaChi);
        pnEast.add(pnSDT);
        pnEast.add(pnSoTaiKhoan);
        pnEast.add(Box.createVerticalGlue());

        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(Color.WHITE);

        btnGhiLai = new JButton("Ghi Lại");
        btnGhiLai.setBackground(backGroundBlue);
        btnGhiLai.setForeground(Color.WHITE);
        btnGhiLai.setPreferredSize(new Dimension(200, 30));
        JPanel pnbtnGhiLai = new JPanel();
        pnbtnGhiLai.add(btnGhiLai);

        btnThoat = new JButton("Thoát");
        btnThoat.setBackground(backGroundBlue);
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setPreferredSize(new Dimension(200, 30));
        JPanel pnbtnThoat = new JPanel();
        pnbtnThoat.add(btnThoat);

        pnSouth.add(pnbtnGhiLai);
        pnSouth.add(pnbtnThoat);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnEast, BorderLayout.CENTER);
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


                Connection conn = DatabaseConnection.getConnection(database);
                if (conn != null) {
                    try {
                        CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_Check(?) }");//gọi đến lệnh trong procerdeur
                        statement.setString(1, txtMaNhaCungCap.getText());
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            int select = JOptionPane.showConfirmDialog(MainUI.frame, "Mã khách hàng đã tồn tại.\nBạn có muốn thay đổi?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
                            if (select == JOptionPane.OK_OPTION) {
                                statement = conn.prepareCall("{ CALL sp_Supplier_Update(?,?,?,?,?) }");
                                statement.setString(1, txtMaNhaCungCap.getText());
                                statement.setString(2, txtTenNhaCungCap.getText());
                                statement.setString(3, txtDiaChi.getText());
                                statement.setString(4, txtSDT.getText());
                                statement.setString(5, txtSoTaiKhoan.getText());
                                int result = statement.executeUpdate();
                                if(result != 0){
                                    showListSupplier(getAllSuppliers());
                                    dispose();
                                    JOptionPane.showMessageDialog(MainUI.frame, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            statement = conn.prepareCall("{ CALL sp_Supplier_Add(?,?,?,?,?) }");
                            statement.setString(1, txtMaNhaCungCap.getText());
                            statement.setString(2, txtTenNhaCungCap.getText());
                            statement.setString(3, txtDiaChi.getText());
                            statement.setString(4, txtSDT.getText());
                            statement.setString(5, txtSoTaiKhoan.getText());
                            int result = statement.executeUpdate();
                            if(result != 0){
                                showListSupplier(getAllSuppliers());
                                dispose();
                                JOptionPane.showMessageDialog(MainUI.frame, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AddAndChangeSupplierDialog1.this, "Kết nối CSDL không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }




    private List<SupplierModel> getAllSuppliers() throws SQLException {
        List<SupplierModel> listSupplier = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                SupplierModel supplierModel = new SupplierModel();
                supplierModel.setMaNhaCungCap(rs.getString("Ma"));
                supplierModel.setTenNhaCungCap(rs.getString("Ten"));
                supplierModel.setDiaChi(rs.getString("DiaChi"));
                supplierModel.setSoDienThoai(rs.getString("SoDienThoai"));
                supplierModel.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                listSupplier.add(supplierModel);
            }
        }
        return listSupplier;
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
        this.setSize(1000, 600);
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);


    }
}