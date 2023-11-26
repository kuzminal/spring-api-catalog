package ru.kuzmin.apicatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kuzmin.apicatalog.domain.dto.CurrencyDTO;
import ru.kuzmin.apicatalog.domain.entity.Currency;
import ru.kuzmin.apicatalog.mapper.ApiMapper;
import ru.kuzmin.apicatalog.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrrencyService {
    private final CurrencyRepository repository;

    public CurrrencyService(CurrencyRepository repository) {
        this.repository = repository;
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
        return currencyDTO;
    }

    public CurrencyDTO update(CurrencyDTO currencyDTO) {
        return currencyDTO;
    }

    public void delete(Long id) {
// TODO Auto-generated method stub
    }
}
