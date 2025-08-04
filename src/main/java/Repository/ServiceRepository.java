package Repository;


import Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {

    List<Service> findByNameContainingIgnoreCase(String name);
    List<Service> findByEnvironment(String environment);
    List<Service> findByOwner(String owner);
    List<Service> findByDisabled(boolean disabled);


}//ServiceRepository
