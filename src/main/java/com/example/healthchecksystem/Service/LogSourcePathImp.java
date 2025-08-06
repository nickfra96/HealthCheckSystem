package com.example.healthchecksystem.Service;

import com.example.healthchecksystem.Entity.LogSource;
import com.example.healthchecksystem.Entity.LogSourcePath;
import com.example.healthchecksystem.Repository.LogSourcePathRepository;
import com.example.healthchecksystem.Repository.LogSourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LogSourcePathImp implements LogSourcePathInterface {

    private final LogSourcePathRepository logSourcePathRepository;
    private final LogSourceRepository logSourceRepository;

    @Autowired
    public LogSourcePathImp(
            LogSourcePathRepository logSourcePathRepository,
            LogSourceRepository logSourceRepository
    ) {
        this.logSourcePathRepository = logSourcePathRepository;
        this.logSourceRepository     = logSourceRepository;
    }


    @Override
    public List<LogSourcePath> listByLogSource(Integer logSourceId) {
        logSourceRepository.findById(logSourceId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "LogSource non trovato: " + logSourceId));
        return logSourcePathRepository.findByLogSourceId(logSourceId);
    }//listByLogSource

    @Override
    public LogSourcePath getById(Integer id) {
        return logSourcePathRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LogSourcePath non trovato: " + id));
    }

    @Override
    @Transactional
    public LogSourcePath create(LogSourcePath path) {

        if (path.getLogSource() == null || path.getLogSource().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Devi fornire l'id del LogSource");
        }


        Integer logSourceId = path.getLogSource().getId();
        LogSource parent = logSourceRepository.findById(logSourceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "LogSource non trovato con id: " + logSourceId));


        path.setLogSource(parent);
        return logSourcePathRepository.save(path);

    }//create

    @Override
    @Transactional
    public LogSourcePath update(Integer id, LogSourcePath path) {

        LogSourcePath existing = logSourcePathRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "LogSourcePath non trovato: " + id));

        existing.setPath(path.getPath());
        return logSourcePathRepository.save(existing);
    }//update

    @Override
    @Transactional
    public void delete(Integer id) {

        // Verifico che esista il record
        if (!logSourcePathRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "LogSourcePath non trovato: " + id);
        }
        // Rimuovo definitivamente
        logSourcePathRepository.deleteById(id);

    }//delete
}//LogSourcePathImp
