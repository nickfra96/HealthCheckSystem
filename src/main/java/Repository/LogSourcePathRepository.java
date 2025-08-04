package Repository;


import Entity.LogSourcePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSourcePathRepository extends JpaRepository<LogSourcePath, Integer> {

    List<LogSourcePath> findByLogSourceLogSourceId(String logSourceId);


}//LogSourcePathRepository
