package com.example.yourgeekengineer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_post")
@Getter
@Setter
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogId;

    @Column(name = "blog_name")
    private String blogName;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "last_updated_at")
    private Timestamp lastUpdatedAt;

    @Column(name = "published_at")
    private Timestamp publishedAt;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tag_blog_post",
            joinColumns = @JoinColumn(name = "blog_post_blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @Override
    public String toString() {
        return "BlogPost{" +
                "blogId=" + blogId +
                ", blogName='" + blogName + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", categories=" + category+
                ", tags=" + tags +
                '}';
    }
}
