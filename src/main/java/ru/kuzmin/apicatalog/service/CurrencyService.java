package ru.kuzmin.apicatalog.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.domain.entity.Currency;
import ru.kuzmin.apicatalog.mapper.ApiMapper;
import ru.kuzmin.apicatalog.repository.CurrencyRepository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Service
public class CurrencyService {
    private final CurrencyRepository repository;
    private final Validator validator;
    private final TransactionTemplate transactionTemplate;

    public CurrencyService(CurrencyRepository repository, Validator validator, TransactionTemplate transactionTemplate, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.validator = validator;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setReadOnly(false);
        transactionTemplate.setTimeout(1000);
    }

    @Transactional(
            readOnly = true,
            rollbackFor = {SQLException.class},
            isolation = Isolation.READ_COMMITTED,
            timeout = 1000
    )
    @Cacheable(cacheNames = "CATALOG_CURRENCY", unless = "#result.empty()")
    public Optional<CurrencyDTO> getById(Long id) {
        Optional<Currency> currency = repository.findById(id);
        return currency.map(ApiMapper.INSTANCE::entityToDTO);
    }

    public CurrencyDTO save(CurrencyDTO currencyDTO) {
        Currency entity = ApiMapper.INSTANCE.DTOToEntity(currencyDTO);
        Set<ConstraintViolation<Currency>> violations = validator.
                validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Currency savedEntity = this.transactionTemplate.execute(status -> repository.save(entity));

        return ApiMapper.INSTANCE.entityToDTO(savedEntity);
    }

    public Optional<CurrencyDTO> update(CurrencyDTO currencyDTO) {
        Currency entity = ApiMapper.INSTANCE.DTOToEntity(currencyDTO);
        Set<ConstraintViolation<Currency>> violations = validator.
                validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Currency savedEntity = this.transactionTemplate.execute(status -> {
                Optional<Currency> dbCurrency = repository.findById(currencyDTO.id());
                if (dbCurrency.isPresent()) {
                    Currency forSave = repository.save(ApiMapper.INSTANCE.DTOToEntity(currencyDTO));
                    return repository.save(ApiMapper.INSTANCE.DTOToEntity(currencyDTO));
                } else {
                    return null;
                }
        });

        return savedEntity != null ? Optional.of(ApiMapper.INSTANCE.entityToDTO(savedEntity)) : Optional.empty();
    }

    public void delete(Long id) {
// TODO Auto-generated method stub
    }
}
