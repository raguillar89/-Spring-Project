package com.example.deva.deva.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FazendaDto2 {

    @NotBlank
    private Long idFaz;
    @NotBlank
    private String nomeFaz;
    @NotNull
    private String proximaColheita;
}
