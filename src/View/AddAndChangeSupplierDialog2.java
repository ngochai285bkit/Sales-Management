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

public class AddAndChangeSupplierDialog2 extends JDialog {
    private JTextField txtMaNhaCungCap, txtDiaChi, txtTenNhaCungCap, txtSDT, txtSoTaiKhoan;
    private JButton btnXacNhan, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 25);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private Database database;

    public AddAndChangeSupplierDialog2(Window owner, String title, SupplierModel supplier, Database database) {
        super(owner);
        this.setTitle(title);
        this.setModal(true);
        this.database = database;
        initComponents();
        setInforSupplier(supplier);

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


        JPanel pnMaNhaCungCap = new JPanel();
        pnMaNhaCungCap.setBackground(Color.WHITE);
        txtMaNhaCungCap = new JTextField();
        txtMaNhaCungCap.setEditable(false);
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

        btnXacNhan = new JButton("Xác Nhận");
        btnXacNhan.setBackground(backGroundBlue);
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setPreferredSize(new Dimension(200, 30));
        JPanel pnbtnXacNhan = new JPanel();
        pnbtnXacNhan.add(btnXacNhan);

        btnThoat = new JButton("Thoát");
        btnThoat.setBackground(backGroundBlue);
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setPreferredSize(new Dimension(200, 30));
        JPanel pnbtnThoat = new JPanel();
        pnbtnThoat.add(btnThoat);

        pnSouth.add(pnbtnXacNhan);
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
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = DatabaseConnection.getConnection(database);
                if (conn != null) {
                    try {
                        CallableStatement statement = conn.prepareCall("{CALL sp_Supplier_Update(?,?,?,?,?)}");
                        statement.setString(1, txtMaNhaCungCap.getText());
                        statement.setString(2, txtTenNhaCungCap.getText());
                        statement.setString(3, txtDiaChi.getText());
                        statement.setString(4, txtSDT.getText());
                        statement.setString(5, txtSoTaiKhoan.getText());
                        int result = statement.executeUpdate();
                        if(result==0){
                            JOptionPane.showMessageDialog(AddAndChangeSupplierDialog2.this, "Sửa thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            showListSupplier(getAllSuppliers());
                            dispose();
                            JOptionPane.showMessageDialog(AddAndChangeSupplierDialog2.this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AddAndChangeSupplierDialog2.this, "Kết nối CSDL không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }
    private List<SupplierModel> getAllSuppliers() throws SQLException {
        List<SupplierModel> listSupplier = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if(conn!=null){
            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()){
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

    private void showListSupplier(List<SupplierModel> listSuppliers){
        SupplierPanel.dtmDanhSachNCC.setRowCount(0);
        for(SupplierModel supplierModel:listSuppliers){
            Vector<String> vector = new Vector<>();
            vector.add(supplierModel.getMaNhaCungCap());
            vector.add(supplierModel.getTenNhaCungCap());
            vector.add(supplierModel.getDiaChi());
            vector.add(supplierModel.getSoDienThoai());
            vector.add(supplierModel.getSoTaiKhoan());
            SupplierPanel.dtmDanhSachNCC.addRow(vector);
        }
    }


    private void setInforSupplier(SupplierModel supplier) {

        txtMaNhaCungCap.setText(supplier.getMaNhaCungCap());
        txtTenNhaCungCap.setText(supplier.getTenNhaCungCap());
        txtDiaChi.setText(supplier.getDiaChi());
        txtSDT.setText(supplier.getSoDienThoai());
        txtSoTaiKhoan.setText(supplier.getSoTaiKhoan());
    }

    private void showDialog(Window owner) {
        this.setSize(1000, 600);
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);


    }
}