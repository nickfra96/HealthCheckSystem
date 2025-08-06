package com.example.healthchecksystem.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "LogSource")
@Data
public class LogSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(name = "channel")
    private String channel;

    @Column(name = "auth_required")
    private boolean auth_required;

    @Column(name = "auth_ref")
    private Integer auth_ref;

    @Column(name = "format")
    private String format;


    @Column(name = "endpoint")
    private String endpoint;

    @OneToMany(mappedBy = "logSource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogSourcePath> paths;














}//LogSource
