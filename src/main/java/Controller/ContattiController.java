package Controller;


import Entity.Contatti;
import Service.ContattiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/contatti", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContattiController {

    private final ContattiInterface service;

    @Autowired
    public ContattiController(ContattiInterface service) {
        this.service = service;
    }//ContattiController


    /**
     * GET /api/contatti
     * Restituisce tutti i contatti.
     */
    @GetMapping
    public ResponseEntity<List<Contatti>> listAll() {
        List<Contatti> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }//listAll


    /**
     * GET /api/contatti/service/{serviceId}
     * Restituisce i contatti per uno specifico Service.
     */
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Contatti>> listByService(@PathVariable String serviceId) {
        List<Contatti> list = service.listByService(serviceId);
        return ResponseEntity.ok().body(list);
    }//listByService

    /**
     * GET /api/contatti/{id}
     * Restituisce un singolo contatto per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contatti> getById(@PathVariable String id) {
        Contatti cont = service.getById(id);
        return ResponseEntity.ok().body(cont);
    }//getById

    /**
     * POST /api/contatti
     * Crea un nuovo contatto.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Contatti> create(@RequestBody Contatti contatto) {
        Contatti created = service.create(contatto);
        return ResponseEntity.status(201).body(created);
    }//create


    /**
     * PUT /api/contatti/{id}
     * Aggiorna un contatto esistente.
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contatti> update(@PathVariable String id, @RequestBody Contatti contatto) {
        Contatti updated = service.update(id, contatto);
        return ResponseEntity.ok().body(updated);
    }//update

    /**
     * DELETE /api/contatti/{id}
     * Elimina un contatto per ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }//delete





}//ContattiController
