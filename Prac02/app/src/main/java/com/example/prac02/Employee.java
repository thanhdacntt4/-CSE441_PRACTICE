package com.example.prac02;
public class Employee {
    private int id;
    private String name;
    private String birthDate;
    private double salary;

    // Constructor
    public Employee(int id, String name, String birthDate, double salary) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Phương thức toString() để trả về thông tin của nhân viên
    @Override
    public String toString() {
        return  id + "-" + name + "-" + birthDate + "-" + salary;
    }
}
