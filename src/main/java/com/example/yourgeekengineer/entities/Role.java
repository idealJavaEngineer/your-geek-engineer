package com.example.yourgeekengineer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> user = new ArrayList<>();
}
