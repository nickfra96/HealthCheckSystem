package Service;

import Entity.Credenziali;

import java.util.List;

public interface CredenzialiInterface {

    List<Credenziali> listByAuth(Integer authId);
    Credenziali getById(Integer id);
    Credenziali create(Credenziali cred);
    Credenziali update(Integer id, Credenziali cred);
    void delete(Integer id);
    List<Credenziali> listAll();

}//CredenzialiInterface
