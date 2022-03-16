package com.example.deva.deva.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@EqualsAndHashCode
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmp;
    @Column(nullable = false, unique = true, length = 30, name = "nomeEmp")
    private String nomeEmp;
    @CNPJ
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    @Column(nullable = false, unique = true, length = 18, name = "cnpj")
    private String cnpj;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(nullable = false, unique = false, length = 10, name = "fundacaoEmp")
    private String fundacaoEmp;

    public Empresa(){ }

    public Empresa(Long idEmp, String nomeEmp, String cnpj, String fundacaoEmp){
        super();
        this.idEmp = idEmp;
        this.nomeEmp = nomeEmp;
        this.cnpj = cnpj;
        this.fundacaoEmp = fundacaoEmp;
    }
}
