package View;


import Controller.DatabaseConnection;
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

public class AddAndChangeSupplierDialogEdit extends JDialog {
    private JTextField txtMaNhaCungCap, txtDiaChi, txtTenNhaCungCap, txtSDT, txtSoTaiKhoan;
    private JButton btnXacNhan, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private Database database;

    public AddAndChangeSupplierDialogEdit(Window owner, String title, SupplierModel supplier, Database database) {
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
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTieuDe = new JLabel("Thông tin nhà cung cấp");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);


        //The Center panel
        JPanel pnCenter = new JPanel();
        //pnCenter.setPreferredSize(new Dimension(400,0));
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setBackground(new Color(245, 245, 251));


        JPanel pnMaNhaCungCap = new JPanel();
        pnMaNhaCungCap.setBackground(new Color(245, 245, 251));
        txtMaNhaCungCap = new JTextField();
        txtMaNhaCungCap.setEditable(false);
        txtMaNhaCungCap.setPreferredSize(dimenTextField);
        txtMaNhaCungCap.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));;
        JLabel lblMaNhaCungCap = new JLabel("Mã nhà cung cấp: ");
        lblMaNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaNhaCungCap.setPreferredSize(dimenLabel);
        pnMaNhaCungCap.add(lblMaNhaCungCap);
        pnMaNhaCungCap.add(txtMaNhaCungCap);

        JPanel pnTenNhaCungCap = new JPanel();
        pnTenNhaCungCap.setBackground(new Color(245, 245, 251));
        txtTenNhaCungCap = new JTextField();
        txtTenNhaCungCap.setPreferredSize(dimenTextField);
        txtTenNhaCungCap.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));
        JLabel lblTenNhaCungCap = new JLabel("Tên nhà cung cấp: ");
        lblTenNhaCungCap.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenNhaCungCap.setPreferredSize(dimenLabel);
        pnTenNhaCungCap.add(lblTenNhaCungCap);
        pnTenNhaCungCap.add(txtTenNhaCungCap);

        JPanel pnDiaChi = new JPanel();
        pnDiaChi.setBackground(new Color(245, 245, 251));
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(dimenTextField);
        txtDiaChi.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDiaChi.setPreferredSize(dimenLabel);
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnSDT = new JPanel();
        pnSDT.setBackground(new Color(245, 245, 251));
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(dimenTextField);
        txtSDT.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSDT.setPreferredSize(dimenLabel);
        pnSDT.add(lblSDT);
        pnSDT.add(txtSDT);

        JPanel pnSoTaiKhoan = new JPanel();
        pnSoTaiKhoan.setBackground(new Color(245, 245, 251));
        txtSoTaiKhoan = new JTextField();
        txtSoTaiKhoan.setPreferredSize(dimenTextField);
        txtSoTaiKhoan.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));
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

        //South panel
        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnSouth.setBackground(new Color(245, 245, 251));

        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setIcon(new FlatSVGIcon(Objects.requireNonNull(AddAndChangeSupplierDialogEdit.class.getResource("/Images/24x24/checked_24x24.svg"))));
        btnXacNhan.setBackground(Color.WHITE);
        btnXacNhan.setForeground(Color.BLACK);
        btnXacNhan.setPreferredSize(new Dimension(160, 38));
        btnXacNhan.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,19));
        JPanel pnbtnXacNhan = new JPanel();
        pnbtnXacNhan.setBackground(new Color(245, 245, 251));
        pnbtnXacNhan.add(btnXacNhan);

        btnThoat = new JButton("Thoát");
        btnThoat.setIcon(new FlatSVGIcon(Objects.requireNonNull(AddAndChangeSupplierDialogEdit.class.getResource("/Images/24x24/exitDialog_24x24.svg"))));
        btnThoat.setBackground(Color.WHITE);
        btnThoat.setForeground(Color.BLACK);
        btnThoat.setPreferredSize(new Dimension(160, 38));
        btnThoat.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,19));
        JPanel pnbtnThoat = new JPanel();
        pnbtnThoat.setBackground(new Color(245, 245, 251));
        pnbtnThoat.add(btnThoat);

        pnSouth.add(pnbtnXacNhan);
        pnSouth.add(pnbtnThoat);

        //Main Panel
        JPanel pnMain = new JPanel();
        pnMain.setBackground(new Color(245, 245, 251));
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
                            JOptionPane.showMessageDialog(AddAndChangeSupplierDialogEdit.this, "Sửa thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            showListSupplier(getAllSuppliers());
                            dispose();
                            JOptionPane.showMessageDialog(AddAndChangeSupplierDialogEdit.this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AddAndChangeSupplierDialogEdit.this, "Kết nối CSDL không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
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
        this.setSize(809, 500);
        this.setLocationRelativeTo(owner);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);


    }
}