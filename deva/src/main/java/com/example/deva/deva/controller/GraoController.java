package com.example.deva.deva.controller;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Grao;
import com.example.deva.deva.model.dto.GraoDto;
import com.example.deva.deva.service.EmpresaService;
import com.example.deva.deva.service.GraoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gr")
public class GraoController {

    @Autowired
    private GraoService graoService;
    @Autowired
    private EmpresaService empresaService;

    public GraoController(GraoService graoService) {
        this.graoService = graoService;
    }

    @PostMapping //Usado para registrar uma novo Grão
    public ResponseEntity<Object> saveGrao(@RequestBody @Valid GraoDto graoDto){
        var grao = new Grao();
        BeanUtils.copyProperties(graoDto, grao);
        return ResponseEntity.status(HttpStatus.CREATED).body(graoService.save(grao));
    }

    @GetMapping //Usado para buscar todos os Grãos existentes
    public ResponseEntity<List<Grao>> findAllGr(){
        List<Grao> listGr = graoService.findAllGr();
        return ResponseEntity.ok().body(listGr);
    }

    @GetMapping("/{idGr}") //Usado para buscar os dados dos Grãos pelo seu ID
    public ResponseEntity<Grao> findByIdGr(@PathVariable Long idGr){
        Grao grao = graoService.findByIdGr(idGr);
        return ResponseEntity.ok().body(grao);
    }

    @GetMapping("/gremplist/{idEmp}") //Usado para buscar a listagem de Grãos de uma Empresa
    public ResponseEntity<List<Grao>> FindGraoEmp(@PathVariable Long idEmp) {
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(graoService.FindGraoEmp(empresa));
    }

    @GetMapping("/grempcount/{idEmp}") //Usado para buscar a quantidade de Grãos de uma Empresa
    public ResponseEntity<Long> CountGraoEmp(@PathVariable Long idEmp) {
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(graoService.CountGraoEmp(empresa));
    }

    @DeleteMapping("/{idGr}") //Usado para deletar um Grão
    public void deleteGr(@PathVariable Long idGr){
        graoService.deleteGr(idGr);
    }

    @PutMapping("/{idGr}") //Usado para atualizar os dados do Grão
    public Grao updateGr(@PathVariable Long idGr, @RequestBody GraoDto graoDto){
        return graoService.updateGr(idGr, graoDto.updateGr());
    }
}
