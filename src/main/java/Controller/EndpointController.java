package Controller;

import Entity.Endpoint;
import Service.EndpointInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
public class EndpointController {


    private final EndpointInterface service;

    @Autowired
    public EndpointController(EndpointInterface service) {
        this.service = service;
    }//EndpointController


    /**
     * GET /api/endpoints
     * Restituisce tutti gli endpoint.
     */
    @GetMapping
    public ResponseEntity<List<Endpoint>> listAll() {
        List<Endpoint> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }//listAll


    /**
     * GET /api/endpoints/service/{serviceId}
     * Restituisce tutti gli endpoint per uno specifico Service.
     */
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Endpoint>> listByService(@PathVariable String serviceId) {
        List<Endpoint> list = service.listByService(serviceId);
        return ResponseEntity.ok().body(list);
    }//listByService

    /**
     * GET /api/endpoints/{id}
     * Restituisce un singolo endpoint per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Endpoint> getById(@PathVariable Integer id) {
        Endpoint ep = service.getById(id);
        return ResponseEntity.ok().body(ep);
    }//getById

    /**
     * POST /api/endpoints
     * Crea un nuovo endpoint.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Endpoint> create(@RequestBody Endpoint endpoint) {
        Endpoint created = service.create(endpoint);
        return ResponseEntity.status(201).body(created);
    }//create

    /**
     * PUT /api/endpoints/{id}
     * Aggiorna un endpoint esistente.
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endpoint> update(@PathVariable Integer id, @RequestBody Endpoint endpoint) {
        Endpoint updated = service.update(id, endpoint);
        return ResponseEntity.ok().body(updated);
    }//update

    /**
     * DELETE /api/endpoints/{id}
     * Elimina un endpoint per ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }//delete



}//EndpointController
