package com.education.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Label extends Auditable {

     private String description;

     @Enumerated(EnumType.STRING)
     private com.education.enums.Label code;
}
