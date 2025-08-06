package com.example.healthchecksystem.Controller;

import com.example.healthchecksystem.Entity.Autenticazione;
import com.example.healthchecksystem.Service.AutenticazioneInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/autenticazioni", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutenticazioneController {


    private final AutenticazioneInterface service;

    @Autowired
    public AutenticazioneController(AutenticazioneInterface service) {
        this.service = service;
    }//AutenticazioneController


    /**
     * GET /api/autenticazioni
     * Restituisce tutte le autenticazioni.
     */
    @GetMapping
    public ResponseEntity<List<Autenticazione>> listAll() {
        List<Autenticazione> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }//listAll

    /**
     * GET /api/autenticazioni/{id}
     * Restituisce un’autenticazione per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autenticazione> getById(@PathVariable Integer id) {
        Autenticazione auth = service.getById(id);
        return ResponseEntity.ok().body(auth);
    }//getById

    /**
     * GET /api/autenticazioni/endpoint/{endpointId}
     * Restituisce l’autenticazione per un dato endpoint.
     */
    @GetMapping("/endpoint/{endpointId}")
    public ResponseEntity<Autenticazione> getByEndpoint(@PathVariable Integer endpointId) {
        Autenticazione auth = service.getByEndpoint(endpointId);
        return ResponseEntity.ok().body(auth);
    }//getByEndpoint

    /**
     * POST /api/autenticazioni
     * Crea una nuova autenticazione.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Autenticazione> create(@RequestBody Autenticazione auth) {
        Autenticazione created = service.create(auth);
        return ResponseEntity.status(201).body(created);
    }//create
    /**
     * PUT /api/autenticazioni/{id}
     * Aggiorna un’autenticazione esistente.
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Autenticazione> update(@PathVariable Integer id, @RequestBody Autenticazione auth) {
        Autenticazione updated = service.update(id, auth);
        return ResponseEntity.ok().body(updated);
    }//update

    /**
     * DELETE /api/autenticazioni/{id}
     * Elimina un’autenticazione per ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }//delete


}//AutenticazioneController
