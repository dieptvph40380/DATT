package com.example.datt.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SoccerTime {
    String tenca;
    String giaca;
    String soluong;
    String thoigianca;

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> SoccerTime = new HashMap<>();
        SoccerTime.put("tenca", tenca);
        SoccerTime.put("giaca", giaca);
        SoccerTime.put("soluong", soluong);
        SoccerTime.put("thoigianca", thoigianca);
        return SoccerTime;
    }

    public int getSoluongAsInt() {
        try {
            return Integer.parseInt(soluong);
        } catch (NumberFormatException e) {
            return 0; // Giá trị mặc định nếu lỗi
        }
    }

    public SoccerTime() {
    }

    public SoccerTime(String tenca, String giaca, String soluong, String thoigianca) {
        this.tenca = tenca;
        this.giaca = giaca;
        this.soluong = soluong;
        this.thoigianca = thoigianca;
    }

    public String getTenca() {
        return tenca;
    }

    public void setTenca(String tenca) {
        this.tenca = tenca;
    }

    public String getGiaca() {
        return giaca;
    }

    public void setGiaca(String giaca) {
        this.giaca = giaca;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getThoigianca() {
        return thoigianca;
    }

    public void setThoigianca(String thoigianca) {
        this.thoigianca = thoigianca;
    }
}
