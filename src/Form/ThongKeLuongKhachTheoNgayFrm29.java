package Form;

import DAO.ThongKeKhachHangDAO29;
import Entity.ThongKeLuongKhachTheoKhungGio29;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ThongKeLuongKhachTheoNgayFrm29 extends JFrame {
    private JTextField ngayBatDauField;
    private JTextField ngayKetThucField;
    private JButton nutThongKe;
    private JButton nutQuayLaiTrangChu;
    private JTable tblThongKeLuongKhachTheoNgayFrm29;
    private JPanel panel;

    public ThongKeLuongKhachTheoNgayFrm29() {
        super("Thong Ke Luong Khach Theo Ngay");

        // Initialize components
        ngayBatDauField = new JTextField();
        ngayKetThucField = new JTextField();
        nutThongKe = new JButton("Thong Ke");
        nutQuayLaiTrangChu = new JButton("Quay Lai Trang Chu");
        tblThongKeLuongKhachTheoNgayFrm29 = new JTable();
        panel = new JPanel();

        tblThongKeLuongKhachTheoNgayFrm29.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 70));
        tblThongKeLuongKhachTheoNgayFrm29.setFillsViewportHeight(true);

        tblThongKeLuongKhachTheoNgayFrm29.setRowHeight(20);

        ngayBatDauField.setMaximumSize(new java.awt.Dimension(500, 20));

        ngayKetThucField.setMaximumSize(new java.awt.Dimension(500, 20));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Ngay Bat Dau:"));
        panel.add(ngayBatDauField);
        panel.add(new JLabel("Ngay Ket Thuc:"));
        panel.add(ngayKetThucField);
        panel.add(nutThongKe);
        panel.add(nutQuayLaiTrangChu);
        panel.add(tblThongKeLuongKhachTheoNgayFrm29.getTableHeader());
        panel.add(tblThongKeLuongKhachTheoNgayFrm29);

        this.setContentPane(panel);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        nutThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date ngayBatDau = new Date(sdf.parse(ngayBatDauField.getText()).getTime());
                    Date ngayKetThuc = new Date(sdf.parse(ngayKetThucField.getText()).getTime());

                    ThongKeKhachHangDAO29 dao = new ThongKeKhachHangDAO29();
                    List<ThongKeLuongKhachTheoKhungGio29> result = dao.getThongKeKhachTheoGio(ngayBatDau, ngayKetThuc);

                    updateTable(result);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        nutQuayLaiTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        tblThongKeLuongKhachTheoNgayFrm29.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblThongKeLuongKhachTheoNgayFrm29.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) tblThongKeLuongKhachTheoNgayFrm29.getModel();
                    Date ngay = (Date) model.getValueAt(selectedRow, 0);
                    Time gioBatDau = (Time) model.getValueAt(selectedRow, 1);
                    Time gioKetThuc = (Time) model.getValueAt(selectedRow, 2);

                    try {
                        new ThongKeHoaDonTheoKhungGioFrm29(ngay, gioBatDau, gioKetThuc).setVisible(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void updateTable(List<ThongKeLuongKhachTheoKhungGio29> thongKeList) {
        String[] columnNames = {"Ngay", "Gio Bat Dau", "Gio Ket Thuc", "Trung Binh So Khach", "Trung Binh Doanh Thu Dau Khach", "Tong Doanh Thu Khung Gio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (ThongKeLuongKhachTheoKhungGio29 thongKe : thongKeList) {
            Object[] row = {
                    thongKe.getNgay(),
                    thongKe.getGioBatDau(),
                    thongKe.getGioKetThuc(),
                    thongKe.getTrungBinhSoKhach(),
                    thongKe.getTrungBinhDoanhThuDauKhach(),
                    thongKe.getTongDoanhThuKhungGio()
            };
            model.addRow(row);
        }

        tblThongKeLuongKhachTheoNgayFrm29.setModel(model);
    }
}
