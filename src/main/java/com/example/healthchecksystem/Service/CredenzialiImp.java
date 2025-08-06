package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.Autenticazione;
import com.example.healthchecksystem.Entity.Credenziali;
import com.example.healthchecksystem.Repository.AutenticazioneRepository;
import com.example.healthchecksystem.Repository.CredenzialiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredenzialiImp implements CredenzialiInterface {


    private  CredenzialiRepository credenzialiRepository;
    private  AutenticazioneRepository autenticazioneRepository;

    @Autowired
    public CredenzialiImp(CredenzialiRepository credenzialiRepository, AutenticazioneRepository autenticazioneRepository) {
        this.credenzialiRepository = credenzialiRepository;
        this.autenticazioneRepository = autenticazioneRepository;
    }//CredenzialiImp



    @Override
    public List<Credenziali> listByAuth(Integer authId) {
        // Verifica esistenza dell'Autenticazione
        autenticazioneRepository.findById(authId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autenticazione non trovata: " + authId));


        return credenzialiRepository.findAll().stream()
                .filter(c -> c.getAutenticazione() != null
                        && authId.equals(c.getAutenticazione().getId())).collect(Collectors.toList());
    }//listByAuth

    @Override
    public Credenziali getById(Integer id) {
        return credenzialiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Credenziali non trovate: " + id));
    }//getById

    @Override
    @Transactional
    public Credenziali create(Credenziali cred) {
        // Controllo che esissta autenticazione
        if (cred.getAutenticazione() == null
                || cred.getAutenticazione().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Devi fornire l'id dell'Autenticazione");
        }
        Integer authId = cred.getAutenticazione().getId();
        Autenticazione parent = autenticazioneRepository.findById(authId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autenticazione non trovata con id: " + authId));

        cred.setAutenticazione(parent);
        return credenzialiRepository.save(cred);
    }//create

    @Override
    @Transactional
    public Credenziali update(Integer id, Credenziali cred) {
        Credenziali existing = credenzialiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Credenziali non trovate: " + id));

        existing.setUsername(cred.getUsername());
        existing.setPassword(cred.getPassword());
        existing.setToken(cred.getToken());
        existing.setCertificate(cred.getCertificate());
        return credenzialiRepository.save(existing);
    }//update

    @Override
    @Transactional
    public void delete(Integer id) {

        if (!credenzialiRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Credenziali non trovate: " + id);
        }
        credenzialiRepository.deleteById(id);

    }//delete

    @Override
    public List<Credenziali> listAll() {
        return credenzialiRepository.findAll();
    }


}//CredenzialiImp
