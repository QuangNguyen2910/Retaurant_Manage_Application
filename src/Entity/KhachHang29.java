package Entity;

public class KhachHang29 {
    private Integer ID;
    private String ten;
    private String sdt;
    private String email;
    private String diaChi;

    // Constructor
    public KhachHang29(Integer ID, String ten, String sdt, String email, String diaChi) {
        this.ID = ID;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    // Getter methods
    public Integer getID() {
        return ID;
    }

    public String getTen() {
        return ten;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    // Setter methods
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
