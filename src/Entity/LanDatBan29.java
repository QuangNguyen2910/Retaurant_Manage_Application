package Entity;

import java.sql.Time;
import java.util.Date;

public class LanDatBan29 {
    private Integer ID;
    private KhachHang29 khachHang;

    private Date ngayDat;

    private Time gioDat;

    private Integer soNguoi;
    // Constructor
    public LanDatBan29(Integer ID, KhachHang29 khachHang, Date ngayDat, Time gioDat, Integer soNguoi) {
        this.ID = ID;
        this.khachHang = khachHang;
        this.ngayDat = ngayDat;
        this.gioDat = gioDat;
        this.soNguoi = soNguoi;
    }

    // Getter methods
    public Integer getID() {
        return ID;
    }

    public KhachHang29 getKhachHang() {
        return khachHang;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public Time getGioDat() {
        return gioDat;
    }

    public Integer getSoNguoi() {
        return soNguoi;
    }

    // Setter methods
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setKhachHang(KhachHang29 khachHang) {
        this.khachHang = khachHang;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public void setGioDat(Time gioDat) {
        this.gioDat = gioDat;
    }

    public void setSoNguoi(Integer soNguoi) {
        this.soNguoi = soNguoi;
    }
}
