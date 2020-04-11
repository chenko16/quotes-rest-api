package ru.chenko.quotes.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chenko.quotes.dao.entity.QuoteEntity;

public interface QuoteRepository extends CrudRepository<QuoteEntity, Long> {

    QuoteEntity findQuoteEntityByIsin(String isin);

}
