package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "currency")
@Data
@RequiredArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String code;
    private String description;
    private Boolean enabled;
    @Column(name = "decimal_places")
    private int decimalPlaces;
}
