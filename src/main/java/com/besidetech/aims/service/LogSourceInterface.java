package com.besidetech.aims.service;

import com.besidetech.aims.entity.LogSource;

import java.util.List;

public interface LogSourceInterface {

    List<LogSource> listByService(String serviceId);
    LogSource getById(Integer id);
    LogSource create(LogSource logSource);
    LogSource update(Integer id, LogSource logSource);
    void delete(Integer id);
    List<LogSource> listAll();

}//LogSourceInterface
