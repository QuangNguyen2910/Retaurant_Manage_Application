package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ThongKeKhachHangFrm29 extends JFrame {

    private JPanel panel;
    private JButton nutThongKeKhachHangTheoNgay;

    public ThongKeKhachHangFrm29() {
        super("Thong Ke Khach Hang");
        nutThongKeKhachHangTheoNgay.setText("Thong Ke Khach Hang Theo Ngay");

        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        nutThongKeKhachHangTheoNgay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeLuongKhachTheoNgayFrm29 ThongKeListFrm = null;
                ThongKeListFrm = new ThongKeLuongKhachTheoNgayFrm29();
                ThongKeListFrm.setVisible(true);
            }
        });
    }
}
