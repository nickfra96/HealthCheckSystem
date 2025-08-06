package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.Endpoint;
import com.example.healthchecksystem.Repository.EndpointRepository;
import com.example.healthchecksystem.Repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EndpointImp implements EndpointInterface {


    private final EndpointRepository endpointRepo;
    private final ServiceRepository serviceRepo;

    @Autowired
    public EndpointImp(EndpointRepository endpointRepo, ServiceRepository serviceRepo) {
        this.endpointRepo = endpointRepo;
        this.serviceRepo  = serviceRepo;
    }//EndpointImp



    @Override
    public List<Endpoint> listByService(String serviceId) {
        // Verifico che esista il Service
        serviceRepo.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Service non trovato: " + serviceId
                ));
        // Restituisco tutti gli endpoint per quel serviceId
        return endpointRepo.findByServiceId(serviceId);
    }//listByService

    @Override
    public Endpoint getById(Integer id) {
        return endpointRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Endpoint non trovato: " + id
                ));
    }//getById

    @Override
    @Transactional
    public Endpoint create(Endpoint endpoint) {


        if (endpoint.getService() == null
                || endpoint.getService().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Devi fornire l'id del Service"
            );
        }
        String svcId = endpoint.getService().getId();
        var parent = serviceRepo.findById(svcId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Service non trovato con id: " + svcId
                ));
        endpoint.setService(parent);
        return endpointRepo.save(endpoint);

    }//create

    @Override
    @Transactional
    public Endpoint update(Integer id, Endpoint endpoint) {
        Endpoint existing = endpointRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Endpoint non trovato: " + id
                ));
        // Aggiorno i campi
        existing.setType(endpoint.getType());
        existing.setUrl(endpoint.getUrl());
        existing.setMethod(endpoint.getMethod());
        existing.setExpected_status(endpoint.getExpected_status());
        existing.setExpected_content_status(endpoint.getExpected_content_status());
        existing.setProtocollo(endpoint.getProtocollo());
        existing.setTimeoutMs(endpoint.getTimeoutMs());
        existing.setPorta(endpoint.getPorta());
        return endpointRepo.save(existing);
    }//update

    @Override
    @Transactional
    public void delete(Integer id) {

        if (!endpointRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Endpoint non trovato: " + id
            );
        }//if
        endpointRepo.deleteById(id);
    }//delete

    @Override
    public List<Endpoint> listAll() {
        return endpointRepo.findAll();
    }

}//EndpointImp
