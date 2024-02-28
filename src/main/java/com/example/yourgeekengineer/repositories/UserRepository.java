package com.example.yourgeekengineer.repositories;

import com.example.yourgeekengineer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUserId(long userId);
}
