package com.besidetech.aims.service;

import com.besidetech.aims.entity.LogSourcePath;

import java.util.List;

public interface LogSourcePathInterface {

    List<LogSourcePath> listByLogSource(Integer logSourceId);
    LogSourcePath getById(Integer id);
    LogSourcePath create(LogSourcePath path);
    LogSourcePath update(Integer id, LogSourcePath path);
    void delete(Integer id);


}//LogSourcePathInterface
