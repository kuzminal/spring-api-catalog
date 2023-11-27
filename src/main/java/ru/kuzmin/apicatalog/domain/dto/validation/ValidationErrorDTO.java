package ru.kuzmin.apicatalog.domain.dto.validation;

import java.util.List;

public record ValidationErrorDTO(
        List<ViolationDTO> violations
) {
}
