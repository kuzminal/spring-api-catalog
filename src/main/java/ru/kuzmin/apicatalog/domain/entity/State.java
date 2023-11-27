package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "state", schema = "public")
@Data
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_seq")
    private Long id;
    private String code;
    private String name;
    private Boolean enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, updatable = false,
            insertable = false)
    private Country country;
}
