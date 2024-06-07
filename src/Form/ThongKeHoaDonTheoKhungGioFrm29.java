package Form;

import DAO.ThongKeKhachHangDAO29;
import Entity.HoaDon29;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class ThongKeHoaDonTheoKhungGioFrm29 extends JFrame {
    private JPanel panel;
    private JTable tblThongKeHoaDonTheoKhungGioFrm29;
    private JButton nutQuayLaiTrangChu;
    private JButton nutQuayLaiDanhSach;

    public ThongKeHoaDonTheoKhungGioFrm29(Date ngay, Time gioBatDau, Time gioKetThuc) throws SQLException {
        super("Thong Ke Hoa Don Theo Khung Gio");

        nutQuayLaiTrangChu = new JButton("Quay Lai Trang Chu");
        nutQuayLaiDanhSach = new JButton("Quay Lai Danh Sach");
        tblThongKeHoaDonTheoKhungGioFrm29 = new JTable();

        tblThongKeHoaDonTheoKhungGioFrm29.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 70));
        tblThongKeHoaDonTheoKhungGioFrm29.setFillsViewportHeight(true);
        tblThongKeHoaDonTheoKhungGioFrm29.setRowHeight(20);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nutQuayLaiTrangChu);
        panel.add(nutQuayLaiDanhSach);
        panel.add(tblThongKeHoaDonTheoKhungGioFrm29.getTableHeader());
        panel.add(tblThongKeHoaDonTheoKhungGioFrm29);

        // Set the panel as the content pane
        this.setContentPane(panel);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        fetchAndDisplayData(ngay, gioBatDau, gioKetThuc);

        nutQuayLaiDanhSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        nutQuayLaiTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to ThongKeKhachHangFrm29 Window
            }
        });
    }

    private void fetchAndDisplayData(Date ngay, Time gioBatDau, Time gioKetThuc) throws SQLException {
        ThongKeKhachHangDAO29 dao = new ThongKeKhachHangDAO29();
        List<HoaDon29> hoaDonList = dao.getThongKeHoaDonTheoKhungGio(ngay, gioBatDau, gioKetThuc);

        updateTable(hoaDonList);
    }

    private void updateTable(List<HoaDon29> hoaDonList) {
        String[] columnNames = {"Ma Hoa Don", "Ten Khach", "Ngay", "Tong So Mon Goi", "Tong Tien Thanh Toan"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (HoaDon29 hoaDon : hoaDonList) {
            Object[] row = {
                    hoaDon.getID(),
                    hoaDon.getKhachHang().getTen(),
                    hoaDon.getBanDat().getLanDatBan().getNgayDat(),
                    hoaDon.getTongSoMonGoi(),
                    hoaDon.getTongTienThanhToan(),
            };
            model.addRow(row);
        }

        tblThongKeHoaDonTheoKhungGioFrm29.setModel(model);
    }
}
