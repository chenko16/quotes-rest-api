package ru.chenko.quotes.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENERGY_LEVEL")
public class EnergyLevelEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String isin;
    private Double value;

    public EnergyLevelEntity() {
    }

    public EnergyLevelEntity(String isin, Double value) {
        this.isin = isin;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getIsin() {
        return isin;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
