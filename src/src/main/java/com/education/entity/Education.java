package com.education.entity;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Education extends Auditable {

    private String logoPath;

   // @Column(nullable = false)
    private String phone;

   // @Column(unique = true,nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    private String url;

   // @Column(unique = true,nullable = false)
    private String name;


  // @NonNull
    private String lecithinPath;


    @Column
    private Date payedUntil;

    @Column(name = "isBlock", columnDefinition = "boolean default false")
    private boolean isBlock;

    @ManyToOne
    private AuthUser owner;

    @Override
    public Long getId() {
        return super.getId();
    }



}
