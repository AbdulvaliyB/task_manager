package com.example.task_manager.repository;




import com.example.task_manager.entity.User;
import com.example.task_manager.projection.UserTaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.firstname u.surname t.title t.description from users u join task t on t.id = u.task_id", nativeQuery = true)
    List<UserTaskProjection> getUserTaskProjection();

}
