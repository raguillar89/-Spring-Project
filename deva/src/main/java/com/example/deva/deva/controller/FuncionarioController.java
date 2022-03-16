package com.example.deva.deva.controller;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Funcionario;
import com.example.deva.deva.model.dto.FuncionarioDto;
import com.example.deva.deva.service.EmpresaService;
import com.example.deva.deva.service.FuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private EmpresaService empresaService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping //Usado para registrar um novo Funcionário
    public ResponseEntity<Object> saveFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto){
        if(funcionarioService.existsByEndFunc(funcionarioDto.getEndFunc())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Endereço já existente!");
        }
        if(funcionarioService.existsByCpfFunc(funcionarioDto.getCpfFunc())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já existente!");
        }
        if(funcionarioService.existsByTelFunc(funcionarioDto.getTelFunc())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Telefone já existente!");
        }
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionario));
    }

    @GetMapping //Usado para buscar todos os Funcionários existentes
    public ResponseEntity<List<Funcionario>> findAllFunc(){
        List<Funcionario> listFunc = funcionarioService.findAllFunc();
        return ResponseEntity.ok().body(listFunc);
    }

    @GetMapping("/{idFunc}") //Usado para buscar os dados dos Funcionários pelo seu ID
    public ResponseEntity<Funcionario> findByIdFunc(@PathVariable Long idFunc){
        Funcionario funcionario = funcionarioService.findByIdFunc(idFunc);
        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping("/funcemplist/{idEmp}") //Usado para buscar a listagem de Funcionários de uma Empresa
    public ResponseEntity<List<Funcionario>> FindFuncEmp(@PathVariable Long idEmp) {
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(funcionarioService.FindFuncEmp(empresa));
    }

    @GetMapping("/funcempcount/{idEmp}") //Usado para buscar a quantidade de Funcionários de uma Empresa
    public ResponseEntity<Long> CountFuncEmp(@PathVariable Long idEmp) {
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(funcionarioService.CountFuncEmp(empresa));
    }

    @DeleteMapping("/{idFunc}") //Usado para deletar um Funcionário
    public void deleteFunc(@PathVariable Long idFunc){
        funcionarioService.deleteFunc(idFunc);
    }

    @PutMapping("/{idFunc}") //Usado para atualizar os dados do Funcionário
    public Funcionario updateFunc(@PathVariable Long idFunc, @RequestBody FuncionarioDto funcionarioDto){
        return funcionarioService.updateFunc(idFunc, funcionarioDto.updateFunc());
    }
}
