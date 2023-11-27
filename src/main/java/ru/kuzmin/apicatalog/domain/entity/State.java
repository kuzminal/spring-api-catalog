package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "state", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class State extends Base {
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
