package ru.kuzmin.apicatalog.domain.dto;

public record CountryDTO(
        Long id,
        String code,
        String name,
        String locale,
        String timezone,
        Boolean enabled
) {
}
