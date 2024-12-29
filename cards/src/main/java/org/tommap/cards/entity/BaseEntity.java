package org.tommap.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false, nullable = false, length = 20)
    @CreatedBy
    private String createdBy;

    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", insertable = false, length = 20)
    @LastModifiedBy
    private String updatedBy;
}
