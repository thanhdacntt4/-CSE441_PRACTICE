package com.example.b2andb3_btth4;


public class Student {

    private String id;
    private String hoten;
    private String lop;
    private double diem;
    private String anhdaidien;

    public Student() {
        // Constructor mặc định cho Firestore
    }

    public Student(String id, String hoten, String lop, double diem, String anhdaidien) {
        this.id = id;
        this.hoten = hoten;
        this.lop = lop;
        this.diem = diem;
        this.anhdaidien = anhdaidien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String getAnhdaidien() {
        return anhdaidien;
    }

    public void setAnhdaidien(String anhdaidien) {
        this.anhdaidien = anhdaidien;
    }
}
