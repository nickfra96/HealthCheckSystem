package com.besidetech.aims.repository;

import com.besidetech.aims.entity.LogSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSourceRepository extends JpaRepository<LogSource, Integer> {

    List<LogSource> findByServiceId(String id);

}//LogSource
