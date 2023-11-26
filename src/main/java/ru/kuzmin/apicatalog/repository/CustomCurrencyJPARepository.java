package ru.kuzmin.apicatalog.repository;

import ru.kuzmin.apicatalog.domain.entity.Currency;

import java.util.List;

public interface CustomCurrencyJPARepository {
    List<Currency> myCustomFindMethod(String code);
}
