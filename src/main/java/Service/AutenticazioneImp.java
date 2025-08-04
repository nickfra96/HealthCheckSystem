package Service;


import Entity.Autenticazione;
import Entity.Endpoint;
import Repository.AutenticazioneRepository;
import Repository.EndpointRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AutenticazioneImp implements AutenticazioneInterface {


    private final AutenticazioneRepository authRepo;
    private final EndpointRepository endpointRepo;

    @Autowired
    public AutenticazioneImp(AutenticazioneRepository authRepo, EndpointRepository endpointRepo) {
        this.authRepo     = authRepo;
        this.endpointRepo = endpointRepo;
    }//AutenticazioneImp

    @Override
    public List<Autenticazione> listAll() {
        return authRepo.findAll();

    }//listAll

    @Override
    public Autenticazione getById(Integer id) {
        return authRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autenticazione non trovata: " + id
                ));
    }//getById

    @Override
    public Autenticazione getByEndpoint(Integer endpointId) {
        return authRepo.findByEndpointEndpointId(endpointId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autenticazione non trovata per endpoint: " + endpointId
                ));
    }//getByEndpoint

    @Override
    @Transactional
    public Autenticazione create(Autenticazione auth) {
        if (auth.getEndpoint() == null || auth.getEndpoint().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Devi fornire l'id dell'Endpoint"
            );
        }
        Integer epId = auth.getEndpoint().getId();
        Endpoint ep = endpointRepo.findById(epId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Endpoint non trovato con id: " + epId
                ));
        auth.setEndpoint(ep);
        return authRepo.save(auth);
    }//create

    @Override
    @Transactional
    public Autenticazione update(Integer id, Autenticazione auth) {
        Autenticazione existing = authRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autenticazione non trovata: " + id
                ));
        existing.setTipoAuth(auth.getTipoAuth());
        existing.setUserId(auth.getUserId());
        existing.setStatus(auth.getStatus());
        // Nota: createdAt gestito automaticamente da @UpdateTimestamp
        return authRepo.save(existing);
    }//update

    @Override
    @Transactional
    public void delete(Integer id) {

        if (!authRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Autenticazione non trovata: " + id
            );
        }
        authRepo.deleteById(id);
    }//delete

}//AutenticazioneImp
