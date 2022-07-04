package View;

//import Controller.CustomerController;

import Controller.DatabaseConnection;
import Model.CustomerModel;
import Model.Database;

import javax.swing.*;
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

public class AddAndChangeCustomerDialogAdd extends JDialog {
    private JTextField txtMaKhachHang, txtTenKhachHang, txtDiaChi, txtSDT;
    private final Dimension dimenLabel = new Dimension(190, 25);
    private final Dimension dimenTextField=  new Dimension(220, 25);
    private final Color backGroundBlue= new Color(78 , 138 , 201);
    private JButton btnXacNhan, btnThoat;
    private final Database database;

    // constructor
    public AddAndChangeCustomerDialogAdd(Frame parent, String title, Database database){
        super(parent, title, true);
        this.database = database;
        initComponents();
        addEvents();
        showDialog(parent);

    }

    public void initComponents(){
        // The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        JLabel lblTieuDe= new JLabel("Thông tin khách hàng");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);

        //The bottom panel
        JPanel pnBottom = new JPanel();
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnXacNhan = new JButton("Lưu thay đổi");
        btnXacNhan.setPreferredSize(new Dimension(150, 30));
        btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(new Dimension(150, 30));
        pnBottom.add(btnXacNhan);
        pnBottom.add(btnThoat);


        // The Center panel
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout( new BoxLayout (pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setBackground(Color.WHITE);
        pnCenter.setPreferredSize(new Dimension(400, 0));

        JPanel pnMaKhachHang= new JPanel();
        pnMaKhachHang.setBackground(Color.WHITE);
        txtMaKhachHang = new JTextField();
        txtMaKhachHang.setPreferredSize(dimenTextField);
        JLabel lblMaKhachHang = new JLabel("Mã khách hàng: ");
        lblMaKhachHang.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaKhachHang.setPreferredSize(dimenLabel);
        pnMaKhachHang.add(lblMaKhachHang);
        pnMaKhachHang.add(txtMaKhachHang);

        JPanel pnTenKhachHang= new JPanel();
        pnTenKhachHang.setBackground(Color.WHITE);
        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setPreferredSize(dimenTextField);
        JLabel lblTenKH = new JLabel("Tên khách hàng: ");
        lblTenKH.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenKH.setPreferredSize(dimenLabel);
        pnTenKhachHang.add(lblTenKH);
        pnTenKhachHang.add(txtTenKhachHang);

        JPanel pnDiaChi= new JPanel();
        pnDiaChi.setBackground(Color.WHITE);
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(dimenTextField);
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblDiaChi.setPreferredSize(dimenLabel);
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnSDT= new JPanel();
        pnSDT.setBackground(Color.WHITE);
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(dimenTextField);
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblSDT.setPreferredSize(dimenLabel);
        pnSDT.add(lblSDT);
        pnSDT.add(txtSDT);

        pnCenter.add(Box.createVerticalGlue());
        pnCenter.add(pnMaKhachHang);
        pnCenter.add(pnTenKhachHang);
        pnCenter.add(pnDiaChi);
        pnCenter.add(pnSDT);
        pnCenter.add(pnBottom);
        pnCenter.add(Box.createVerticalGlue());

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);


        Container con = this.getContentPane();
        con.add(pnMain);

    }

    private void addEvents(){
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
                if (conn!= null){
                    try {
                        CallableStatement statement = conn.prepareCall("{ CALL sp_Customer_Check(?)}");
                        statement.setString(1, txtMaKhachHang.getText());
                        ResultSet resultSet = statement.executeQuery();
                        if(resultSet.next()){
                            int select = JOptionPane.showConfirmDialog(MainUI.frame, "Mã khách hàng đã tồn tại \n Bạn có muốn tiếp tục?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
                            if(select== JOptionPane.OK_OPTION){
                                statement = conn.prepareCall("{CALL sp_Customer_Update(?,?,?,?)}");
                                statement.setString(1, txtMaKhachHang.getText());
                                statement.setString(2, txtTenKhachHang.getText());
                                statement.setString(3, txtDiaChi.getText());
                                statement.setString(4, txtSDT.getText());
                                int result = statement.executeUpdate();
                                if(result!= 0 ){
                                    showListCustomer(getAllCustomer());
                                    dispose();
                                    JOptionPane.showMessageDialog(MainUI.frame, "Cập nhật thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            statement = conn.prepareCall("{CALL sp_Customer_Add(?,?,?,?)}");
                            statement.setString(1, txtMaKhachHang.getText());
                            statement.setString(2, txtTenKhachHang.getText());
                            statement.setString(3, txtDiaChi.getText());
                            statement.setString(4, txtSDT.getText());
                            int result = statement.executeUpdate();
                            if(result!= 0 ){
                                showListCustomer(getAllCustomer());
                                dispose();
                                JOptionPane.showMessageDialog(MainUI.frame, "Thêm thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private List<CustomerModel> getAllCustomer() throws SQLException {
        List<CustomerModel> listCustomer = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Customer_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
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
        CustomerPanel.dtmDsKhachHang.setRowCount(0);
        for (CustomerModel customerModel : listCustomer) {
            Vector<String> vector = new Vector<>();
            vector.add(customerModel.getMaKhachHang());
            vector.add(customerModel.getTenKhachHang());
            vector.add(customerModel.getDiaChi());
            vector.add(customerModel.getSoDienThoai());
            CustomerPanel.dtmDsKhachHang.addRow(vector);
        }
    }


    private void showDialog(Frame parent){
        this.setSize(600,500);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);
    }


}
