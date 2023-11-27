package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "city", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class City extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq")
    private Long id;
    private String name;
    private Boolean enabled;
}
