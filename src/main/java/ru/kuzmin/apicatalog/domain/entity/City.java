package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "city", schema = "public")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq")
    private Long id;
    private String name;
    private Boolean enabled;
}
