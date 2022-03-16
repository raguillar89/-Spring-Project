package com.example.deva.deva.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "funcionario")
@Setter
@Getter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFunc;
    @Column(nullable = false, unique = false, length = 20, name = "nomeFunc")
    private String nomeFunc;
    @Column(nullable = false, unique = false, length = 20, name = "sobrenomeFunc")
    private String sobrenomeFunc;
    @CPF
    @Pattern(regexp = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
    @Column(nullable = false, unique = true, length = 11, name = "cpfFunc")
    private String cpfFunc;
    @Column(nullable = false, unique = true, length = 40, name = "endFunc")
    private String endFunc;
    @Pattern(regexp = "\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{7}$")
    @Column(nullable = false, unique = true, length = 16, name = "telFunc")
    private String telFunc;
    @Column(nullable = false, unique = false, length = 9, name = "sexoFunc")
    private String sexoFunc;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(nullable = false, unique = false, length = 10, name = "dataNascFunc")
    private String dataNascFunc;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(nullable = false, unique = false, length = 10, name = "dataContFunc")
    private String dataContFunc;
    @ManyToOne
    private Empresa empresa;

    public Funcionario(){ }

    public Funcionario(String nomeFunc, String sobrenomeFunc, String cpfFunc, String endFunc, String telFunc, String sexoFunc, String dataNascFunc, String dataContFunc){
        super();
        this.nomeFunc = nomeFunc;
        this.sobrenomeFunc = sobrenomeFunc;
        this.cpfFunc = cpfFunc;
        this.endFunc = endFunc;
        this.telFunc = telFunc;
        this.sexoFunc = sexoFunc;
        this.dataNascFunc = dataNascFunc;
        this.dataContFunc = dataContFunc;
    }
}
