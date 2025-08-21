package com.besidetech.aims.repository;


import com.besidetech.aims.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {

    List<Service> findByNomeContainingIgnoreCase(String nome);
    List<Service> findByEnvironment(String environment);
    List<Service> findByOwner(String owner);
    List<Service> findByStato(boolean stato);


}//ServiceRepository
