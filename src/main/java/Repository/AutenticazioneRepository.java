package Repository;


import Entity.Autenticazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutenticazioneRepository extends JpaRepository<Autenticazione, Integer> {

    Optional<Autenticazione> findByEndpointEndpointId(Integer endpointId);


}//AutenticazioneRepository
