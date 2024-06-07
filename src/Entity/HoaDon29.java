package Entity;

import java.util.List;

public class HoaDon29 {
    private Integer ID;
    private KhachHang29 khachHang;
    private BanDat29 banDat;
    private List<MonKhachGoi29> cacMonGoi;
    private List<ComboKhachGoi29> cacComboGoi;
    private Integer tongSoMonGoi;
    private Float tongTienThanhToan;

    // Constructor
    public HoaDon29(Integer ID, KhachHang29 khachHang, BanDat29 banDat, List<MonKhachGoi29> cacMonGoi, List<ComboKhachGoi29> cacComboGoi) {
        this.ID = ID;
        this.khachHang = khachHang;
        this.banDat = banDat;
        this.cacMonGoi = cacMonGoi;
        this.cacComboGoi = cacComboGoi;
        this.tongSoMonGoi = calculateTongSoMonGoi();
        this.tongTienThanhToan = calculateTongTienThanhToan();
    }

    // Getter methods
    public Integer getID() {
        return ID;
    }

    public KhachHang29 getKhachHang() {
        return khachHang;
    }

    public BanDat29 getBanDat() {
        return banDat;
    }

    public List<MonKhachGoi29> getCacMonGoi() {
        return cacMonGoi;
    }

    public List<ComboKhachGoi29> getCacComboGoi() {
        return cacComboGoi;
    }

    public Integer getTongSoMonGoi() {
        return tongSoMonGoi;
    }

    public Float getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    // Setter methods
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setKhachHang(KhachHang29 khachHang) {
        this.khachHang = khachHang;
    }

    public void setBanDat(BanDat29 banDat) {
        this.banDat = banDat;
    }

    public void setCacMonGoi(List<MonKhachGoi29> cacMonGoi) {
        this.cacMonGoi = cacMonGoi;
        this.tongSoMonGoi = calculateTongSoMonGoi();
        this.tongTienThanhToan = calculateTongTienThanhToan();
    }

    public void setCacComboGoi(List<ComboKhachGoi29> cacComboGoi) {
        this.cacComboGoi = cacComboGoi;
        this.tongSoMonGoi = calculateTongSoMonGoi();
        this.tongTienThanhToan = calculateTongTienThanhToan();
    }

    // Helper methods
    private Integer calculateTongSoMonGoi() {
        Integer total = 0;
        for (MonKhachGoi29 monGoi : cacMonGoi) {
            if (monGoi != null) total += monGoi.getSoLuong();
        }
        for (ComboKhachGoi29 comboGoi : cacComboGoi) {
            if (comboGoi != null) total += comboGoi.getSoLuong();
        }
        return total;
    }

    private Float calculateTongTienThanhToan() {
        Float total = 0.0f;
        for (MonKhachGoi29 monGoi : cacMonGoi) {
            if (monGoi != null) total += monGoi.getThanhTien();
        }
        for (ComboKhachGoi29 comboGoi : cacComboGoi) {
            if (comboGoi != null) total += comboGoi.getThanhTien();
        }

        return total;
    }
}

