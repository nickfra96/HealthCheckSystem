package Service;

import Entity.CheckHistory;
import Repository.CheckHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class CheckHistoryImp implements CheckHistoryInterface {

    private final CheckHistoryRepository checkHistoryRepository;

    @Autowired
    public CheckHistoryImp(CheckHistoryRepository checkHistoryRepository) {
        this.checkHistoryRepository = checkHistoryRepository;
    }//CheckHistoryImp


    @Override
    public List<CheckHistory> listAll() {
        return checkHistoryRepository.findAll();
    }//listAll

    @Override
    public List<CheckHistory> listByEndpoint(Integer endpointId, Instant from, Instant to) {
        // restituisce tutti gli eventi per l'endpoint e intervallo
        return checkHistoryRepository.findByEndpointEndpointIdAndTimestampBetweenOrderByTimestampDesc(endpointId, from, to);
    }//listByEndpoint

    @Override
    public List<CheckHistory> listFailuresByEndpoint(Integer endpointId, Instant from, Instant to) {
        // restituisce solo i failures
        return checkHistoryRepository.findByEndpointEndpointIdAndSuccessFalseAndTimestampBetweenOrderByTimestampDesc(endpointId, from, to);
    }//listFailuresByEndpoint

    @Override
    public CheckHistory getLatestByEndpoint(Integer endpointId) {
        return checkHistoryRepository.findTopByEndpointEndpointIdOrderByTimestampDesc(endpointId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nessuna history trovata per endpoint: " + endpointId
                ));
    }

    @Override
    public List<CheckHistory> listGlobal(Instant from, Instant to) {
        return checkHistoryRepository.findByTimestampBetweenOrderByTimestampDesc(from, to);
    }//listGlobal

    @Override
    public List<CheckHistory> listGlobalFailures(Instant from, Instant to) {
        return checkHistoryRepository.findByTimestampBetweenAndSuccessFalseOrderByTimestampDesc(from, to);
    }//listGlobalFailures

}//CheckHistoryImp
