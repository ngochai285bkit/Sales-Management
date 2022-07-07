package View;

import Controller.DatabaseConnection;
import Model.Database;
import Model.EmployeeModel;
import org.jdatepicker.JDatePanel;
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
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class AddAndChangeEmployeeDialogEdit extends JDialog {
    private JTextField txtMaNhanVien, txtDiaChi, txtChucVu, txtTenNhanVien, txtSDT;
    private JDatePicker txtNgaySinh, txtNgayBatDauLam;
    private final Dimension dimenLabel = new Dimension(150, 25);
    private final Dimension dimenTextField=  new Dimension(220, 25);
    private final Color backGroundBlue= new Color(78 , 138 , 201);
    private JButton btnXacNhan, btnThoat;
    private final Database database;
    private JComboBox chonGioiTinh;
    private EmployeeModel employee;

    public AddAndChangeEmployeeDialogEdit(Frame parent, String title, EmployeeModel employee, Database database){
        super(parent, title , true);
        this.database= database;
        this.employee= employee;
        initComponents();
        addEvents();
        showDialog(parent);
    }

    private void initComponents(){
        //The top panel
        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        JLabel lblTieuDe = new JLabel("Thông tin nhân viên");
        lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD,26));
        lblTieuDe.setForeground(backGroundBlue);
        pnTop.add(lblTieuDe);

        //The bottom panel
        JPanel pnBottom = new JPanel();
        pnBottom.setBorder(BorderFactory.createEmptyBorder(0 ,0, 20,0));
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnXacNhan = new JButton("Lưu thay đổi");
        btnXacNhan.setPreferredSize(new Dimension(150, 30));
        btnXacNhan.setBackground(backGroundBlue);
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(new Dimension(150, 30));
        btnThoat.setBackground(backGroundBlue);
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        pnBottom.add(btnXacNhan);
        pnBottom.add(btnThoat);


        //The center panel
        JPanel pnEast = new JPanel();
        pnEast.setLayout( new BoxLayout (pnEast, BoxLayout.Y_AXIS));
        pnEast.setBackground(Color.WHITE);
        pnEast.setPreferredSize(new Dimension(400, 0));

        JPanel pnMaNhanVien= new JPanel();
        pnMaNhanVien.setBackground(Color.WHITE);
        txtMaNhanVien = new JTextField();
        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setPreferredSize(dimenTextField);
        JLabel lblMaNhanVien = new JLabel("Mã nhân viên: ");
        lblMaNhanVien.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblMaNhanVien.setPreferredSize(dimenLabel);
        pnMaNhanVien.add(lblMaNhanVien);
        pnMaNhanVien.add(txtMaNhanVien);

        JPanel pnTenNhanVien= new JPanel();
        pnTenNhanVien.setBackground(Color.WHITE);
        txtTenNhanVien = new JTextField();
        txtTenNhanVien.setPreferredSize(dimenTextField);
        JLabel lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblTenNhanVien.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblTenNhanVien.setPreferredSize(dimenLabel);
        pnTenNhanVien.add(lblTenNhanVien);
        pnTenNhanVien.add(txtTenNhanVien);

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

        JPanel pnChucVu= new JPanel();
        pnChucVu.setBackground(Color.WHITE);
        txtChucVu = new JTextField();
        txtChucVu.setPreferredSize(dimenTextField);
        JLabel lblChucVu = new JLabel("Chức vụ: ");
        lblChucVu.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblChucVu.setPreferredSize(dimenLabel);
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtChucVu);

        JPanel pnNgaySinh= new JPanel();
        pnNgaySinh.setBackground(Color.WHITE);
        txtNgaySinh = new JDatePicker(employee.getNgaySinhNhanVien());
        txtNgaySinh.setPreferredSize(dimenTextField);
        JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblNgaySinh.setPreferredSize(dimenLabel);
        pnNgaySinh.add(lblNgaySinh);
        pnNgaySinh.add(txtNgaySinh);

        JPanel pnNgayBatDauLam = new JPanel();
        pnNgayBatDauLam.setBackground(Color.WHITE);
        txtNgayBatDauLam = new JDatePicker(employee.getNgayBatDauLam());
        txtNgayBatDauLam.setPreferredSize(dimenTextField);
        JLabel lblNgayBatDauLam = new JLabel("Ngày làm việc: ");
        lblNgayBatDauLam.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblNgayBatDauLam.setPreferredSize(dimenLabel);
        pnNgayBatDauLam.add(lblNgayBatDauLam);
        pnNgayBatDauLam.add(txtNgayBatDauLam);

        JPanel pnGioiTinh= new JPanel();
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblGioiTinh.setPreferredSize(dimenLabel);
        pnGioiTinh.setBackground(Color.WHITE);
        String gioiTinh[] = {"Nam", "Nữ"};
        chonGioiTinh =  new JComboBox(gioiTinh);
        chonGioiTinh.setPreferredSize(dimenTextField);
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(chonGioiTinh);

        txtMaNhanVien.setText(employee.getMaNhanVien());
        txtTenNhanVien.setText(employee.getHoTenNhanVien());
        txtDiaChi.setText(employee.getDiaChiNhanVien());
        txtSDT.setText(employee.getSdtNhanVien());
        txtChucVu.setText(employee.getChucVuNhanVien());
        if(employee.getGioiTinh().equals("Nam")){
            chonGioiTinh.setSelectedIndex(0);
        } else {
            chonGioiTinh.setSelectedIndex(1);
        }

        pnEast.add(Box.createVerticalGlue());
        pnEast.add(pnMaNhanVien);
        pnEast.add(pnTenNhanVien);
        pnEast.add(pnDiaChi);
        pnEast.add(pnSDT);
        pnEast.add(pnChucVu);
        pnEast.add(pnNgaySinh);
        pnEast.add(pnNgayBatDauLam);
        pnEast.add(pnGioiTinh);
        pnEast.add(Box.createVerticalGlue());

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnEast, BorderLayout.CENTER);
        pnMain.add(pnBottom, BorderLayout.SOUTH);

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
                if (conn != null) {
                    try {
                        CallableStatement statement = conn.prepareCall("{CALL sp_Employee_Update(?,?,?,?,?,?,?,?)}");
                        statement.setString(1, txtMaNhanVien.getText());
                        statement.setString(2, txtTenNhanVien.getText());
                        statement.setString(3, txtDiaChi.getText());
                        statement.setString(4, txtSDT.getText());
                        statement.setString(5, txtChucVu.getText());
                        statement.setString(6,
                                new SimpleDateFormat("dd/MM/yyyy").format(txtNgaySinh.getModel().getValue()));
                        statement.setString(7,
                                new SimpleDateFormat("dd/MM/yyyy").format(txtNgayBatDauLam.getModel().getValue()));
                        statement.setString(8, (String) chonGioiTinh.getSelectedItem());

                        int result = statement.executeUpdate();
                        if (result != 0) {
                            showListEmployee(getAllEmployee());
                            dispose();
                            JOptionPane.showMessageDialog(MainUI.frame, "Chỉnh sửa thành công", "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);

                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private List<EmployeeModel> getAllEmployee() {
        List<EmployeeModel> listEmployee = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            try{
                CallableStatement statement = ((Connection) conn).prepareCall("{ CALL sp_Employee_GetAll() }");
                ResultSet rs = statement.executeQuery();
                while (rs != null && rs.next()) {
                    EmployeeModel employeeModel = new EmployeeModel();
                    employeeModel.setMaNhanVien(rs.getString("MaNV"));
                    employeeModel.setHoTenNhanVien(rs.getString("HoTenNV"));
                    employeeModel.setDiaChiNhanVien(rs.getString("DiaChiNV"));
                    employeeModel.setSdtNhanVien(rs.getString("SdtNV"));
                    employeeModel.setChucVuNhanVien(rs.getString("ChucVu"));
                    try {
                        employeeModel.setNgaySinhNhanVien(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("NgaySinh")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        employeeModel.setNgayBatDauLam(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("NgayLamViec")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        employeeModel.setGioiTinh(rs.getString("GioiTinh"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    listEmployee.add(employeeModel);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listEmployee;
    }

    private void showListEmployee(java.util.List<EmployeeModel> listEmployee) {
        EmployeePanel.dtmDsNhanVien.setRowCount(0);
        for (EmployeeModel employeeModel : listEmployee) {
            Vector<String> vector = new Vector<>();
            vector.add(employeeModel.getMaNhanVien());
            vector.add(employeeModel.getHoTenNhanVien());
            vector.add(employeeModel.getDiaChiNhanVien());
            vector.add(employeeModel.getSdtNhanVien());
            vector.add(employeeModel.getChucVuNhanVien());
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(employeeModel.getNgaySinhNhanVien()));
            vector.add(new SimpleDateFormat("dd/MM/yyyy").format(employeeModel.getNgayBatDauLam()));
            vector.add(employeeModel.getGioiTinh());
            EmployeePanel.dtmDsNhanVien.addRow(vector);
        }
    }


    private void showDialog(Frame parent){
        this.setSize(1000,600);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);

    }
}
