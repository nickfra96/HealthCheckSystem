package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.Contatti;

import java.util.List;

public interface ContattiInterface {


    List<Contatti> listAll();
    List<Contatti> listByService(String serviceId);
    Contatti getById(String id);
    Contatti create(Contatti contatto);
    Contatti update(String id, Contatti contatto);
    void delete(String id);



}//ContattiInterface
