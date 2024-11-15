package com.example.datt.model;

import java.util.HashMap;

public class FootballField {
    String anh;
    String tensan;
    Integer soluongca;


    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> FootballField = new HashMap<>();
        FootballField.put("tensan", tensan);
        FootballField.put("soluongca", soluongca);
        FootballField.put("anh", anh);
        return FootballField;
    }

    public FootballField() {
    }

    public FootballField(String anh, String tensan, Integer soluongca) {
        this.anh = anh;
        this.tensan = tensan;
        this.soluongca = soluongca;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTensan() {
        return tensan;
    }

    public void setTensan(String tensan) {
        this.tensan = tensan;
    }

    public Integer getSoluongca() {
        return soluongca;
    }

    public void setSoluongca(Integer soluongca) {
        this.soluongca = soluongca;
    }
}
