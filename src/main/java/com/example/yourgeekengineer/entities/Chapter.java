package com.example.yourgeekengineer.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "chapter")
@Getter
@Setter
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private long chapterId;

    @Column(name = "name")
    private String name;

    @Column(name = "items")
    private int itemsCount;

    @Column(name = "description")
    private String description;

    @Column(name = "chapter_number")
    private int chapterNumber;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name ="item_id")
    private List<Item> items;

}
