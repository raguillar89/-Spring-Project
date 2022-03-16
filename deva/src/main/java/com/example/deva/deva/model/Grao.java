package com.example.deva.deva.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "grao")
@Setter
@Getter
public class Grao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrao;
    @Column(nullable = false, unique = true, length = 20, name = "nomeGrao")
    private String nomeGrao;
    @Column(name = "tempoColheita")
    private Long tempoColheita;
    @ManyToOne
    private Empresa empresa;

    public Grao(){ };

    public Grao(String nomeGrao, Long tempoColheita){
        super();
        this.nomeGrao = nomeGrao;
        this.tempoColheita = tempoColheita;
    }
}
