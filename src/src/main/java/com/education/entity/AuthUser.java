package com.education.entity;

import com.education.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Auditable {

    @Column(nullable = false)
    private String fullName;

    private String imagePath;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @ManyToOne(optional = false)
    private Education education;

    @ManyToOne(optional = false)
    private Address address;

    @ManyToOne
    @Enumerated(value = EnumType.STRING)
    private Language language;


    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "boolean default false")
    boolean isBlock;

    private String parentEmail;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Payload> payloads;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Daily> dailies;

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Long id, Long createdBy, Long updatedBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String username, String password, String fullName, String phone, String chatId, Role role) {
        super(id, createdBy, updatedBy, createdAt, updatedAt, deleted);
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
    }

    public String getAuthority() {
        return role.name();
    }
}
