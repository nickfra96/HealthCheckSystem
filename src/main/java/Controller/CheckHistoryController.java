package Controller;

import Entity.CheckHistory;
import Service.CheckHistoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/api/checkhistory", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckHistoryController {


    private final CheckHistoryInterface service;

    @Autowired
    public CheckHistoryController(CheckHistoryInterface service) {
        this.service = service;
    }//CheckHistoryController


    /**
     * GET /api/checkhistory
     * Restituisce tutte le check history.
     */
    @GetMapping
    public ResponseEntity<List<CheckHistory>> listAll() {
        List<CheckHistory> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }//listAll

    /**
     * GET /api/checkhistory/endpoint/{endpointId}
     * Restituisce tutti gli eventi (success + failure) per un endpoint e intervallo.
     */
    @GetMapping("/endpoint/{endpointId}")
    public ResponseEntity<List<CheckHistory>> listByEndpoint(@PathVariable Integer endpointId, @RequestParam Instant from, @RequestParam Instant to) {
        List<CheckHistory> list = service.listByEndpoint(endpointId, from, to);
        return ResponseEntity.ok().body(list);
    }//listByEndpoint

    /**
     * GET /api/checkhistory/endpoint/{endpointId}/failures
     * Restituisce solo i failure per un endpoint e intervallo.
     */
    @GetMapping("/endpoint/{endpointId}/failures")
    public ResponseEntity<List<CheckHistory>> listFailuresByEndpoint(@PathVariable Integer endpointId, @RequestParam Instant from, @RequestParam Instant to) {
        List<CheckHistory> list = service.listFailuresByEndpoint(endpointId, from, to);
        return ResponseEntity.ok().body(list);
    }//listFailuresByEndpoint

    /**
     * GET /api/checkhistory/endpoint/{endpointId}/latest
     * Restituisce l'ultimo evento per un endpoint.
     */
    @GetMapping("/endpoint/{endpointId}/latest")
    public ResponseEntity<CheckHistory> getLatestByEndpoint(@PathVariable Integer endpointId) {
        CheckHistory latest = service.getLatestByEndpoint(endpointId);
        return ResponseEntity.ok().body(latest);
    }//getLatestByEndpoint

    /**
     * GET /api/checkhistory/global
     * Restituisce tutti gli eventi globali in un intervallo.
     */
    @GetMapping("/global")
    public ResponseEntity<List<CheckHistory>> listGlobal(@RequestParam Instant from, @RequestParam Instant to) {
        List<CheckHistory> list = service.listGlobal(from, to);
        return ResponseEntity.ok().body(list);
    }//listGlobal

    /**
     * GET /api/checkhistory/global/failures
     * Restituisce solo i failure globali in un intervallo.
     */
    @GetMapping("/global/failures")
    public ResponseEntity<List<CheckHistory>> listGlobalFailures(@RequestParam Instant from, @RequestParam Instant to) {
        List<CheckHistory> list = service.listGlobalFailures(from, to);
        return ResponseEntity.ok().body(list);
    }//listGlobalFailures

    /**
     * POST /api/checkhistory
     * Inserisce un nuovo CheckHistory.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CheckHistory> create(@RequestBody CheckHistory record) {
        CheckHistory created = service.create(record);
        return ResponseEntity.status(201).body(created);      // HTTP 201 Created

    }//create


}//CheckHistoryController
