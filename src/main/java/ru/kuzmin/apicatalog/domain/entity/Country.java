package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "country", schema = "public")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    private Long id;
    @Column(name = "code", nullable = false, length = 4)
    private String code;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "locale", nullable = false, length = 6)
    private String locale;
    @Column(name = "time_zone", nullable = false, length = 10)
    private String timezone;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, updatable = false,
            insertable = false)
    @OrderBy(value = "id")
    private List<State> states;
}
