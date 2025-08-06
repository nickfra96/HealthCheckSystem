package com.example.healthchecksystem.Repository;


import com.example.healthchecksystem.Entity.Contatti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContattiRepository extends JpaRepository<Contatti, String> {

    List<Contatti> findByServiceId(String serviceId);
    Contatti findByEmail(String email);



}//ContattiRepository
