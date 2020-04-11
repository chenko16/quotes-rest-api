package ru.chenko.quotes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.chenko.quotes.dao.entity.EnergyLevelEntity;
import ru.chenko.quotes.dao.repository.EnergyLevelRepository;
import ru.chenko.quotes.dao.repository.QuoteRepository;
import ru.chenko.quotes.dao.entity.QuoteEntity;
import ru.chenko.quotes.model.EnergyLevel;
import ru.chenko.quotes.model.Quote;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoImpl implements Dao {

    private QuoteRepository quoteRepository;
    private EnergyLevelRepository energyLevelRepository;

    @Autowired
    public DaoImpl(QuoteRepository quoteRepository, EnergyLevelRepository energyLevelRepository) {
        this.quoteRepository = quoteRepository;
        this.energyLevelRepository = energyLevelRepository;
    }

    @Override
    @Transactional
    public void saveQuote(Quote security) {
        //Сохраняем историю, поэтому не ищем quotes с таким же ISIN, в просто добавляем новую запись
        QuoteEntity newQuote = new QuoteEntity(security.getIsin(), security.getBid(), security.getAsk());
        quoteRepository.save(newQuote);
    }

    @Override
    @Transactional
    public void saveEnergyLevel(EnergyLevel energyLevel) {
        EnergyLevelEntity found = energyLevelRepository.findEnergyLevelEntityByIsin(energyLevel.getIsin());
        if(found == null) {
            found = new EnergyLevelEntity(energyLevel.getIsin(), energyLevel.getValue());
        } else {
            found.setValue(energyLevel.getValue());
        }

        energyLevelRepository.save(found);
    }

    @Override
    @Transactional
    public EnergyLevel findEnergyLevelByIsin(String isin) {
        EnergyLevelEntity entity = energyLevelRepository.findEnergyLevelEntityByIsin(isin);
        if(entity != null) {
            return new EnergyLevel(entity.getIsin(), entity.getValue());
        }

        return null;
    }

    @Override
    @Transactional
    public List<EnergyLevel> getEnergyLevelList() {
        Iterable<EnergyLevelEntity> foundList = energyLevelRepository.findAll();

        List<EnergyLevel> energyLevelList = new ArrayList<>();
        foundList.forEach(found -> energyLevelList.add(new EnergyLevel(found.getIsin(), found.getValue())));

        return energyLevelList;
    }
}
