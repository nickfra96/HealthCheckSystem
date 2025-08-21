package com.besidetech.aims.service;

import com.besidetech.aims.entity.Service;

import java.util.List;
import java.util.Set;

public interface ServiceServiceInterface {

    List<Service> list(String name, String env, String owner, Boolean disabled);
    Service getById(String serviceId);
    Service create(Service service);
    Service update(String serviceId, Service service);
    void disable(String serviceId);
    List<Service> listAll();
    void addDependency (String serviceId, String dependsOnId);
    Set<Service> getDependencies(String serviceId);
    Set<Service> getDependents(String serviceId);

}//ServiceServiceInterface
