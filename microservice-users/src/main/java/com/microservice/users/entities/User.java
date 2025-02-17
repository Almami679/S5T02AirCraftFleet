package com.microservice.users.entities;

import com.microservice.users.entities.entitiesEnums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private double wallet;  // Saldo del usuario

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Hangar hangar;

    public User(String username, String password, Role role, int wallet) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.wallet = wallet;
        this.hangar = new Hangar(this);
    }
}