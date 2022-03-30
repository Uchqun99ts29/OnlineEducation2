package com.education.entity;

import com.education.enums.Label;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group extends Auditable {

    @ManyToOne
    private Education education;

    @Enumerated(EnumType.STRING)
    private Label label;

    private Double price;

    private String name;

    private LocalDateTime startTime;

    @ManyToOne
    private AuthUser teacher;

    @ManyToMany
    private List<AuthUser> student;

}
