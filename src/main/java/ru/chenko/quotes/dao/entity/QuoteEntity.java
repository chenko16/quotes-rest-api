package ru.chenko.quotes.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUOTE")
public class QuoteEntity {

    @Id @GeneratedValue
    private Long id;

    private String isin;
    private Double bid;
    private Double ask;

    public QuoteEntity() {
    }

    public QuoteEntity(String isin, Double bid, Double ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }

    public String getIsin() {
        return isin;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }
}
