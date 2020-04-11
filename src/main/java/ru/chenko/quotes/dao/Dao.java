package ru.chenko.quotes.dao;

import ru.chenko.quotes.model.EnergyLevel;
import ru.chenko.quotes.model.Quote;

import java.util.List;

public interface Dao {

    void saveQuote(Quote security);

    void saveEnergyLevel(EnergyLevel energyLevel);
    EnergyLevel findEnergyLevelByIsin(String isin);
    List<EnergyLevel> getEnergyLevelList();
}
