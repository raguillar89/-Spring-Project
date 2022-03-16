package com.example.deva.deva.model.dto;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Fazenda;
import com.example.deva.deva.model.Grao;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FazendaDto {

    @NotBlank
    private String nomeFaz;
    @NotBlank
    private String endFaz;
    @NotNull
    private String ultimaColheita;
    @NotNull
    private Double estq;
    @NotNull
    @ManyToOne
    private Empresa empresa;
    @NotNull
    @ManyToOne
    private Grao grao;

    public Fazenda updateFaz(){
        Fazenda fazenda = new Fazenda();
        fazenda.setNomeFaz(nomeFaz);
        fazenda.setEndFaz(endFaz);
        fazenda.setUltimaColheita(ultimaColheita);
        fazenda.setEstq(estq);
        fazenda.setEmpresa(empresa);
        fazenda.setGrao(grao);
        return fazenda;
    }
}
