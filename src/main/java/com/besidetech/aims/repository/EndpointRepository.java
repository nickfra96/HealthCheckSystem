package com.besidetech.aims.repository;

import com.besidetech.aims.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Integer> {

    List<Endpoint> findByServiceId(String id);



}//EndpointRepository
