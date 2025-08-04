package Repository;

import Entity.Credenziali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredenzialiRepository extends JpaRepository<Credenziali, Integer> {

    List<Credenziali> findByCredType(String credType);


}//CredenzialiRepository
