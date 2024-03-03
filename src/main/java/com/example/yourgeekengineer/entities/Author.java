package com.example.yourgeekengineer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long authorId;

    @Column(name = "expertise")
    private String expertise;

    //uni directional relation with user to get user data related to the author
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    //ManyToOne unidirectional relation we don't want badge to get authors list
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<BlogPost> blogs = new ArrayList<>();
}
