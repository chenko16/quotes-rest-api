package ru.chenko.quotes.model;

public class Quote {

    private String isin;
    private Double bid;
    private Double ask;

    public Quote(String isin, Double bid, Double ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }
}
