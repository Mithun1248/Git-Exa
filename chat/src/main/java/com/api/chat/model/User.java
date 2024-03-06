package com.api.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String name;

    private String pwd;

    @Enumerated(value = EnumType.STRING)
    private List<Roles> role = List.of(Roles.USER);
}
