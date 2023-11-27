package ru.kuzmin.apicatalog.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Data
public class Audit {
    @Column(name = "created_on", nullable = false)
    @CreatedDate
    private LocalDateTime createdOn;
    @Column(name = "updated_on", nullable = true)
    @LastModifiedDate
    private LocalDateTime updatedOn;
}
