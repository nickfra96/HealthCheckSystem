package Controller;


import Entity.LogSourcePath;
import Service.LogSourcePathImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/logsource-paths", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogSourcePathController {

    private final LogSourcePathImp service;

    @Autowired
    public LogSourcePathController(LogSourcePathImp service) {
        this.service = service;
    }


    /**
     * GET /api/logsource-paths/logsource/{logSourceId}
     * Restituisce tutti i LogSourcePath per uno specifico LogSource.
     */
    @GetMapping(path = "/logsource/{logSourceId}")
    public ResponseEntity<List<LogSourcePath>> listByLogSource(@PathVariable Integer logSourceId) {
        List<LogSourcePath> list = service.listByLogSource(logSourceId);
        return ResponseEntity.ok().body(list); //http 200
    }//listByLogSource

    /**
     * GET /api/logsource-paths/{id}
     * Restituisce un singolo LogSourcePath per ID.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<LogSourcePath> getById(@PathVariable Integer id) {
        LogSourcePath path = service.getById(id);
        return ResponseEntity.ok().body(path);
    }//getById

    /**
     * POST /api/logsource-paths
     * Crea un nuovo LogSourcePath.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LogSourcePath> create(@RequestBody LogSourcePath path) {
        LogSourcePath created = service.create(path);
        return ResponseEntity.status(201).body(created);   // HTTP 201 Created

    }//create


    /**
     * PUT /api/logsource-paths/{id}
     * Modifica un LogSourcePath esistente.
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogSourcePath> update(@PathVariable Integer id, @RequestBody LogSourcePath path) {
        LogSourcePath updated = service.update(id, path);
        return ResponseEntity.ok().body(updated);   // HTTP 200

    }//update


    /**
     * DELETE /api/logsource-paths/{id}
     * Elimina un LogSourcePath per ID.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content

    }//delete




}//LogSourcePathController
