package com.example.yourgeekengineer.repositories;


import com.example.yourgeekengineer.entities.Tag;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagName(String tagName);

    List<Tag> findAllByTagName(String tagName);

}
