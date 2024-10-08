
package com.example.thuchanh3;
import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private String gender;
    private double gpa;
    private String birthDate; // Ngày sinh
    private String email; // Email
    private String major; // Chuyên ngành

    public Student(String id, String name, String gender, double gpa, String birthDate, String email, String major) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.gpa = gpa;
        this.birthDate = birthDate;
        this.email = email;
        this.major = major;
    }

    // Getter và Setter cho các thuộc tính
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getGpa() {
        return gpa;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    // Thêm phương thức để lấy ID hình ảnh giới tính
    public int getGenderImage() {
        return gender.equals("male") ? R.drawable.ic_male : R.drawable.ic_female;
    }
}
