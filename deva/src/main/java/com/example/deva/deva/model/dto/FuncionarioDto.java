package com.example.deva.deva.model.dto;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Funcionario;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FuncionarioDto {

    @NotBlank
    private String nomeFunc;
    @NotBlank
    private String sobrenomeFunc;
    @NotBlank
    private String cpfFunc;
    @NotBlank
    private String endFunc;
    @NotBlank
    private String telFunc;
    @NotBlank
    private String sexoFunc;
    @NotNull
    private String dataNascFunc;
    @NotNull
    private String dataContFunc;
    @NotNull
    @ManyToOne
    private Empresa empresa;

    public Funcionario updateFunc(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFunc(nomeFunc);
        funcionario.setSobrenomeFunc(sobrenomeFunc);
        funcionario.setCpfFunc(cpfFunc);
        funcionario.setEndFunc(endFunc);
        funcionario.setTelFunc(telFunc);
        funcionario.setSexoFunc(sexoFunc);
        funcionario.setDataContFunc(dataNascFunc);
        funcionario.setDataContFunc(dataContFunc);
        return funcionario;
    }
}
