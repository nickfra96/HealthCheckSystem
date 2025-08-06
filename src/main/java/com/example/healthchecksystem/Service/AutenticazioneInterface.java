package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.Autenticazione;

import java.util.List;

public interface AutenticazioneInterface {



    List<Autenticazione> listAll();
    Autenticazione getById(Integer id);
    Autenticazione getByEndpoint(Integer endpointId);
    Autenticazione create(Autenticazione auth);
    Autenticazione update(Integer id, Autenticazione auth);
    void delete(Integer id);



}//AutenticazioneInterface
