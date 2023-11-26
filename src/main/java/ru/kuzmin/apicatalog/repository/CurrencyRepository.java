package ru.kuzmin.apicatalog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kuzmin.apicatalog.domain.entity.Currency;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    List<Currency> findByCode(String code);
    List<Currency> findByCodeAndDescription(String code, String description);
    List<Currency> findByDescriptionOrderByCodeAsc(String description);
    List<Currency> findByDescriptionOrderByCodeDesc(String description);

    @Query("SELECT c FROM Currency c WHERE c.code = :code ORDER BY c.code DESC")
    Currency retrieveByCode(@Param("code") String code);
}
