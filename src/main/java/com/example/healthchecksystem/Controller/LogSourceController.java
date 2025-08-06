package com.example.healthchecksystem.Controller;


import com.example.healthchecksystem.Entity.LogSource;
import com.example.healthchecksystem.Service.LogSourceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/logsources", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogSourceController {

    private final LogSourceInterface service;

    @Autowired
    public LogSourceController(LogSourceInterface service) {
        this.service = service;
    }//LogSourceController


    /**
     * GET /api/logsources
     * Restituisce tutti i LogSource.
     */
    @GetMapping
    public ResponseEntity<List<LogSource>> listAll() {
        List<LogSource> list = service.listAll();
        return ResponseEntity.ok().body(list);  // HTTP 200

    }//listAll

    /**
     * GET /api/logsources/service/{serviceId}
     * Restituisce tutti i LogSource per uno specifico service.
     */
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<LogSource>> listByService(@PathVariable String serviceId) {
        List<LogSource> list = service.listByService(serviceId);
        return ResponseEntity.ok().body(list);
    }//listByService

    /**
     * GET /api/logsources/{id}
     * Restituisce un singolo LogSource per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LogSource> getById(@PathVariable Integer id) {
        LogSource ls = service.getById(id);
        return ResponseEntity.ok().body(ls);
    }//getById

    /**
     * POST /api/logsources
     * Crea un nuovo LogSource.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogSource> create(@RequestBody LogSource logSource) {
        LogSource created = service.create(logSource);
        return ResponseEntity.status(201).body(created);  // HTTP 201 Created

    }//create

    /**
     * PUT /api/logsources/{id}
     * Aggiorna un LogSource esistente.
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LogSource> update(@PathVariable Integer id, @RequestBody LogSource logSource) {
        LogSource updated = service.update(id, logSource);
        return ResponseEntity.ok().body(updated);
    }//update

    /**
     * DELETE /api/logsources/{id}
     * Elimina un LogSource per ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content

    }//delete

}//LogSourceController
