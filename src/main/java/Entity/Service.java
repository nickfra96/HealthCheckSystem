package Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "service")
@Data
public class Service {

    @Id
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "descrizione", columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "owner", length = 128)
    private String owner;

    @Column(name = "environment", nullable = false, length = 32)
    private String environment;

    @Column(length = 64)
    private String version;

    @Column(name = "stato", nullable = false)
    private boolean stato;


    // Relazioni
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endpoint> endpoints;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogSource> logSources;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contatti> contatti;


}//Entity
