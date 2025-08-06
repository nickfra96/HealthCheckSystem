package com.example.healthchecksystem.Repository;


import com.example.healthchecksystem.Entity.Autenticazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutenticazioneRepository extends JpaRepository<Autenticazione, Integer> {

    Optional<Autenticazione> findByEndpointId(Integer id);


}//AutenticazioneRepository
