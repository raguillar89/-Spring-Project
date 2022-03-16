package com.example.deva.deva.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "fazenda")
@Getter
@Setter
@EqualsAndHashCode
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaz;
    @Column(nullable = false, unique = true, length = 30, name = "nomeFaz")
    private String nomeFaz;
    @Column(nullable = false, unique = false, length = 40, name = "endFaz")
    private String endFaz;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "ultimaColheita")
    private String ultimaColheita;
    @Column(name = "estoqueInicial")
    private Double estq;
    @Transient
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "proximaColheita")
    private String proximaColheita;
    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Grao grao;

    public Fazenda(){ }

    public Fazenda(String nomeFaz, String endFaz, Double estq, Empresa empresa, Grao grao){
        super();
        this.nomeFaz = nomeFaz;
        this.endFaz = endFaz;
        this.estq = estq;
        this.empresa = empresa;
        this.grao = grao;
    }
}
