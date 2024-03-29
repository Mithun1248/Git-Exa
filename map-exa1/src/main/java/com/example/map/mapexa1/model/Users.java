package com.example.map.mapexa1.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name="group_id")
    @JsonManagedReference
    private UserGroup groups;

//    @ManyToOne
//    @JoinColumn(name="group_id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    private UserGroup groups;
}
