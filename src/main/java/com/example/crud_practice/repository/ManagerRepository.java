package com.example.crud_practice.repository;

import com.example.crud_practice.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByManagerName(String managerName);

}
