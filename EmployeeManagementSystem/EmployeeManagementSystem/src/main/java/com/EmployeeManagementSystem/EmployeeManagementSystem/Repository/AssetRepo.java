package com.EmployeeManagementSystem.EmployeeManagementSystem.Repository;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepo extends JpaRepository<Assets, Integer> {
    Optional<Assets> findById(int assetId);
}