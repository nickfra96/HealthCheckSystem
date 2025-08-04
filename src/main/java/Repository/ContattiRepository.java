package Repository;


import Entity.Contatti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContattiRepository extends JpaRepository<Contatti, String> {

    List<Contatti> findByServiceServiceId(String serviceId);
    Contatti findByEmail(String email);
    List<Contatti> findByRole(String role);


}//ContattiRepository
