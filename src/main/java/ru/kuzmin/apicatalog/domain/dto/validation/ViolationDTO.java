package ru.kuzmin.apicatalog.domain.dto.validation;

public record ViolationDTO(
        String field,
        String message
) {
}
