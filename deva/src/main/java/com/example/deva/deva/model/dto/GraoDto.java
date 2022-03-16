package com.example.deva.deva.model.dto;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Grao;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GraoDto {

    @NotBlank
    private String nomeGrao;
    @NotNull
    private Long tempoColheita;
    @NotNull
    @ManyToOne
    private Empresa empresa;

    public Grao updateGr(){
        Grao grao = new Grao();
        grao.setNomeGrao(nomeGrao);
        grao.setTempoColheita(tempoColheita);
        grao.setEmpresa(empresa);
        return grao;
    }
}
