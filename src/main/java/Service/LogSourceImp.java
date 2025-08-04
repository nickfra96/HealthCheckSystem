package Service;

import Entity.LogSource;
import Repository.LogSourceRepository;
import Repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LogSourceImp implements LogSourceInterface {


    private final LogSourceRepository logSourceRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public LogSourceImp(LogSourceRepository logSourceRepository, ServiceRepository   serviceRepository) {
        this.logSourceRepository = logSourceRepository;
        this.serviceRepository   = serviceRepository;
    } //LogSourceImp

    @Override
    public List<LogSource> listByService(String serviceId) {
        // Verifica esistenza del Service
        serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Service non trovato: " + serviceId
                ));
        // Recupera tutti i LogSource per quel serviceId
        return logSourceRepository.findByServiceServiceId(serviceId);
    }//listByService

    @Override
    public LogSource getById(Integer id) {
        return logSourceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "LogSource non trovato: " + id
                ));
    }//getById

    @Override
    @Transactional
    public LogSource create(LogSource logSource) {
        // Verifico che esista
        if (logSource.getService() == null || logSource.getService().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Devi fornire l'id del Service"
            );
        }
        String svcId = logSource.getService().getId();

        var parent = serviceRepository.findById(svcId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Service non trovato con id: " + svcId
                ));
        //salvo
        logSource.setService(parent);
        return logSourceRepository.save(logSource);
    }//create

    @Override
    @Transactional
    public LogSource update(Integer id, LogSource logSource) {
        LogSource existing = logSourceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "LogSource non trovato: " + id
                ));
        // Aggiorno i campi
        existing.setChannel(logSource.getChannel());
        existing.setAuth_required(logSource.isAuth_required());
        existing.setAuth_ref(logSource.getAuth_ref());
        existing.setFormat(logSource.getFormat());
        existing.setEndpoint(logSource.getEndpoint());

        return logSourceRepository.save(existing);
    }//update

    @Override
    @Transactional
    public void delete(Integer id) {

        if (!logSourceRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "LogSource non trovato: " + id
            );
        }
        logSourceRepository.deleteById(id);


    }//delete

    @Override
    public List<LogSource> listAll() {
        return logSourceRepository.findAll();
    }
}//LogSourceImp
