package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
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
    private String code;
    private String description;
    private Boolean enabled;
    @Column(name = "decimal_places")
    private int decimalPlaces;
}
