package com.education.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected Long id;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

    @CreatedDate
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;
}
