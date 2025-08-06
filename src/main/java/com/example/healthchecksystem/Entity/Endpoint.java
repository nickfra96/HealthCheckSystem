package com.example.healthchecksystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Endpoint")
@Data
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(name = "type", nullable = false, length = 16)
    private String type;

    @Column(name = "url", nullable = false, length = 2048)
    private String url;

    @Column(name = "method",nullable = false, length = 8)
    private String method;

    @Column(name = "expected_status", columnDefinition = "JSONB")
    private String expected_status;

    @Column(name = "expected_content_status", length = 64)
    private String expected_content_status;

    @Column(name = "protocollo", nullable = false)
    private String protocollo;

    @Column(name = "timeout_ms", nullable = false)
    private Integer timeoutMs;

    @Column(name = "porta")
    private Integer porta;


    //Relazioni

    //da controlalre ci vuole la one to many
    @OneToOne(mappedBy = "endpoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private Autenticazione autenticazione;


    @OneToMany(
            mappedBy = "endpoint",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CheckHistory> checkHistory;



}//Endpoint
