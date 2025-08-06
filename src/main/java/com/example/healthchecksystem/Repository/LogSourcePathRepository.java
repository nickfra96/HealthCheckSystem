package com.example.healthchecksystem.Repository;


import com.example.healthchecksystem.Entity.LogSourcePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSourcePathRepository extends JpaRepository<LogSourcePath, Integer> {

    List<LogSourcePath> findByLogSourceId(Integer logSourceId);



}//LogSourcePathRepository
