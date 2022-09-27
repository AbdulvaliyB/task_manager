package com.example.task_manager.repository;


import com.example.task_manager.entity.Role;
import com.example.task_manager.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleEnum name);
}
