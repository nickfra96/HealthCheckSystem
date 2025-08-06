package com.example.healthchecksystem.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Contatti")
@Data
public class Contatti {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "livello")
    private String livello;

    @Column(name = "descrizione")
    private String descrizione;

}//Contatti
