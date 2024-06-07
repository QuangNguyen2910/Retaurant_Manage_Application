package Entity;

public class ComboKhachGoi29 {
    private Integer ID;
    private BanDat29 banDat;
    private Integer soLuong;
    private Float donGia;
    private Float thanhTien; // thanhTien = soLuong * donGia

    // Constructor
    public ComboKhachGoi29(Integer ID, BanDat29 banDat, Integer soLuong, Float donGia) {
        this.ID = ID;
        this.banDat = banDat;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    // Getter methods
    public Integer getID() {
        return ID;
    }

    public BanDat29 getBanDat() {
        return banDat;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public Float getDonGia() {
        return donGia;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    // Setter methods
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setBanDat(BanDat29 banDat) {
        this.banDat = banDat;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = soLuong * donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }
}
