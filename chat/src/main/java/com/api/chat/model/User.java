package com.api.chat.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String name;

    private String pwd;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.USER;
}
