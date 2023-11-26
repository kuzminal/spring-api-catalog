package ru.kuzmin.apicatalog.domain.dto;

public record CurrencyDTO(
        Long id,
        String code,
        String description,
        Boolean enable,
        Integer decimalPlaces
) {}
