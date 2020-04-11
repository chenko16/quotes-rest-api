package ru.chenko.quotes.model;

public class EnergyLevel {

    private String isin;
    private Double value;

    public EnergyLevel() {
    }

    public EnergyLevel(String isin, Double value) {
        this.isin = isin;
        this.value = value;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
