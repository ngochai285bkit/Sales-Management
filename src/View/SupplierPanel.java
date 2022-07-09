package View;

import Controller.DatabaseConnection;
import Model.Database;
import Model.ProductModel;
import Model.SupplierModel;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class SupplierPanel extends JPanel {
    // attributes
    private Database database;
    private JRadioButton rbtnMaNhaCungCap,rbtnTenNhaCungCap,rbtnDiaChiNhaCungCap,rbtnDienThoai,rbtnSoTaiKhoan;
    private JButton btnSua, btnThemMoi, btnXoa, btnXuatfile;
    public static DefaultTableModel dtmDanhSachNCC;
    private JTable tbDsNCC;
    private final Color backGroundColor = new Color(245, 245, 251);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    private SupplierModel supplier;
    private JTextField txtTimKiem;


    // constructor
    public SupplierPanel(Database database) {
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
                JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
                lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
                lblTitle.setForeground(new Color(78, 138, 211));
                lblTitle.setHorizontalAlignment(JLabel.CENTER);
                lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
                pnTop.add(lblTitle, BorderLayout.CENTER);

                //Center panel
                JPanel pnCenter = new JPanel();
                pnCenter.setLayout(new BorderLayout());

                tbDsNCC = new JTable();
                tbDsNCC.setGridColor(Color.BLACK);
                tbDsNCC.setBackground(backGroundColor);
                tbDsNCC.setForeground(Color.BLACK);
                tbDsNCC.setDefaultEditor(Object.class, null);
                tbDsNCC.setRowHeight(25);
                tbDsNCC.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
                tbDsNCC.setShowGrid(true);
                tbDsNCC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tbDsNCC.setAutoCreateRowSorter(true);


                JTableHeader tableHeader = tbDsNCC.getTableHeader();
                tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                tableHeader.setBackground(new Color(79, 138, 201));
                tableHeader.setForeground(Color.WHITE);
                tableHeader.setOpaque(true);
                tableHeader.setReorderingAllowed(true);
                //tableHeader.setMaximumSize(t);
                tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

                dtmDanhSachNCC = new DefaultTableModel();
                dtmDanhSachNCC.addColumn("Mã Nhà cung cấp");
                dtmDanhSachNCC.addColumn("Tên nhà cung cấp");
                dtmDanhSachNCC.addColumn("Địa chỉ nhà cung cấp");
                dtmDanhSachNCC.addColumn("Điện thoại");
                dtmDanhSachNCC.addColumn("Số tài khoản ngân hàng");

                tbDsNCC.setModel(dtmDanhSachNCC);
                tbDsNCC.getColumnModel().getColumn(1).setPreferredWidth(100);
                JScrollPane scrollDanhSachNV = new JScrollPane(tbDsNCC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                scrollDanhSachNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.BLACK)));

                DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
                cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
                tbDsNCC.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
                tbDsNCC.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
                tbDsNCC.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
                tbDsNCC.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
                tbDsNCC.getColumnModel().getColumn(4).setCellRenderer(cellRendererCenter);
                pnCenter.add(scrollDanhSachNV, BorderLayout.CENTER);
                try {
                    showListSupplier(getAllSuppliers());
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


        rbtnMaNhaCungCap = new JRadioButton("Mã nhà cung cấp");
        rbtnMaNhaCungCap.setFont(font);
        rbtnTenNhaCungCap = new JRadioButton("Tên nhà cung cấp");
        rbtnTenNhaCungCap.setFont(font);
        rbtnDiaChiNhaCungCap = new JRadioButton("Địa chỉ nhà cung cấp");
        rbtnDiaChiNhaCungCap.setFont(font);
        rbtnDienThoai = new JRadioButton("Điện Thoại");
        rbtnDienThoai.setFont(font);
        rbtnSoTaiKhoan = new JRadioButton("Số tài khoản");
        rbtnSoTaiKhoan.setFont(font);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtnMaNhaCungCap);
        bg.add(rbtnTenNhaCungCap);
        bg.add(rbtnDiaChiNhaCungCap);
        bg.add(rbtnDienThoai);
        bg.add(rbtnSoTaiKhoan);

        txtTimKiem = new JTextField(20);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm");

        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(rbtnDiaChiNhaCungCap);
        pnTimKiem.add(rbtnMaNhaCungCap);
        pnTimKiem.add(rbtnTenNhaCungCap);
        pnTimKiem.add(rbtnDienThoai);
        pnTimKiem.add(rbtnSoTaiKhoan);

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
        btnXuatfile.setForeground(new Color(245,245,251));
        btnXuatfile.setBackground(backGroundBlue);
        btnXuatfile.setIcon(UIManager.getIcon("Tree.closedIcon"));
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

    private void addEvents() {

        btnThemMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAndChangeSupplierDialogAdd(MainUI.frame, "Thêm nhà cung cấp", database);
                try {
                    showListSupplier(getAllSuppliers());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rowSelected = tbDsNCC.getSelectedRow();
                if (rowSelected != -1) {
                    supplier = new SupplierModel();
                    supplier.setMaNhaCungCap((String) tbDsNCC.getValueAt(rowSelected, 0));
                    supplier.setTenNhaCungCap((String) tbDsNCC.getValueAt(rowSelected, 1));
                    supplier.setDiaChi((String) tbDsNCC.getValueAt(rowSelected, 2));
                    supplier.setSoDienThoai((String) tbDsNCC.getValueAt(rowSelected, 3));
                    supplier.setSoTaiKhoan((String) tbDsNCC.getValueAt(rowSelected, 4));
                    new AddAndChangeSupplierDialogEdit(MainUI.frame, "Sửa nhà cung cấp", supplier, database);
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "bạn chưa chọn hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    showListSupplier(getAllSuppliers());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsNCC.getSelectedRow();
                if (rowSelected != -1) {
                    Connection conn = DatabaseConnection.getConnection(database);
                    if (conn != null) {
                        try {
                            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_Delete(?)} ");
                            statement.setString(1, (String) tbDsNCC.getValueAt(rowSelected, 0));
                            int result = statement.executeUpdate();
                            if (result != 0) {
                                showListSupplier(getAllSuppliers());
                                JOptionPane.showMessageDialog(null, "Xóa thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"chưa chọn hàng","Thông báo",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showListSupplier(listFiltered());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showListSupplier(listFiltered());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showListSupplier(listFiltered());

            }
        });
        rbtnMaNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListSupplier(listFiltered());
            }
        });
        rbtnTenNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListSupplier(listFiltered());
            }
        });
        rbtnDiaChiNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListSupplier(listFiltered());
            }
        });
        rbtnDienThoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListSupplier(listFiltered());
            }
        });
        rbtnSoTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showListSupplier(listFiltered());
            }
        });}




    private List<SupplierModel> listFiltered() {
        List< SupplierModel> listLater = new ArrayList<>();
        String searchText = txtTimKiem.getText().toLowerCase();
        try {
            List< SupplierModel> list = getAllSuppliers();
            for ( SupplierModel  supplier : list) {
                if (txtTimKiem.getText().isEmpty()) {
                    listLater.add( supplier);
                } else {
                    if (rbtnMaNhaCungCap.isSelected()) {
                        if ( supplier.getMaNhaCungCap().toLowerCase().contains(searchText)) {
                            listLater.add( supplier);
                        }
                    } else if(rbtnTenNhaCungCap.isSelected()){
                        if ( supplier.getTenNhaCungCap().toLowerCase().contains(searchText)) {
                            listLater.add( supplier);
                        }
                    } else if(rbtnDiaChiNhaCungCap.isSelected()){
                        if ( supplier.getDiaChi().toLowerCase().contains(searchText)) {
                            listLater.add( supplier);
                        }
                    } else if (rbtnDienThoai.isSelected()){
                        if ( supplier.getSoDienThoai().toLowerCase().contains(searchText)) {
                            listLater.add( supplier);
                        }

                    } else if(rbtnSoTaiKhoan.isSelected()){
                        if ( supplier.getSoTaiKhoan().toLowerCase().contains(searchText)) {
                            listLater.add( supplier);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLater;
    }
    private SupplierModel getSupplier() {
        SupplierModel supplier = new SupplierModel();

        return supplier;
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
        dtmDanhSachNCC.setRowCount(0);
        for (SupplierModel supplierModel : listSuppliers) {
            Vector<String> vector = new Vector<>();
            vector.add(supplierModel.getMaNhaCungCap());
            vector.add(supplierModel.getTenNhaCungCap());
            vector.add(supplierModel.getDiaChi());
            vector.add(supplierModel.getSoDienThoai());
            vector.add(supplierModel.getSoTaiKhoan());
            dtmDanhSachNCC.addRow(vector);
        }
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý nhà cung cấp");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Database database = new Database("sales_management", "3306", "root", "");
        frame.getContentPane().add(new SupplierPanel(database));
        frame.setVisible(true);
    }
}