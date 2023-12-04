package ru.kuzmin.apicatalog.domain.dto;

import java.time.LocalDateTime;

public record CurrencyDTO(
        Long id,
        String code,
        String description,
        Boolean enabled,
        Integer decimalPlaces,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        Long version
) {}
