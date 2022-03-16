package com.example.deva.deva.model.dto;

import com.example.deva.deva.model.Empresa;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmpresaDto {

    @NotBlank
    private String nomeEmp;
    @NotBlank
    private String cnpj;
    @NotNull
    private String fundacaoEmp;

    public Empresa updateEmp(){
        Empresa empresa = new Empresa();
        empresa.setNomeEmp(nomeEmp);
        empresa.setCnpj(cnpj);
        empresa.setFundacaoEmp(fundacaoEmp);
        return empresa;
    }
}
