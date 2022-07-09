package View;


import Controller.DatabaseConnection;
import Model.Database;
import Model.ProductModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AddAndChangeProductDialogEdit extends JDialog {
    private JTextField txtMaSanPham, txtTenSanPham, txtGia, txtDonVi, txtLoai,txtSoLuong;
    private JDatePicker txtHan;
    private JButton btnXacNhan, btnThoat;
    private final Dimension dimenLabel = new Dimension(200, 25);
    private final Dimension dimenTextField = new Dimension(200, 30);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private Database database;
    private ProductModel product;
    

    public AddAndChangeProductDialogEdit(Window owner, String title, ProductModel product, Database database) {
        super(owner);
        this.setTitle(title);
        this.setModal(true);
        this.database = database;
        this.product = product;
        initComponents();
        setInforProduct(product);

        addEvents();
        showDialog(owner);

    }

    private void initComponents() {
        //The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTieuDe = new JLabel("Thông tin sản phẩm");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);


        //The right panel
        JPanel pnEast = new JPanel();
        pnEast.setBackground(new Color(245, 245, 251));
        pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
        
        

        JPanel pnMaSanPham = new JPanel();
        pnMaSanPham.setBackground(new Color(245, 245, 251));
        txtMaSanPham = new JTextField();
        txtMaSanPham.setEditable(false);
        txtMaSanPham.setPreferredSize(dimenTextField);
        txtMaSanPham.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        JLabel lblMaSanPham = new JLabel("Mã sản phẩm: ");
        lblMaSanPham.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaSanPham.setPreferredSize(dimenLabel);
        pnMaSanPham.add(lblMaSanPham);
        pnMaSanPham.add(txtMaSanPham);

        JPanel pnTenSanPham = new JPanel();
        pnTenSanPham.setBackground(new Color(245, 245, 251));
        txtTenSanPham = new JTextField();
        txtTenSanPham.setPreferredSize(dimenTextField);
        txtTenSanPham.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        JLabel lblTenSanPham = new JLabel("Tên sản phẩm: ");
        lblTenSanPham.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenSanPham.setPreferredSize(dimenLabel);
        pnTenSanPham.add(lblTenSanPham);
        pnTenSanPham.add(txtTenSanPham);

        JPanel pnGia = new JPanel();
        pnGia.setBackground(new Color(245, 245, 251));
        txtGia = new JTextField();
        txtGia.setPreferredSize(dimenTextField);
        txtGia.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        JLabel lblGia = new JLabel("Giá: ");
        lblGia.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblGia.setBackground(new Color(245, 245, 251));
        lblGia.setPreferredSize(dimenLabel);
        pnGia.add(lblGia);
        pnGia.add(txtGia);

        JPanel pnDonVi = new JPanel();
        pnDonVi.setBackground(new Color(245, 245, 251));
        txtDonVi = new JTextField();
        txtDonVi.setPreferredSize(dimenTextField);
        txtDonVi.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        JLabel lblDonVi = new JLabel("Đơn vị: ");
        lblDonVi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDonVi.setPreferredSize(dimenLabel);
        pnDonVi.add(lblDonVi);
        pnDonVi.add(txtDonVi);

        JPanel pnLoai = new JPanel();
        pnLoai.setBackground(new Color(245, 245, 251));
        txtLoai = new JTextField();
        txtLoai.setPreferredSize(dimenTextField);
        txtLoai.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        JLabel lblLoai = new JLabel("Loại: ");
        lblLoai.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblLoai.setPreferredSize(dimenLabel);
        pnLoai.add(lblLoai);
        pnLoai.add(txtLoai);

        JPanel pnHan = new JPanel();
        pnHan.setBackground(new Color(245, 245, 251));
        txtHan = new JDatePicker(product.getHan());
        txtHan.setPreferredSize(dimenTextField);
        txtHan.getComponent(0).setPreferredSize(new Dimension(170,30));
        txtHan.getComponent(1).setPreferredSize(new Dimension(30,30));
        txtHan.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        JLabel lblHan = new JLabel("Hạn: ");
        lblHan.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblHan.setPreferredSize(dimenLabel);
        pnHan.add(lblHan);
        pnHan.add(txtHan);

        JPanel pnSoLuong = new JPanel();
        pnSoLuong.setBackground(new Color(245, 245, 251));
        txtSoLuong = new JTextField();
        txtSoLuong.setPreferredSize(dimenTextField);
        txtSoLuong.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
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
        pnSouth.setBackground(new Color(245, 245, 251));
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER));


        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setBackground(backGroundBlue);
        btnXacNhan.setForeground(new Color(245, 245, 251));
        btnXacNhan.setPreferredSize(new Dimension(200, 30));
        btnXacNhan.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,19));
        JPanel pnbtnXacNhan = new JPanel();
        pnbtnXacNhan.setBackground(new Color(245, 245, 251));
        pnbtnXacNhan.add(btnXacNhan);

        btnThoat = new JButton("Thoát");
        btnThoat.setBackground(backGroundBlue);
        btnThoat.setForeground(new Color(245, 245, 251));
        btnThoat.setPreferredSize(new Dimension(200, 30));
        btnThoat.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,19));
        JPanel pnbtnThoat = new JPanel();
        pnbtnThoat.setBackground(new Color(245, 245, 251));
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
                        CallableStatement statement = conn.prepareCall("{CALL sp_Product_Update(?,?,?,?,?,?,?)}");
                        statement.setString(1,txtMaSanPham.getText());
                        statement.setString(2,txtTenSanPham.getText());
                        statement.setString(3,txtDonVi.getText());
                        statement.setString(4,txtLoai.getText());
                        statement.setString(5,new SimpleDateFormat("dd/MM/yyyy").format(txtHan.getModel().getValue()));
                        statement.setString(6,txtGia.getText());
                        statement.setString(7,txtSoLuong.getText());
                        int result = statement.executeUpdate();
                        if(result==0){
                            JOptionPane.showMessageDialog(AddAndChangeProductDialogEdit.this, "Sửa thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            showListProduct(getAllProducts());
                            dispose();
                            JOptionPane.showMessageDialog(AddAndChangeProductDialogEdit.this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AddAndChangeProductDialogEdit.this, "Kết nối CSDL không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

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
                String sDate = rs.getString("Han");
                Date hsd = null;
                try {
                    hsd = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                productModel.setHan(hsd);
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
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(ProductModel.getHan()));
            vector.add(ProductModel.getGia());
            vector.add(ProductModel.getSoLuong());
            ProductPanel.dtmDanhSachSP.addRow(vector);
        }
    }


    private void setInforProduct(ProductModel Product) {

        txtMaSanPham.setText(Product.getMaSanPham());
        txtTenSanPham.setText(Product.getTenSanPham());
        txtDonVi.setText(Product.getDonVi());
        txtLoai.setText(Product.getLoai());
        //txtHan.setText(new SimpleDateFormat("dd/MM/yyyy").format(Product.getHan()));
        //txtHan.getModel().setDate();
        txtGia.setText(Product.getGia());
        txtSoLuong.setText(Product.getSoLuong());
    }

    private void showDialog(Window owner) {
        this.setSize(1000, 600);
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);


    }
}