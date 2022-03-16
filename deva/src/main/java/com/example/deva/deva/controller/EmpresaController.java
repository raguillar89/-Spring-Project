package com.example.deva.deva.controller;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.dto.EmpresaDto;
import com.example.deva.deva.service.EmpresaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping //Usado para registrar uma nova Empresa
    public ResponseEntity<Object> saveEmpresa(@RequestBody @Valid EmpresaDto empresaDto){
        if(empresaService.existsByNomeEmp(empresaDto.getNomeEmp())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Empresa já existente!");
        }
        if(empresaService.existsByCnpj(empresaDto.getCnpj())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CNPJ já existente!");
        }
        var empresa = new Empresa();
        BeanUtils.copyProperties(empresaDto, empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresa));
    }

    @GetMapping //Usado para buscar todas as Empresas existentes
    public ResponseEntity<List<Empresa>> findAllEmp(){
        List<Empresa> listEmp = empresaService.findAllEmp();
        return ResponseEntity.ok().body(listEmp);
    }

    @GetMapping("/{idEmp}") //Usado para buscar os dados das Empresas pelo seu ID
    public ResponseEntity<Empresa> findByIdEmp(@PathVariable Long idEmp){
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(empresa);
    }

    @DeleteMapping("/{idEmp}") //Usado para deletar uma Empresa
    public void deleteEmp(@PathVariable Long idEmp){
        empresaService.deleteEmp(idEmp);
    }

    @PutMapping("/{idEmp}") //Usado para atualizar os dados da Empresa
    public Empresa updateEmp(@PathVariable Long idEmp, @RequestBody EmpresaDto empresaDto){
        return empresaService.updateEmp(idEmp, empresaDto.updateEmp());
    }
}
