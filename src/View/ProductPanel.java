package View;

import Controller.DatabaseConnection;
import Model.Database;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ProductPanel extends JPanel {
    // attributes
    private Database database;
    private JRadioButton rbtnMaSanPham,rbtnTenSanPham,rbtnLoai,rbtnDonVi,rbtnHan,rbtnGia,rbtnSoLuong;
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
                new AddAndChangeProductDialogAdd(MainUI.frame, "Thêm sản phẩm", database);
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
                    product.setGia((String) tbDsSP.getValueAt(rowSelected, 5));
                    Date hsd = null;
                    try {
                        hsd = new SimpleDateFormat("dd/MM/yyyy").parse((String) tbDsSP.getValueAt(rowSelected, 4));

                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    product.setHan(hsd);
                    product.setSoLuong((String) tbDsSP.getValueAt(rowSelected, 6));
                    new AddAndChangeProductDialogEdit(MainUI.frame, "Sửa sản phẩm", product, database);
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
                showListProduct(listFiltered());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showListProduct(listFiltered());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showListProduct(listFiltered());

            }
        });
        rbtnMaSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });
        rbtnTenSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });
        rbtnLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });
        rbtnDonVi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });
        rbtnHan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });
        rbtnGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });

        rbtnSoLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListProduct(listFiltered());
            }
        });
    }



    private List<ProductModel> listFiltered() {
        List< ProductModel> listLater = new ArrayList<>();
        String searchText = txtTimKiem.getText().toLowerCase();
        try {
            List< ProductModel> list = getAllProducts();
            for ( ProductModel  Product : list) {
                if (txtTimKiem.getText().isEmpty()) {
                    listLater.add( Product);
                } else {
                    if (rbtnMaSanPham.isSelected()) {
                        if ( product.getMaSanPham().toLowerCase().contains(searchText)) {
                            listLater.add( product);
                        }
                    } else if(rbtnTenSanPham.isSelected()){
                        if ( product.getTenSanPham().toLowerCase().contains(searchText)) {
                            listLater.add( product);
                        }
                    } else if(rbtnLoai.isSelected()){
                        if ( product.getLoai().toLowerCase().contains(searchText)) {
                            listLater.add( product);
                        }
                    } else if (rbtnDonVi.isSelected()){
                        if ( product.getDonVi().toLowerCase().contains(searchText)) {
                            listLater.add( product);
                        }
                    } else if(rbtnHan.isSelected()){
                        String han = new SimpleDateFormat("dd/MM/yyyy").format(product.getHan());
                        if ( han.contains(searchText)) {
                            listLater.add( product);
                        }
                    } else if(rbtnGia.isSelected()){
                        if ( product.getGia().toLowerCase().contains(searchText)) {
                            listLater.add( product);
                        }
                    } else if(rbtnSoLuong.isSelected()){
                        String ngaySinh= new SimpleDateFormat("dd/MM/yyyy").format( product.getSoLuong());
                        if(ngaySinh.contains(searchText)){
                            listLater.add( product);
                        }
                    }
                    }
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLater;
    }



    private void initComponents() {
        // implementation the top panel
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        pnTop.setBackground(new Color(245, 245, 251));
        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        //Center panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        tbDsSP = new JTable();
        tbDsSP.setGridColor(Color.BLACK);
        tbDsSP.setBackground(backGroundColor);
        tbDsSP.setForeground(Color.BLACK);
        tbDsSP.setDefaultEditor(Object.class, null);
        tbDsSP.setRowHeight(25);
        tbDsSP.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        tbDsSP.setShowGrid(true);
        tbDsSP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbDsSP.setAutoCreateRowSorter(true);


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
                5, 5, 5), BorderFactory.createLineBorder(new Color(78, 138, 201), 2)));


        rbtnMaSanPham = new JRadioButton("Mã sản phẩm");
        rbtnMaSanPham.setFont(font);
        rbtnTenSanPham = new JRadioButton("Tên sản phẩm");
        rbtnTenSanPham.setFont(font);
        rbtnDonVi = new JRadioButton("Đơn vị");
        rbtnDonVi.setFont(font);
        rbtnLoai = new JRadioButton("Loại");
        rbtnLoai.setFont(font);
        rbtnHan = new JRadioButton("Hạn");
        rbtnHan.setFont(font);
        rbtnGia = new JRadioButton("Giá");
        rbtnGia.setFont(font);
        rbtnSoLuong = new JRadioButton("Số lượng");
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
                5, 5, 5), BorderFactory.createLineBorder(new Color(78, 138, 201), 2)));
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
        btnXuatfile.setForeground(new Color(245,245,251));
        btnXuatfile.setBackground(backGroundBlue);
        btnSua = new JButton("Sửa");
        btnSua.setFont(font);
        btnSua.setPreferredSize(new Dimension(200, 30));
        btnSua.setForeground(new Color(245,245,251));
        btnSua.setBackground(backGroundBlue);
        btnThemMoi = new JButton("Thêm mới");
        btnThemMoi.setFont(font);
        btnThemMoi.setPreferredSize(new Dimension(200, 30));
        btnThemMoi.setForeground(new Color(245,245,251));
        btnThemMoi.setBackground(backGroundBlue);
        btnXoa = new JButton("Xoá");
        btnXoa.setFont(font);
        btnXoa.setPreferredSize(new Dimension(200, 30));
        btnXoa.setForeground(new Color(245,245,251));
        btnXoa.setBackground(backGroundBlue);
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
                try {
                    productModel.setHan(new SimpleDateFormat("dd/MM/yyyy").parse( rs.getString("Han")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(ProductModel.getHan()));
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
