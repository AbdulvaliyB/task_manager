package com.example.task_manager.repository;

import com.example.task_manager.entity.User;
import com.example.task_manager.projection.UserTaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
