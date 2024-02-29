package com.example.yourgeekengineer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "category_tag",
                joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name ="tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BlogPost> blogPosts = new ArrayList<>();
}
