package View;

import Model.Database;
import Model.EmployeeModel;

import javax.swing.*;
import java.awt.*;

public class AddAndChangeEmployeeDialog extends JDialog {
    private JTextField txtMaNhanVien, txtDiaChi, txtChucVu, txtTenNhanVien, txtSDT, txtNgaySinh, txtNgayBatDauLam;
    private final Dimension dimenLabel = new Dimension(150, 25);
    private final Dimension dimenTextField=  new Dimension(220, 25);
    private final Color backGroundBlue= new Color(78 , 138 , 201);
    private JButton btnXacNhan, btnThoat;
    private Database database;

    // constructor
    public AddAndChangeEmployeeDialog(Frame parent, String title, Database database){
        super(parent, title, true);
        this.database=database;
        initComponents();
        showDialog(parent);
    }

    public AddAndChangeEmployeeDialog(Frame parent, String title, EmployeeModel employee, Database database){
        super(parent, title , true);
        this.database= database;
        initComponents();
        setInforEmployee(employee);
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
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnXacNhan = new JButton("Lưu thay đổi");
        btnXacNhan.setPreferredSize(new Dimension(150, 30));
        btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(new Dimension(150, 30));
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
        txtNgaySinh = new JTextField();
        txtNgaySinh.setPreferredSize(dimenTextField);
        JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblNgaySinh.setPreferredSize(dimenLabel);
        pnNgaySinh.add(lblNgaySinh);
        pnNgaySinh.add(txtNgaySinh);

        JPanel pnNgayBatDauLam = new JPanel();
        pnNgayBatDauLam.setBackground(Color.WHITE);
        txtNgayBatDauLam = new JTextField();
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
        JComboBox chonGioiTinh =  new JComboBox(gioiTinh);
        chonGioiTinh.setPreferredSize(dimenTextField);
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(chonGioiTinh);


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

    private void setInforEmployee(EmployeeModel employee){
        txtMaNhanVien.setText(employee.getMaNhanVien());
    }

    private void showDialog(Frame parent){
        this.setSize(1000,600);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);

    }
}
