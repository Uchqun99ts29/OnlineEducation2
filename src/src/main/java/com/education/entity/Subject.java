package com.education.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "child")
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends Auditable {

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    private Education education;
}

