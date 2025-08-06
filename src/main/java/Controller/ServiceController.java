package Controller;


import Entity.Service;
import Service.ServiceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceServiceImp serviceServiceImp;

    @Autowired
    public ServiceController(ServiceServiceImp serviceServiceImp) {
        this.serviceServiceImp = serviceServiceImp;
    }//ServiceController


    /**
     * GET /api/services
     * Se non passiamo parametri restituisce tutti i servizi,
     * altrimenti applica i filtri.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Service>> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String env,
            @RequestParam(required = false) String owner,
            @RequestParam(required = false) Boolean disabled
    ) throws URISyntaxException {
        List<Service> result;
        if (name == null && env == null && owner == null && disabled == null) {
            result = serviceServiceImp.listAll();
        } else {
            result = serviceServiceImp.list(name, env, owner, disabled);
        }
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET /api/services/{id}
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Service> getById(@PathVariable String id) throws RuntimeException{
        Service svc = serviceServiceImp.getById(id);
        return ResponseEntity.ok().body(svc);
    }

    /**
     * POST /api/services
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Service> create(@RequestBody Service service) throws RuntimeException {
        Service created = serviceServiceImp.create(service);
        // HTTP 201
        return ResponseEntity.status(201).body(created);
    }//create

    /**
     * PUT /api/services/{id}
     */
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Service> update(@PathVariable String id, @RequestBody Service service) throws RuntimeException {
        Service updated = serviceServiceImp.update(id, service);
        return ResponseEntity.ok().body(updated);
    }//update


    /**
     * DELETE /api/services/{id}
     * Soft-delete: segna lo stato = true e ritorna 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disable(@PathVariable String id) throws RuntimeException {
        serviceServiceImp.disable(id);
        // HTTP 204
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/dependencies")
    public Set<Service> dependencies(@PathVariable String id) {
        return serviceServiceImp.getDependencies(id);
    }//dependencies

    @PostMapping("/{id}/dependencies/{depId}")
    public ResponseEntity<Void> addDependency(@PathVariable String id, @PathVariable String depId) {
        serviceServiceImp.addDependency(id, depId);
        return ResponseEntity.noContent().build();
    }//addDependency


}//ServiceController
