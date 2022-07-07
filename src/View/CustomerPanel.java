package View;

import Controller.DatabaseConnection;
import Model.CustomerModel;
import Model.Database;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CustomerPanel extends JPanel {
    // attributes
    private final Database database;
    private final Color backGroundColor = new Color(245, 245, 251);
    private final Color backGroundBlue = new Color(78, 138, 201);
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
    private JButton btnSua, btnThemMoi, btnXoa;
    public static DefaultTableModel dtmDsKhachHang;
    private JTable tbDsKhachHang;
    private CustomerModel custom;

    // constructor
    public CustomerPanel(Database database) {
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
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTitle.setForeground(new Color(78, 138, 211));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnTop.add(lblTitle, BorderLayout.CENTER);

        // Panel center
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));


        tbDsKhachHang = new JTable();
        tbDsKhachHang.setBackground(backGroundColor);
        tbDsKhachHang.setForeground(Color.BLACK);
        tbDsKhachHang.setDefaultEditor(Object.class, null);
        tbDsKhachHang.setRowHeight(25);
        tbDsKhachHang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        tbDsKhachHang.setShowGrid(true);

        JTableHeader tableHeader = tbDsKhachHang.getTableHeader();
        tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        tableHeader.setBackground(new Color(79, 138, 201));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(true);
        tableHeader.setReorderingAllowed(true);
        //tableHeader.setMaximumSize(t);
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        dtmDsKhachHang = new DefaultTableModel();
        dtmDsKhachHang.addColumn("Mã khách hàng");
        dtmDsKhachHang.addColumn("Tên khách hàng");
        dtmDsKhachHang.addColumn("Địa chỉ");
        dtmDsKhachHang.addColumn("Điện thoại");
        tbDsKhachHang.setModel(dtmDsKhachHang);
        JScrollPane scrollDanhSachKH = new JScrollPane(tbDsKhachHang, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollDanhSachNV.setBorder(BorderFactory.createEmptyBorder(4 , 10,4 ,10));
        scrollDanhSachKH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.BLACK)));

        DefaultTableCellRenderer cellRendererCenter = new DefaultTableCellRenderer();
        cellRendererCenter.setHorizontalAlignment(JLabel.CENTER);
        tbDsKhachHang.getColumnModel().getColumn(0).setCellRenderer(cellRendererCenter);
        tbDsKhachHang.getColumnModel().getColumn(1).setCellRenderer(cellRendererCenter);
        tbDsKhachHang.getColumnModel().getColumn(2).setCellRenderer(cellRendererCenter);
        tbDsKhachHang.getColumnModel().getColumn(3).setCellRenderer(cellRendererCenter);
        pnCenter.add(scrollDanhSachKH, BorderLayout.CENTER);

        try {
            showListCustomer(getAllCustomer());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Panel Bottom

        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnSouth.setBackground(backGroundColor);
        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(new Dimension(200, 30));
        btnSua.setBackground(backGroundBlue);
        btnSua.setForeground(Color.WHITE);
        btnSua.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnThemMoi = new JButton("Thêm mới");
        btnThemMoi.setPreferredSize(new Dimension(200, 30));
        btnThemMoi.setBackground(backGroundBlue);
        btnThemMoi.setForeground(Color.WHITE);
        btnThemMoi.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(new Dimension(200, 30));
        btnXoa.setBackground(backGroundBlue);
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

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
        pnTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,
                5, 0, 0), BorderFactory.createLineBorder(new Color(78, 138, 201), 1)));


        JRadioButton rbtnMaNhaNhanVien = new JRadioButton("Mã khách hàng");
        rbtnMaNhaNhanVien.setFont(font);
        JRadioButton rbtnTenNhaNhanVien = new JRadioButton("Tên khách hàng");
        rbtnTenNhaNhanVien.setFont(font);
        JRadioButton rbtnDiaChiNhaNhanVien = new JRadioButton("Địa chỉ khách hàng");
        rbtnDiaChiNhaNhanVien.setFont(font);
        JRadioButton rbtnDienThoai = new JRadioButton("Điện Thoại");
        rbtnDienThoai.setFont(font);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtnMaNhaNhanVien);
        buttonGroup.add(rbtnTenNhaNhanVien);
        buttonGroup.add(rbtnDiaChiNhaNhanVien);
        buttonGroup.add(rbtnDienThoai);

        JTextField txtTimKiem = new JTextField(20);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tìm kiếm");

        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(rbtnDiaChiNhaNhanVien);
        pnTimKiem.add(rbtnMaNhaNhanVien);
        pnTimKiem.add(rbtnTenNhaNhanVien);
        pnTimKiem.add(rbtnDienThoai);

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


        this.add(pnEast, BorderLayout.EAST);
        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnCenterMain, BorderLayout.CENTER);


    }

    private CustomerModel getCustomer() {
        CustomerModel customer = new CustomerModel();
        return customer;
    }

    private void addEvents() {
        btnThemMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAndChangeCustomerDialogAdd(MainUI.frame, "Thêm khách hàng", database);
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsKhachHang.getSelectedRow();
                if(rowSelected!=-1){
                    CustomerModel customerModel = new CustomerModel();
                    customerModel.setMaKhachHang((String) tbDsKhachHang.getValueAt(rowSelected, 0));
                    customerModel.setTenKhachHang((String) tbDsKhachHang.getValueAt(rowSelected, 1));
                    customerModel.setDiaChi((String) tbDsKhachHang.getValueAt(rowSelected, 2));
                    customerModel.setSoDienThoai((String) tbDsKhachHang.getValueAt(rowSelected, 3));
                    new AddAndChangeCustomerDialogEdit(MainUI.frame, "Chỉnh sửa thông tin khách hàng", customerModel, database);
                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn đối tượng cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tbDsKhachHang.getSelectedRow();
                if(rowSelected!=-1){
                    Connection conn = DatabaseConnection.getConnection(database);
                    try {
                        CallableStatement statement = conn.prepareCall("{ CALL sp_Customer_Delete(?) }");
                        statement.setString(1, (String) tbDsKhachHang.getValueAt(rowSelected, 0));
                        int result = statement.executeUpdate();
                        if(result!= 0){
                            showListCustomer(getAllCustomer());
                            JOptionPane.showMessageDialog(MainUI.frame, "Xoá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(MainUI.frame, "Bạn chưa chọn đối tượng cần xoá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private List<CustomerModel> getAllCustomer() throws SQLException {
        List<CustomerModel> listCustomer = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = ((Connection) conn).prepareCall("{ CALL sp_Customer_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                CustomerModel customerModel = new CustomerModel();
                customerModel.setMaKhachHang(rs.getString("Ma"));
                customerModel.setTenKhachHang(rs.getString("Ten"));
                customerModel.setDiaChi(rs.getString("DiaChi"));
                customerModel.setSoDienThoai(rs.getString("SDT"));
                listCustomer.add(customerModel);
            }
        }
        return listCustomer;
    }

    private void showListCustomer(List<CustomerModel> listCustomer) {
        dtmDsKhachHang.setRowCount(0);
        for (CustomerModel customerModel : listCustomer) {
            Vector<String> vector = new Vector<>();
            vector.add(customerModel.getMaKhachHang());
            vector.add(customerModel.getTenKhachHang());
            vector.add(customerModel.getDiaChi());
            vector.add(customerModel.getSoDienThoai());
            dtmDsKhachHang.addRow(vector);
        }
    }


    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        JFrame frame = new JFrame();
        frame.setTitle("Quản lý khách hàng");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Database database = new Database("sales_management", "3306", "root", "");
        frame.getContentPane().add(new CustomerPanel(database));

        frame.setVisible(true);
    }
}
