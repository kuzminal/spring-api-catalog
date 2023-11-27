package ru.kuzmin.apicatalog.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.domain.entity.Currency;
import ru.kuzmin.apicatalog.mapper.ApiMapper;
import ru.kuzmin.apicatalog.repository.CurrencyRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class CurrrencyService {
    private final CurrencyRepository repository;
    private final Validator validator;

    public CurrrencyService(CurrencyRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public CurrencyDTO getById(Long id) {
        CurrencyDTO response = null;
        Optional<Currency> currency = repository.findById(id);
        if (currency.isPresent()) {
            response = ApiMapper.INSTANCE.entityToDTO(currency.get());
        }
        return response;
    }

    public CurrencyDTO save(CurrencyDTO currencyDTO) {
        Currency entity = ApiMapper.INSTANCE.DTOToEntity(currencyDTO);
        Set<ConstraintViolation<Currency>> violations = validator.
                validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Currency savedEntity = repository.save(entity);
        return ApiMapper.INSTANCE.entityToDTO(savedEntity);
    }

    public CurrencyDTO update(CurrencyDTO currencyDTO) {
        return currencyDTO;
    }

    public void delete(Long id) {
// TODO Auto-generated method stub
    }
}
