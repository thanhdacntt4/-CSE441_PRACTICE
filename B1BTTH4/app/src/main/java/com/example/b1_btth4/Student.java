package com.example.b1_btth4;

public class Student {
    private String mssv;
    private String hoten;
    private String lop;
    private double diem;

    public Student() {}

    public Student(String mssv, String hoten, String lop, double diem) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.lop = lop;
        this.diem = diem;
    }

    public String getMssv() { return mssv; }
    public String getHoten() { return hoten; }
    public String getLop() { return lop; }
    public double getDiem() { return diem; }
}
