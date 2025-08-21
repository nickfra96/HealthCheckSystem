package com.besidetech.aims.controller;


import com.besidetech.aims.dto.ApiException;
import com.besidetech.aims.entity.Service;
import com.besidetech.aims.service.ServiceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/checks")
public class ChecksController {

    private final ServiceServiceImp serviceServiceImp;

    @Autowired
    public ChecksController(ServiceServiceImp serviceServiceImp) {
        this.serviceServiceImp = serviceServiceImp;
    }


    /**
     * GET /api/health
     */
    @GetMapping(value = "/health")
    public ResponseEntity<Void> health() throws Exception {
        return ResponseEntity.ok().build();
    }

    /**
     * GET /api/performance
     */
    @GetMapping(value = "/performance")
    public ResponseEntity<Void> performance() throws RuntimeException{
        serviceServiceImp.listAll();
        return ResponseEntity.ok().build();
    }

}
