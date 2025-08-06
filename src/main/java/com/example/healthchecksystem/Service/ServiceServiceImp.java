package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.Service;
import com.example.healthchecksystem.Repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImp implements ServiceServiceInterface {

    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    public List<Service> list(String name, String env, String owner, Boolean disabled) {
        return serviceRepository.findAll().stream()
                .filter(s -> name     == null || s.getNome().toLowerCase().contains(name.toLowerCase()))
                .filter(s -> env      == null || s.getEnvironment().equals(env))
                .filter(s -> owner    == null || s.getOwner().equals(owner))
                .filter(s -> disabled == null || s.isStato() == disabled)
                .collect(Collectors.toList());
    }//list

    @Override
    public Service getById(String serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Service non trovato con id: " + serviceId)
                );
    }//getById

    @Override
    @Transactional
    public Service create(Service service) {
        service.setStato(false);
        return serviceRepository.save(service);
    }//create

    @Override
    public Service update(String serviceId, Service service) {
        Service existing = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service non trovato con id: " + serviceId));
        existing.setNome(service.getNome());
        existing.setDescrizione(service.getDescrizione());
        existing.setOwner(service.getOwner());
        existing.setEnvironment(service.getEnvironment());
        existing.setVersion(service.getVersion());
        existing.setStato(service.isStato());

        return serviceRepository.save(existing);
    }//update

    @Override
    @Transactional
    public void disable(String serviceId) {

        Service existing = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Service non trovato con id: " + serviceId)
                );
        existing.setStato(true);
        serviceRepository.save(existing);

    }//disable

    @Override
    public List<Service> listAll() {
        return serviceRepository.findAll();
    }

    @Override
    @Transactional
    public void addDependency(String serviceId, String dependsOnId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Service non trovato: " + serviceId));
        Service dependency = serviceRepository.findById(dependsOnId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Service da cui dipende non trovato: " + dependsOnId));

        service.getDependencies().add(dependency);
        serviceRepository.save(service);
    }//addDependency

    @Override
    public Set<Service> getDependencies(String serviceId) {
        return serviceRepository.findById(serviceId)
                .map(Service::getDependencies)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Service non trovato: " + serviceId));
    }//getDependencies

    @Override
    public Set<Service> getDependents(String serviceId) {
        return serviceRepository.findById(serviceId)
                .map(Service::getDependents)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Service non trovato: " + serviceId));
    }//getDependents







}//ServiceServiceImp
