package com.example.healthchecksystem.Repository;


import com.example.healthchecksystem.Entity.CheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckHistoryRepository extends JpaRepository<CheckHistory, String> {

    // per dashboard servizio: timeline eventi (tutti) e timeline failures
    List<CheckHistory> findByEndpointIdAndTimestampBetweenOrderByTimestampDesc(Integer endpointId, Instant from, Instant to);
    List<CheckHistory> findByEndpointIdAndSuccessFalseAndTimestampBetweenOrderByTimestampDesc(Integer endpointId, Instant from, Instant to);

    // ultimo check di un endpoint
    Optional<CheckHistory> findTopByEndpointIdOrderByTimestampDesc(Integer endpointId);

    // per dashboard globale: tutti gli eventi nel periodo, e solo i failures
    List<CheckHistory> findByTimestampBetweenOrderByTimestampDesc(Instant from, Instant to);
    List<CheckHistory> findByTimestampBetweenAndSuccessFalseOrderByTimestampDesc(Instant from, Instant to);
}//CheckHistory
