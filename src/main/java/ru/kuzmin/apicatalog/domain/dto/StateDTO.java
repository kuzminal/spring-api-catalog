package ru.kuzmin.apicatalog.domain.dto;

public record StateDTO(
        Long id,
        String code,
        String name,
        Boolean enabled,
        CountryDTO country) {
}
