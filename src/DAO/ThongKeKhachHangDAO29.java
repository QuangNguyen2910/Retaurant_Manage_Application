package DAO;

import Entity.*;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThongKeKhachHangDAO29 {
    private Connection dbCon = null;

    public ThongKeKhachHangDAO29() {
        String dbUrl = "jdbc:mysql://localhost:3306/cnpm";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbCon = DriverManager.getConnection(dbUrl, "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ThongKeLuongKhachTheoKhungGio29> getThongKeKhachTheoGio(Date ngayBatDau, Date ngayKetThuc) {
        String sql = "(SELECT tbllandatban29.ngayDat, tbllandatban29.gioDat, tbllandatban29.soNguoi, tblcombokhachgoi29.soLuong, tblcombokhachgoi29.donGia " +
                "FROM tblhoadon29 JOIN tblbandat29 ON tblbandat29.ID = tblhoadon29.bandatID " +
                "JOIN tbllandatban29 ON tblbandat29.landatbanID = tbllandatban29.ID " +
                "JOIN tblcombokhachgoi29 ON tblbandat29.ID = tblcombokhachgoi29.bandatID " +
                "WHERE ngayDat >= ? AND ngayDat <= ? " +
                "UNION " +
                "SELECT tbllandatban29.ngayDat, tbllandatban29.gioDat, tbllandatban29.soNguoi, tblmonkhachgoi29.soLuong, tblmonkhachgoi29.donGia " +
                "FROM tblhoadon29 JOIN tblbandat29 ON tblbandat29.ID = tblhoadon29.bandatID " +
                "JOIN tbllandatban29 ON tblbandat29.landatbanID = tbllandatban29.ID " +
                "JOIN tblmonkhachgoi29 ON tblbandat29.ID = tblmonkhachgoi29.bandatID " +
                "WHERE ngayDat >= ? AND ngayDat <= ?) " +
                "ORDER BY ngayDat, gioDat;";

        try (PreparedStatement preparedStatement = dbCon.prepareStatement(sql)) {
            preparedStatement.setDate(1, ngayBatDau);
            preparedStatement.setDate(2, ngayKetThuc);
            preparedStatement.setDate(3, ngayBatDau);
            preparedStatement.setDate(4, ngayKetThuc);

            ResultSet rs = preparedStatement.executeQuery();

            Map<String, Map<String, List<Float>>> result = new HashMap<>();

            while (rs.next()) {
                String ngay = rs.getDate(1).toString();
                Time gioDat = rs.getTime(2);
                int soNguoi = rs.getInt(3);
                int soLuong = rs.getInt(4);
                float donGia = rs.getFloat(5);
                float doanhThu = soLuong * donGia;

                String timeSlot = getTimeSlot(gioDat);

                // Initialize time slots for each date if not already present
                result.putIfAbsent(ngay, new HashMap<>());
                result.get(ngay).putIfAbsent("time1", new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f, 0.0f))); // [tongLuongKhach, tongDoanhThu, Dem, trungBinhSoKhach, trungBinhDoanhThuDauKhach]
                result.get(ngay).putIfAbsent("time2", new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f, 0.0f)));
                result.get(ngay).putIfAbsent("time3", new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f, 0.0f)));

                List<Float> stats = result.get(ngay).get(timeSlot);

                // Update statistics
                stats.set(0, stats.get(0) + soNguoi); // tongLuongKhach
                stats.set(1, stats.get(1) + doanhThu); // tongDoanhThu
                stats.set(2, stats.get(2) + 1); // Dem
            }

            List<ThongKeLuongKhachTheoKhungGio29> thongKeList = new ArrayList<>();
            // Calculate averages
            for (String ngay : result.keySet()) {
                for (String timeSlot : result.get(ngay).keySet()) {
                    List<Float> stats = result.get(ngay).get(timeSlot);
                    float tongLuongKhach = stats.get(0);
                    float tongDoanhThuKhungGio = stats.get(1);
                    float dem = stats.get(2);

                    float trungBinhSoKhach = 0;
                    float trungBinhDoanhThuDauKhach = 0;
                    if (dem > 0) {
                        trungBinhSoKhach = tongLuongKhach / dem; // trungBinhSoKhach
                        trungBinhDoanhThuDauKhach = tongDoanhThuKhungGio / tongLuongKhach; // trungBinhDoanhThuDauKhach
                    }

                    Time gioBatDau = null;
                    Time gioKetThuc = null;
                    java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                    Date sqlDate = new Date(utilDate.getTime());

                    if (timeSlot.equals("time1")) {
                        gioBatDau = Time.valueOf("08:00:00");
                        gioKetThuc = Time.valueOf("11:59:00");
                    } else if (timeSlot.equals("time2")) {
                        gioBatDau = Time.valueOf("12:00:00");
                        gioKetThuc = Time.valueOf("18:59:00");
                    } else {
                        gioBatDau = Time.valueOf("19:00:00");
                        gioKetThuc = Time.valueOf("21:59:00");
                    }

                    ThongKeLuongKhachTheoKhungGio29 tmp = new ThongKeLuongKhachTheoKhungGio29(sqlDate, gioBatDau, gioKetThuc, trungBinhSoKhach, trungBinhDoanhThuDauKhach, (int) tongDoanhThuKhungGio);
                    thongKeList.add(tmp);
                }
            }

            return thongKeList;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon29> getThongKeHoaDonTheoKhungGio(Date ngay, Time gioBatDau, Time gioKetThuc) throws SQLException {
        String sql = "(SELECT tblkhachhang29.*, tbllandatban29.*, tblbandat29.*, NULL AS IdMon, NULL AS soMon, NULL as giaMon, tblcombokhachgoi29.ID AS IdCombo, tblcombokhachgoi29.soLuong AS soCombo, tblcombokhachgoi29.donGia AS giaCombo, tblhoadon29.* " +
                "FROM tblhoadon29 JOIN tblbandat29 ON tblbandat29.ID = tblhoadon29.bandatID " +
                "JOIN tbllandatban29 ON tblbandat29.landatbanID = tbllandatban29.ID " +
                "JOIN tblcombokhachgoi29 ON tblbandat29.ID = tblcombokhachgoi29.bandatID " +
                "JOIN tblkhachhang29 ON tblkhachhang29.ID = tbllandatban29.khachhangID " +
                "WHERE ngayDat = ?  AND gioDat >=  ? AND gioDat <= ?" +
                "UNION " +
                "SELECT tblkhachhang29.*, tbllandatban29.*, tblbandat29.*, tblmonkhachgoi29.ID AS IdMon, tblmonkhachgoi29.soLuong AS soMon, tblmonkhachgoi29.donGia AS giaMon, NULL AS IdCombo, NULL AS soCombo, NULL AS giaCombo, tblhoadon29.* " +
                "FROM tblhoadon29 JOIN tblbandat29 ON tblbandat29.ID = tblhoadon29.bandatID " +
                "JOIN tbllandatban29 ON tblbandat29.landatbanID = tbllandatban29.ID " +
                "JOIN tblmonkhachgoi29 ON tblbandat29.ID = tblmonkhachgoi29.bandatID " +
                "JOIN tblkhachhang29 ON tblkhachhang29.ID = tbllandatban29.khachhangID " +
                "WHERE ngayDat = ?  AND gioDat >=  ? AND gioDat <= ?) " +
                "ORDER BY ngayDat, gioDat;";

        try (PreparedStatement preparedStatement = dbCon.prepareStatement(sql)) {
            preparedStatement.setDate(1, ngay);
            preparedStatement.setTime(2, gioBatDau);
            preparedStatement.setTime(3, gioKetThuc);
            preparedStatement.setDate(4, ngay);
            preparedStatement.setTime(5, gioBatDau);
            preparedStatement.setTime(6, gioKetThuc);

            ResultSet rs = preparedStatement.executeQuery();

            Map<Integer, HoaDon29> mapper = new HashMap<>();
            while (rs.next()) {
                KhachHang29 khachhang = new KhachHang29(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                LanDatBan29 landatban = new LanDatBan29(rs.getInt(6), khachhang, rs.getDate(7), rs.getTime(8), rs.getInt(10));
                BanDat29 bandat = new BanDat29(rs.getInt(11), landatban);
                int i = -1;
                ComboKhachGoi29 combogoi = null;
                MonKhachGoi29 mongoi = null;
                if (rs.getString(13) == null) {
                    combogoi = new ComboKhachGoi29(rs.getInt(16), bandat, rs.getInt(17), rs.getFloat(18));
                    i = 0;
                }
                else {
                    mongoi = new MonKhachGoi29(rs.getInt(13), bandat, rs.getInt(14), rs.getFloat(15));
                    i = 1;
                }
                Integer hoaDonID = rs.getInt(19);
                if (mapper.containsKey(hoaDonID)) {
                    HoaDon29 hoadon = mapper.get(hoaDonID);
                    if (i == 0) {
                        List<ComboKhachGoi29> tmp = hoadon.getCacComboGoi();
                        tmp.add(combogoi);
                        hoadon.setCacComboGoi(tmp);
                    } else {
                        List<MonKhachGoi29> tmp = hoadon.getCacMonGoi();
                        tmp.add(mongoi);
                        hoadon.setCacMonGoi(tmp);
                    }
                    mapper.put(hoaDonID, hoadon);
                } else {
                    List<MonKhachGoi29> dsmongoi = new ArrayList<>();
                    List<ComboKhachGoi29> dscombogoi = new ArrayList<>();
                    dsmongoi.add(mongoi);
                    dscombogoi.add(combogoi);
                    HoaDon29 hoadon = new HoaDon29(rs.getInt(19), khachhang, bandat, dsmongoi, dscombogoi);
                    mapper.put(hoaDonID, hoadon);
                }
            }

            List<HoaDon29> result = new ArrayList<>();
            for(Integer x : mapper.keySet()) {
                result.add(mapper.get(x));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String getTimeSlot(Time time) {
        int hour = time.toLocalTime().getHour();

        if (hour >= 8 && hour <= 11) {
            return "time1";
        } else if (hour >= 12 && hour <= 18) {
            return "time2";
        } else {
            return "time3";
        }
    }

    public static void main(String[] args) {
        ThongKeKhachHangDAO29 dao = new ThongKeKhachHangDAO29();
        try {
            Date ngay = Date.valueOf("2024-05-11");
            Time gioBatDau = Time.valueOf("8:00:00");
            Time gioKetThuc = Time.valueOf("11:59:00");
            List<HoaDon29> result = dao.getThongKeHoaDonTheoKhungGio(ngay, gioBatDau, gioKetThuc);
            for (HoaDon29 thongKe : result) {
                System.out.println(thongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

