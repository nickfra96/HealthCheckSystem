package com.example.healthchecksystem.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Credenziali")
@Data
public class Credenziali {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password", columnDefinition = "text")
    private String password;

    @Column(name = "token", columnDefinition = "text")
    private String token;

    @Column(name = "certificate", columnDefinition = "text")
    private String certificate;

    @ManyToOne
    @JoinColumn(name = "auth_id")
    private Autenticazione autenticazione;













}//Credenziali
