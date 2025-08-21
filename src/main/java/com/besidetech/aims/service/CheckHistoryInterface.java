package com.besidetech.aims.service;

import com.besidetech.aims.entity.CheckHistory;

import java.time.Instant;
import java.util.List;

public interface CheckHistoryInterface {

    List<CheckHistory> listAll();
    List<CheckHistory> listByEndpoint(Integer endpointId, Instant from, Instant to);
    List<CheckHistory> listFailuresByEndpoint(Integer endpointId, Instant from, Instant to);
    CheckHistory getLatestByEndpoint(Integer endpointId);
    List<CheckHistory> listGlobal(Instant from, Instant to);
    List<CheckHistory> listGlobalFailures(Instant from, Instant to);
    CheckHistory create(CheckHistory record);


}//CheckHistoryInterface
