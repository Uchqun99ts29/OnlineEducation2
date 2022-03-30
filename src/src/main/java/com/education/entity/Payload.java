package com.education.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payload extends Auditable {
    private int amount;

    @ManyToOne
    private AuthUser student;

    @ManyToOne
    private Group group;
}
