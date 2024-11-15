package com.example.datt.model;

import java.util.HashMap;

public class Book {
    String tensan;
    Integer ca;
    String thoigiandat;
    String trangthai;
    Integer gia;
    String mathanhtoan;

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> FootballField = new HashMap<>();
        FootballField.put("tensan", tensan);
        FootballField.put("ca", ca);
        FootballField.put("thoigiandat", thoigiandat);
        FootballField.put("trangthai", trangthai);
        FootballField.put("gia", gia);
        FootballField.put("mathanhtoan", mathanhtoan);
        return FootballField;
    }

    public Book() {
    }

    public Book(String tensan, Integer ca, String thoigiandat, String trangthai, Integer gia, String mathanhtoan) {
        this.tensan = tensan;
        this.ca = ca;
        this.thoigiandat = thoigiandat;
        this.trangthai = trangthai;
        this.gia = gia;
        this.mathanhtoan = mathanhtoan;
    }

    public String getTensan() {
        return tensan;
    }

    public void setTensan(String tensan) {
        this.tensan = tensan;
    }

    public Integer getCa() {
        return ca;
    }

    public void setCa(Integer ca) {
        this.ca = ca;
    }

    public String getThoigiandat() {
        return thoigiandat;
    }

    public void setThoigiandat(String thoigiandat) {
        this.thoigiandat = thoigiandat;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getMathanhtoan() {
        return mathanhtoan;
    }

    public void setMathanhtoan(String mathanhtoan) {
        this.mathanhtoan = mathanhtoan;
    }
}
