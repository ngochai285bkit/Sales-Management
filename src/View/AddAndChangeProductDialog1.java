package View;


import Controller.DatabaseConnection;
import Model.Database;
import Model.ProductModel;
import Model.ProductModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

public class AddAndChangeProductDialog1 extends JDialog {
    private JTextField txtMaSanPham, txtTenSanPham, txtGia, txtDonVi, txtLoai,txtHan,txtSoLuong;
    private JButton btnGhiLai, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 25);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private Database database;

    // constructor
    public AddAndChangeProductDialog1(Window owner, String title, Database database) {
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
        JLabel lblTieuDe = new JLabel("Thông tin sản phẩm");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);


        //The right panel
        JPanel pnEast = new JPanel();
        pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
        pnEast.setBackground(Color.WHITE);
        //pnEast.setPreferredSize(new Dimension(400, 0));

        JPanel pnMaSanPham = new JPanel();
        pnMaSanPham.setBackground(Color.WHITE);
        txtMaSanPham = new JTextField();
        txtMaSanPham.setPreferredSize(dimenTextField);
        JLabel lblMaSanPham = new JLabel("Mã sản phẩm: ");
        lblMaSanPham.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaSanPham.setPreferredSize(dimenLabel);
        pnMaSanPham.add(lblMaSanPham);
        pnMaSanPham.add(txtMaSanPham);

        JPanel pnTenSanPham = new JPanel();
        pnTenSanPham.setBackground(Color.WHITE);
        txtTenSanPham = new JTextField();
        txtTenSanPham.setPreferredSize(dimenTextField);
        JLabel lblTenSanPham = new JLabel("Tên sản phẩm: ");
        lblTenSanPham.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenSanPham.setPreferredSize(dimenLabel);
        pnTenSanPham.add(lblTenSanPham);
        pnTenSanPham.add(txtTenSanPham);

        JPanel pnGia = new JPanel();
        pnGia.setBackground(Color.WHITE);
        txtGia = new JTextField();
        txtGia.setPreferredSize(dimenTextField);
        JLabel lblGia = new JLabel("Giá ");
        lblGia.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblGia.setPreferredSize(dimenLabel);
        pnGia.add(lblGia);
        pnGia.add(txtGia);

        JPanel pnDonVi = new JPanel();
        pnDonVi.setBackground(Color.WHITE);
        txtDonVi = new JTextField();
        txtDonVi.setPreferredSize(dimenTextField);
        JLabel lblDonVi = new JLabel("Đơn vị: ");
        lblDonVi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDonVi.setPreferredSize(dimenLabel);
        pnDonVi.add(lblDonVi);
        pnDonVi.add(txtDonVi);

        JPanel pnLoai = new JPanel();
        pnLoai.setBackground(Color.WHITE);
        txtLoai = new JTextField();
        txtLoai.setPreferredSize(dimenTextField);
        JLabel lblLoai = new JLabel("Loại ");
        lblLoai.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblLoai.setPreferredSize(dimenLabel);
        pnLoai.add(lblLoai);
        pnLoai.add(txtLoai);

        JPanel pnHan = new JPanel();
        pnHan.setBackground(Color.WHITE);
        txtHan = new JTextField();
        txtHan.setPreferredSize(dimenTextField);
        JLabel lblHan = new JLabel("Hạn sử dụng: ");
        lblHan.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblHan.setPreferredSize(dimenLabel);
        pnHan.add(lblHan);
        pnHan.add(txtHan);

        JPanel pnSoLuong = new JPanel();
        pnSoLuong.setBackground(Color.WHITE);
        txtSoLuong = new JTextField();
        txtSoLuong.setPreferredSize(dimenTextField);
        JLabel lblSoLuong = new JLabel("Số lượng: ");
        lblSoLuong.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSoLuong.setPreferredSize(dimenLabel);
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtSoLuong);


        pnEast.add(Box.createVerticalGlue());
        pnEast.add(pnMaSanPham);
        pnEast.add(pnTenSanPham);
        pnEast.add(pnDonVi);
        pnEast.add(pnLoai);
        pnEast.add(pnHan);
        pnEast.add(pnGia);
        pnEast.add(pnSoLuong);
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
                        CallableStatement statement = conn.prepareCall("{ CALL sp_Product_Check(?) }");//gọi đến lệnh trong procerdeur
                        statement.setString(1, txtMaSanPham.getText());
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            int select = JOptionPane.showConfirmDialog(MainUI.frame, "Mã sản phẩm đã tồn tại.\nBạn có muốn thay đổi?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
                            if (select == JOptionPane.OK_OPTION) {
                                statement = conn.prepareCall("{ CALL sp_Product_Update(?,?,?,?,?,?,?) }");
                                statement.setString(1, txtMaSanPham.getText());
                                statement.setString(2, txtTenSanPham.getText());
                                statement.setString(3, txtDonVi.getText());
                                statement.setString(4, txtLoai.getText());
                                statement.setString(5,txtHan.getText());
                                statement.setString(6, txtGia.getText());
                                statement.setString(7,txtSoLuong.getText());
                                int result = statement.executeUpdate();
                                if(result != 0){
                                    showListProduct(getAllProducts());
                                    dispose();
                                    JOptionPane.showMessageDialog(MainUI.frame, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            statement = conn.prepareCall("{ CALL sp_Product_Add(?,?,?,?,?,?,?) }");
                            statement.setString(1, txtMaSanPham.getText());
                            statement.setString(2, txtTenSanPham.getText());
                            statement.setString(3, txtDonVi.getText());
                            statement.setString(4, txtLoai.getText());
                            statement.setString(5, txtHan.getText());
                            statement.setString(6,txtGia.getText());
                            statement.setString(7,txtSoLuong.getText());
                            int result = statement.executeUpdate();
                            if(result != 0){
                                showListProduct(getAllProducts());
                                dispose();
                                JOptionPane.showMessageDialog(MainUI.frame, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AddAndChangeProductDialog1.this, "Kết nối CSDL không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }




    private ProductModel getProduct() {
        ProductModel Product = new ProductModel();

        return Product;
    }

    private List<ProductModel> getAllProducts() throws SQLException {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Product_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setMaSanPham(rs.getString("Ma"));
                productModel.setTenSanPham(rs.getString("Ten"));
                productModel.setDonVi(rs.getString("DonVi"));
                productModel.setLoai(rs.getString("Loai"));
                productModel.setHan(rs.getString("Han"));
                productModel.setGia(rs.getString("Gia"));
                productModel.setSoLuong(rs.getString("SoLuong"));
                listProduct.add(productModel);
            }
        }
        return listProduct;
    }

    private void showListProduct(List<ProductModel> listProduct) {

        ProductPanel.dtmDanhSachSP.setRowCount(0);
        for (ProductModel ProductModel : listProduct) {
            Vector<String> vector = new Vector<>();
            vector.add(ProductModel.getMaSanPham());
            vector.add(ProductModel.getTenSanPham());
            vector.add(ProductModel.getDonVi());
            vector.add(ProductModel.getLoai());
            vector.add(ProductModel.getHan());
            vector.add(ProductModel.getGia());
            vector.add(ProductModel.getSoLuong());
            ProductPanel.dtmDanhSachSP.addRow(vector);
        }
    }

    private void showDialog(Window owner) {
        this.setSize(1000, 600);
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);


    }
}