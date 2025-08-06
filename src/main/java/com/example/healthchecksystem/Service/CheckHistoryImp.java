package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.CheckHistory;
import com.example.healthchecksystem.Entity.Endpoint;
import com.example.healthchecksystem.Repository.CheckHistoryRepository;
import com.example.healthchecksystem.Repository.EndpointRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class CheckHistoryImp implements CheckHistoryInterface {

    private final CheckHistoryRepository checkHistoryRepository;
    private final EndpointRepository endpointRepository;

    @Autowired
    public CheckHistoryImp(
            CheckHistoryRepository checkHistoryRepository,
            EndpointRepository endpointRepository
    ) {
        this.checkHistoryRepository = checkHistoryRepository;
        this.endpointRepository     = endpointRepository;
    }


    @Override
    public List<CheckHistory> listAll() {
        return checkHistoryRepository.findAll();
    }//listAll

    @Override
    public List<CheckHistory> listByEndpoint(Integer endpointId, Instant from, Instant to) {
        // restituisce tutti gli eventi per l'endpoint e intervallo
        return checkHistoryRepository.findByEndpointIdAndTimestampBetweenOrderByTimestampDesc(endpointId, from, to);
    }//listByEndpoint

    @Override
    public List<CheckHistory> listFailuresByEndpoint(Integer endpointId, Instant from, Instant to) {
        // restituisce solo i failures
        return checkHistoryRepository.findByEndpointIdAndSuccessFalseAndTimestampBetweenOrderByTimestampDesc(endpointId, from, to);
    }//listFailuresByEndpoint

    @Override
    public CheckHistory getLatestByEndpoint(Integer endpointId) {
        return checkHistoryRepository.findTopByEndpointIdOrderByTimestampDesc(endpointId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nessuna history trovata per endpoint: " + endpointId));
    }

    @Override
    public List<CheckHistory> listGlobal(Instant from, Instant to) {
        return checkHistoryRepository.findByTimestampBetweenOrderByTimestampDesc(from, to);
    }//listGlobal

    @Override
    public List<CheckHistory> listGlobalFailures(Instant from, Instant to) {
        return checkHistoryRepository.findByTimestampBetweenAndSuccessFalseOrderByTimestampDesc(from, to);
    }//listGlobalFailures

    @Override
    @Transactional
    public CheckHistory create(CheckHistory record) {
        //Validazione: serve l'ID dell'endpoint
        if (record.getEndpoint() == null || record.getEndpoint().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Devi fornire l'id dell'Endpoint");
        }//if

        //Carico l'Endpoint
        Integer epId = record.getEndpoint().getId();
        Endpoint endpoint = endpointRepository.findById(epId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Endpoint non trovato con id: " + epId));
        // salvo
        record.setEndpoint(endpoint);
        // il timestamp verrà messo automticamente da @CreationTimestamp
        return checkHistoryRepository.save(record);
    }//create

}//CheckHistoryImp
