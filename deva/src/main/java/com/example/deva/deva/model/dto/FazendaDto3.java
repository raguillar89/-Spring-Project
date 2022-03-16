package com.example.deva.deva.model.dto;

import com.example.deva.deva.model.Fazenda;
import com.example.deva.deva.model.Grao;
import javax.validation.constraints.NotBlank;

public class FazendaDto3 {

    @NotBlank
    private Grao grao;
    @NotBlank
    private Double estq;

    private FazendaDto3(){ }

    public FazendaDto3(Fazenda fazenda) {
        this.grao = fazenda.getGrao();
        this.estq = fazenda.getEstq();
    }

    public String getGrao() {
        return grao.getNomeGrao();
    }
    public void setGrao(Grao grao) {
        this.grao = grao;
    }
    public Double getEstq() {
        return estq;
    }
    public void setEstq(Double estq) {
        this.estq = estq;
    }
}
