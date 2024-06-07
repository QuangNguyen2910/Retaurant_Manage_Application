package Entity;

public class BanDat29 {
    private Integer ID;
    private LanDatBan29 lanDatBan;

    // Constructor
    public BanDat29(Integer ID, LanDatBan29 lanDatBan) {
        this.ID = ID;
        this.lanDatBan = lanDatBan;
    }

    // Getter methods
    public Integer getID() {
        return ID;
    }

    public LanDatBan29 getLanDatBan() {
        return lanDatBan;
    }

    // Setter methods
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setLanDatBan(LanDatBan29 lanDatBan) {
        this.lanDatBan = lanDatBan;
    }
}
