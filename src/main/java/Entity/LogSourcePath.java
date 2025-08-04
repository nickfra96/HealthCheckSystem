package Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LogSourcePath")
@Data
public class LogSourcePath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "path")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private LogSource logSource;



}//LogSourcePath
