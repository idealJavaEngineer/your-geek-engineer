package com.example.yourgeekengineer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name="road_map")
@Getter
@Setter
public class RoadMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roadmap_id")
    private long roadMapId;

    @Column(name = "name")
    private String name;

    @Column(name = "itemCount")
    private int itemCount;

    @Column(name = "chapterCount")
    private int chaptersCount;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "chapter_id")
    private List<Chapter> chapters;
}
