package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.Contatti;
import com.example.healthchecksystem.Entity.Service;
import com.example.healthchecksystem.Repository.ContattiRepository;
import com.example.healthchecksystem.Repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.stereotype.Service
public class ContattiImp implements ContattiInterface {


    @Autowired
    private  ContattiRepository contattiRepo;
    @Autowired
    private  ServiceRepository serviceRepo;



    @Override
    public List<Contatti> listAll() {
        return contattiRepo.findAll();
    }//listAll

    @Override
    public List<Contatti> listByService(String serviceId) {

        serviceRepo.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Service non trovato: " + serviceId
                ));
        return contattiRepo.findByServiceId(serviceId);
    }//listByService

    @Override
    public Contatti getById(String id) {
        return contattiRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contatto non trovato: " + id
                ));
    }//getById

    @Override
    @Transactional
    public Contatti create(Contatti contatto) {

        if (contatto.getService() == null
                || contatto.getService().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Service non trovato: "
            );
        }
        String svcId = contatto.getService().getId();
        Service parent = serviceRepo.findById(svcId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Service non trovato con id: " + svcId
                ));
        contatto.setService(parent);
        return contattiRepo.save(contatto);
    }//create

    @Override
    @Transactional
    public Contatti update(String id, Contatti contatto) {

        Contatti existing = contattiRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contatto non trovato: " + id
                ));

        existing.setEmail(contatto.getEmail());
        existing.setLivello(contatto.getLivello());
        existing.setDescrizione(contatto.getDescrizione());
        return contattiRepo.save(existing);

    }//update

    @Override
    @Transactional
    public void delete(String id) {

        if (!contattiRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Contatto non trovato: " + id
            );
        }
        contattiRepo.deleteById(id);

    }//delete
}//ContattiImp
