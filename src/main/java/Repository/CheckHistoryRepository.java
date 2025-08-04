package Repository;


import Entity.CheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckHistoryRepository extends JpaRepository<CheckHistory, String> {

    // per dashboard servizio: timeline eventi (tutti) e timeline failures
    List<CheckHistory> findByEndpointEndpointIdAndTimestampBetweenOrderByTimestampDesc(Integer endpointId, Instant from, Instant to);
    List<CheckHistory> findByEndpointEndpointIdAndSuccessFalseAndTimestampBetweenOrderByTimestampDesc(Integer endpointId, Instant from, Instant to);

    // ultimo check di un endpoint
    Optional<CheckHistory> findTopByEndpointEndpointIdOrderByTimestampDesc(Integer endpointId);

    // per dashboard globale: tutti gli eventi nel periodo, e solo i failures
    List<CheckHistory> findByTimestampBetweenOrderByTimestampDesc(Instant from, Instant to);

    List<CheckHistory> findByTimestampBetweenAndSuccessFalseOrderByTimestampDesc(Instant from, Instant to);


}//CheckHistory
