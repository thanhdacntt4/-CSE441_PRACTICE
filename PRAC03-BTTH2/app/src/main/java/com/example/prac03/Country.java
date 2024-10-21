package com.example.prac03;

public class Country {
    private String name;
    private int population;
    private int flagImage;
    private String capital;
    private String area; // Diện tích
    private String currency; // Tiền tệ

    public Country(String name, int population, int flagImage, String capital, String area, String currency) {
        this.name = name;
        this.population = population;
        this.flagImage = flagImage;
        this.capital = capital;
        this.area = area;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public String getCapital() {
        return capital;
    }

    public String getArea() {
        return area;
    }

    public String getCurrency() {
        return currency;
    }
}
