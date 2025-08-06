package com.example.healthchecksystem.Repository;

import com.example.healthchecksystem.Entity.Credenziali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredenzialiRepository extends JpaRepository<Credenziali, Integer> {




}//CredenzialiRepository
