package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "currency", schema = "public")
@Data
@RequiredArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_seq")
    private Long id;
    @NotBlank(message = "Code is mandatory")
    @Column(name = "code", nullable = false, length = 4)
    private String code;
    @NotBlank(message = "Description is mandatory")
    @Column(name = "description", nullable = false, length = 30)
    private String description;
    @NotNull(message = "The state of the currency is mandatory")
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @Min(value = 1, message = "The minimum value is 1")
    @Max(value = 5, message = "The maximum value is 5")
    @Column(name = "decimal_places")
    private int decimalPlaces;
}
