package com.besidetech.aims.service;

import com.besidetech.aims.entity.Endpoint;

import java.util.List;

public interface EndpointInterface {


    List<Endpoint> listByService(String serviceId);
    Endpoint getById(Integer id);
    Endpoint create(Endpoint endpoint);
    Endpoint update(Integer id, Endpoint endpoint);
    void delete(Integer id);
    List<Endpoint> listAll();


}//EndpointInterface
