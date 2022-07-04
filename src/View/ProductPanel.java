package View;

import Controller.DatabaseConnection;
import Model.Database;
import Model.ProductModel;
import Model.ProductModel;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class ProductPanel extends JPanel {
    // attributes
    private Database database;
    private JButton btnSua, btnThemMoi, btnXoa, btnXuatfile;
    public static DefaultTableModel dtmDanhSachSP;
    private JTable tbDsSP;
    private final Color backGroundColor = new Color(245, 245, 251);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    private ProductModel product;
    private JTextField txtTimKiem;

    // constructor
    public ProductPanel(Database database) {
        this.database = database;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 251));
        initComponents();
        addEvents();
    }

    private void addEvents() {

        btnThemMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAndChangeProductDialog1(MainUI.frame, "Thêm sản phẩm", database);
                try {
                    showListProduct(getAllProducts());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rowSelected = tbDsSP.getSelectedRow();
                if (rowSelected != -1) {
                    product = new ProductModel();
                    product.setMaSanPham((String) tbDsSP.getValueAt(rowSelected, 0));
                    product.setTenSanPham((String) tbDsSP.getValueAt(rowSelected, 1));
                    product.setDonVi((String) tbDsSP.getValueAt(rowSelected, 2));
                    product.setLoai((String) tbDsSP.getValueAt(rowSelected, 3));
                    product.setGia((String) tbDsSP.getValueAt(rowSelected, 4));
                    product.setHan((String) tbDsSP.getValueAt(rowSelected, 5));
                    product.setSoLuong((String) tbDsSP.getValueAt(rowSelected, 6));
                    new AddAndChangeProductDialog2(MainUI.frame, "Sửa sản phẩm", product, database);
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "bạn chưa chọn hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    showListProduct(getAllProducts());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsSP.getSelectedRow();
                if (rowSelected != -1) {
                    Connection conn = DatabaseConnection.getConnection(database);
                    if (conn != null) {
                        try {
                            CallableStatement statement = conn.prepareCall("{ CALL sp_Product_Delete(?)} ");
                            statement.setString(1, (String) tbDsSP.getValueAt(rowSelected, 0));
                            int result = statement.executeUpdate();
                            if (result != 0) {
                                showListProduct(getAllProducts());
                                JOptionPane.showMessageDialog(null, "Xóa thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"chưa chọn hàng","Lỗi",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    List<ProductModel> dsProductFiltered = getAllProducts();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }




    private void initComponents() {
        // implementation the top panel
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTitle = new JLabel("QUẢN LÝ Sản Phẩm");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        //Center panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
//        pnCenter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
//                5, 0, 0), BorderFactory.createLineBorder(new Color(99, 200, 221, 255), 3)));

        tbDsSP = new JTable();
        //tbDsSP.set
        tbDsSP.setBackground(backGroundColor);
        tbDsSP.setForeground(Color.BLACK);
        tbDsSP.setDefaultEditor(Object.class, null);
        tbDsSP.setRowHeight(25);
        tbDsSP.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        tbDsSP.setShowGrid(true);
        tbDsSP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JTableHeader tableHeader = tbDsSP.getTableHeader();
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        tableHeader.setBackground(new Color(79, 138, 201));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(true);
        tableHeader.setReorderingAllowed(true);
        //tableHeader.setMaximumSize(t);
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        dtmDanhSachSP = new DefaultTableModel();
        dtmDanhSachSP.addColumn("Mã sản phẩm");
        dtmDanhSachSP.addColumn("Tên sản phẩm");
        dtmDanhSachSP.addColumn("Đơn vị");
        dtmDanhSachSP.addColumn("Loại");
        dtmDanhSachSP.addColumn("Hạn");
        dtmDanhSachSP.addColumn("Giá");
        dtmDanhSachSP.addColumn("Số Lượng");

        tbDsSP.setModel(dtmDanhSachSP);
        tbDsSP.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollDanhSachNV = new JScrollPane(tbDsSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scrollDanhSachNV.setBorder(BorderFactory.createEmptyBorder(4 , 10,4 ,10));
        scrollDanhSachNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.BLACK)));

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tbDsSP.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tbDsSP.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tbDsSP.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tbDsSP.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        tbDsSP.getColumnModel().getColumn(4).setCellRenderer(cellRendererCenter);
        tbDsSP.getColumnModel().getColumn(5).setCellRenderer(cellRendererCenter);
        tbDsSP.getColumnModel().getColumn(6).setCellRenderer(cellRendererCenter);
        pnCenter.add(scrollDanhSachNV, BorderLayout.CENTER);
        try {
            showListProduct(getAllProducts());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //East panel
        JPanel pnEast = new JPanel();
        pnEast.setPreferredSize(new Dimension(240, 0));
        pnEast.setLayout(new BorderLayout());
        pnEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
                5, 0, 0), BorderFactory.createLineBorder(new Color(99, 200, 221, 255), 2)));
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
        pnTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 1)));


        JRadioButton rbtnMaSanPham = new JRadioButton("Mã sản phẩm");
        rbtnMaSanPham.setFont(font);
        JRadioButton rbtnTenSanPham = new JRadioButton("Tên sản phẩm");
        rbtnTenSanPham.setFont(font);
        JRadioButton rbtnDonVi = new JRadioButton("Đơn vị");
        rbtnDonVi.setFont(font);
        JRadioButton rbtnLoai = new JRadioButton("Loại");
        rbtnLoai.setFont(font);
        JRadioButton rbtnHan = new JRadioButton("Hạn");
        rbtnHan.setFont(font);
        JRadioButton rbtnGia = new JRadioButton("Giá");
        rbtnGia.setFont(font);
        JRadioButton rbtnSoLuong = new JRadioButton("Số lượng");
        rbtnSoLuong.setFont(font);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtnMaSanPham);
        bg.add(rbtnTenSanPham);
        bg.add(rbtnDonVi);
        bg.add(rbtnLoai);
        bg.add(rbtnHan);
        bg.add(rbtnGia);
        bg.add(rbtnSoLuong);

        txtTimKiem = new JTextField(20);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm");

        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(rbtnMaSanPham);
        pnTimKiem.add(rbtnTenSanPham);
        pnTimKiem.add(rbtnDonVi);
        pnTimKiem.add(rbtnLoai);
        pnTimKiem.add(rbtnHan);
        pnTimKiem.add(rbtnGia);
        pnTimKiem.add(rbtnSoLuong);

        JPanel pnLoc = new JPanel();
        pnLoc.setLayout(new BoxLayout(pnLoc, BoxLayout.Y_AXIS));
        pnLoc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 3)));
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

        //South panel
        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnSouth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,
                5, 0, 0), BorderFactory.createLineBorder(new Color(99, 200, 221, 255), 3)));
        //pnSouth.setBackground(backGroundColor);
        btnXuatfile = new JButton("Xuất file");
        btnXuatfile.setFont(font);
        btnXuatfile.setPreferredSize(new Dimension(200, 30));
        //btnXuatfile.putClientProperty("JButton.buttonType","help");
        btnXuatfile.setIcon(UIManager.getIcon("Tree.closedIcon"));
        btnSua = new JButton("Sửa");
        btnSua.setFont(font);
        btnSua.setPreferredSize(new Dimension(200, 30));
        btnThemMoi = new JButton("Thêm Mới");
        btnThemMoi.setFont(font);
        btnThemMoi.setPreferredSize(new Dimension(200, 30));
        btnXoa = new JButton("Xoá");
        btnXoa.setFont(font);
        btnXoa.setPreferredSize(new Dimension(200, 30));
        pnSouth.add(Box.createHorizontalGlue());
        pnSouth.add(btnXuatfile);
        pnSouth.add(btnSua);
        pnSouth.add(btnThemMoi);
        pnSouth.add(btnXoa);
        pnSouth.add(Box.createHorizontalGlue());

        JPanel pnCenterto = new JPanel();
        pnCenterto.setLayout(new BorderLayout());
        pnCenterto.add(pnCenter, BorderLayout.CENTER);
        pnCenterto.add(pnSouth, BorderLayout.SOUTH);

        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnCenterto, BorderLayout.CENTER);
        this.add(pnEast, BorderLayout.EAST);


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
        dtmDanhSachSP.setRowCount(0);
        for (ProductModel ProductModel : listProduct) {
            Vector<String> vector = new Vector<>();
            vector.add(ProductModel.getMaSanPham());
            vector.add(ProductModel.getTenSanPham());
            vector.add(ProductModel.getDonVi());
            vector.add(ProductModel.getLoai());
            vector.add(ProductModel.getHan());
            vector.add(ProductModel.getGia());
            vector.add(ProductModel.getSoLuong());
            dtmDanhSachSP.addRow(vector);
        }
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý sản phẩm");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Database database = new Database("sales_management", "3306", "root", "");
        frame.getContentPane().add(new ProductPanel(database));
        frame.setVisible(true);
    }
}
