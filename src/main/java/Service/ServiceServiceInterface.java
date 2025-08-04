package Service;

import Entity.Endpoint;
import Entity.Service;

import java.util.List;

public interface ServiceServiceInterface {

    List<Service> list(String name, String env, String owner, Boolean disabled);
    Service getById(String serviceId);
    Service create(Service service);
    Service update(String serviceId, Service service);
    void disable(String serviceId);
    List<Service> listAll();

}//ServiceServiceInterface
