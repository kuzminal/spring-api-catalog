package ru.kuzmin.apicatalog.domain.dto;

public record CityDTO(
        Long id,
        String name,
        Boolean enabled
) {
}
