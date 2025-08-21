package com.besidetech.aims.repository;


import com.besidetech.aims.entity.LogSourcePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSourcePathRepository extends JpaRepository<LogSourcePath, Integer> {

    List<LogSourcePath> findByLogSourceId(Integer logSourceId);



}//LogSourcePathRepository
