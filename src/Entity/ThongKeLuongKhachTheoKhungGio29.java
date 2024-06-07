package Entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeLuongKhachTheoKhungGio29 {
    private Date ngay;
    private Time gioBatDau;
    private Time gioKetThuc;
    private Float trungBinhSoKhach;
    private Float trungBinhDoanhThuDauKhach;
    private Integer tongDoanhThuKhungGio;

    // Constructor
    public ThongKeLuongKhachTheoKhungGio29(Date ngay, Time gioBatDau, Time gioKetThuc, Float trungBinhSoKhach, Float trungBinhDoanhThuDauKhach, Integer tongDoanhThuKhungGio) {
        this.ngay = ngay;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.trungBinhSoKhach = trungBinhSoKhach;
        this.trungBinhDoanhThuDauKhach = trungBinhDoanhThuDauKhach;
        this.tongDoanhThuKhungGio = tongDoanhThuKhungGio;
    }

    // Method to calculate average number of customers
    public Integer calculateAverageNumberCustomers(List<HoaDon29> selectedHoaDonList) {
        Integer totalSoKhach = 0;
        for (HoaDon29 hoaDon : selectedHoaDonList) {
            totalSoKhach += hoaDon.getBanDat().getLanDatBan().getSoNguoi();
        }
        return totalSoKhach / selectedHoaDonList.size();
    }

    // Method to calculate average revenue per customer
    public Float calculateAverageRevenuePerCustomer(List<HoaDon29> selectedHoaDonList) {
        Float totalDoanhThu = 0.0f;
        Integer totalSoKhach = 0;
        for (HoaDon29 hoaDon : selectedHoaDonList) {
            totalDoanhThu += hoaDon.getTongTienThanhToan();
            totalSoKhach += hoaDon.getBanDat().getLanDatBan().getSoNguoi();
        }
        return totalDoanhThu / totalSoKhach;
    }

    // Method to calculate total revenue for the time frame
    public Float calculateTotalRevenue(List<HoaDon29> selectedHoaDonList) {
        Float totalDoanhThu = 0.0f;
        for (HoaDon29 hoaDon : selectedHoaDonList) {
            totalDoanhThu += hoaDon.getTongTienThanhToan();
        }
        return totalDoanhThu;
    }

    // Getter and setter methods


    public Date getNgay() {
        return ngay;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public Float getTrungBinhSoKhach() {
        return trungBinhSoKhach;
    }

    public Float getTrungBinhDoanhThuDauKhach() {
        return trungBinhDoanhThuDauKhach;
    }

    public Integer getTongDoanhThuKhungGio() {
        return tongDoanhThuKhungGio;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }
}
