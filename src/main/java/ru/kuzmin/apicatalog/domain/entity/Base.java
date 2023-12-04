package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Base {
    @Column(name = "created_on", nullable = false)
    @CreatedDate
    private LocalDateTime createdOn;
    @Column(name = "updated_on", nullable = true)
    @LastModifiedDate
    private LocalDateTime updatedOn;
    @Version
    private Long version;
}
