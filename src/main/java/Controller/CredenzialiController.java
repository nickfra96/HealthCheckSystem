package Controller;


import Entity.Credenziali;
import Service.CredenzialiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
public class CredenzialiController {

    private final CredenzialiInterface service;

    @Autowired
    public CredenzialiController(CredenzialiInterface service) {
        this.service = service;
    }//CredenzialiController

    /**
     * GET /api/credentials
     * Restituisce tutte le credenziali.
     */
    @GetMapping
    public ResponseEntity<List<Credenziali>> listAll() {
        List<Credenziali> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }//listAll

    /**
     * GET /api/credentials/auth/{authId}
     * Restituisce tutte le credenziali per una specifica autenticazione.
     */
    @GetMapping("/auth/{authId}")
    public ResponseEntity<List<Credenziali>> listByAuth(@PathVariable Integer authId) {
        List<Credenziali> list = service.listByAuth(authId);
        return ResponseEntity.ok().body(list);
    }//listByAuth


    /**
     * GET /api/credentials/{id}
     * Restituisce una singola credenziale per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Credenziali> getById(@PathVariable Integer id) {
        Credenziali cred = service.getById(id);
        return ResponseEntity.ok().body(cred);
    }//getById

    /**
     * POST /api/credentials
     * Crea una nuova credenziale.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Credenziali> create(@RequestBody Credenziali cred) {
        Credenziali created = service.create(cred);
        return ResponseEntity.status(201).body(created);
    }//create


    /**
     * PUT /api/credentials/{id}
     * Aggiorna una credenziale esistente.
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Credenziali> update(@PathVariable Integer id, @RequestBody Credenziali cred) {
        Credenziali updated = service.update(id, cred);
        return ResponseEntity.ok().body(updated);
    }//update

    /**
     * DELETE /api/credentials/{id}
     * Elimina una credenziale per ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }//delete




}//CredenzialiController
