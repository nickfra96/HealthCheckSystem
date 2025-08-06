package com.example.healthchecksystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Autenticazione")
@Data
public class Autenticazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endpoint_id", nullable = false)
    private Endpoint endpoint;


    @Column(name ="tipo_auth")
    private String tipoAuth;

    @Column(name = "user_id")
    private Integer userId;

    // eventuali parametri addizionali
    @Column(name = "status")
    private String status;

    @UpdateTimestamp
    @Column(name = "created_at")
    private Instant  createdAt;

    @OneToMany(mappedBy = "autenticazione", cascade = CascadeType.ALL)
    private List<Credenziali> credenzialiList;



}//Autenticazione
