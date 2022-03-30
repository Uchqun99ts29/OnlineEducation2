package com.education.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder(builderMethodName = "childBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Daily extends Auditable {

    private byte ball;

    private String description;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private AuthUser student;

    private LocalDate time;

    private Boolean isCame;
}