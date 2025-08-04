package Repository;

import Entity.LogSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSourceRepository extends JpaRepository<LogSource, Integer> {

    List<LogSource> findByServiceServiceId(String serviceId);

}//LogSource
