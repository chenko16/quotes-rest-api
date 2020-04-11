package ru.chenko.quotes.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chenko.quotes.dao.entity.EnergyLevelEntity;

public interface EnergyLevelRepository extends CrudRepository<EnergyLevelEntity, Long> {

    EnergyLevelEntity findEnergyLevelEntityByIsin(String isin);

}
