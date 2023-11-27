package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Base {
    @Embedded
    private Audit audit;

    /*@PrePersist
    public void fillCreatedOn() {
        audit.setCreatedOn(LocalDateTime.now());
    }

    @PreUpdate
    public void fillUpdatedOn() {
        audit.setUpdatedOn(LocalDateTime.now());
    }*/
}
