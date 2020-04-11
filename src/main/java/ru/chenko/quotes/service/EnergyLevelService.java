package ru.chenko.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chenko.quotes.dao.Dao;
import ru.chenko.quotes.model.EnergyLevel;
import ru.chenko.quotes.model.Quote;
import ru.chenko.quotes.model.QuoteValidator;

import java.util.List;

@Service
public class EnergyLevelService {

    private Dao dao;

    @Autowired
    public EnergyLevelService(Dao dao) {
        this.dao = dao;
    }

    public void processQuote(Quote quote) {
        QuoteValidator.checkQuote(quote);

        EnergyLevel energyLevel = dao.findEnergyLevelByIsin(quote.getIsin());
        if(energyLevel == null) {
            if(quote.getBid() == null) {
                energyLevel = new EnergyLevel(quote.getIsin(), quote.getAsk());
            } else {
                energyLevel = new EnergyLevel(quote.getIsin(), quote.getBid());
            }
        } else {
            if(quote.getBid() == null) {
                energyLevel.setValue(quote.getAsk());
            } else {
                if(quote.getBid() > energyLevel.getValue()) {
                    energyLevel.setValue(quote.getBid());
                }
                if(quote.getAsk() < energyLevel.getValue()) {
                    energyLevel.setValue(quote.getAsk());
                }
            }
        }

        dao.saveEnergyLevel(energyLevel);
        dao.saveQuote(quote);
    }

    public EnergyLevel findEnergyLevelByIsin(String isin) {
        return dao.findEnergyLevelByIsin(isin);
    }

    public List<EnergyLevel> getEnergyLevelList() {
        return dao.getEnergyLevelList();
    }
}
