package com.example.deva.deva.controller;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Fazenda;
import com.example.deva.deva.model.Grao;
import com.example.deva.deva.model.dto.FazendaDto;
import com.example.deva.deva.model.dto.FazendaDto2;
import com.example.deva.deva.model.dto.FazendaDto3;
import com.example.deva.deva.repository.FazendaRepository;
import com.example.deva.deva.service.FazendaService;
import com.example.deva.deva.service.EmpresaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fazenda")
public class FazendaController {

    @Autowired
    private FazendaService fazendaService;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private FazendaRepository fazendaRepository;

    public FazendaController(FazendaService fazendaService) {
        this.fazendaService = fazendaService;
    }

    @PostMapping //Usado para registrar uma nova Fazenda
    public ResponseEntity<Object> saveFazenda(@RequestBody @Valid FazendaDto fazendaDto){
        if(fazendaService.existsByEndFaz(fazendaDto.getEndFaz())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este endereço está cadastro em outra Fazenda!");
        }
        var fazenda = new Fazenda();
        BeanUtils.copyProperties(fazendaDto, fazenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(fazendaService.save(fazenda));
    }

    @GetMapping //Usado para buscar todas as Fazendas existentes
    public ResponseEntity<List<Fazenda>> findAllFaz(){
        List<Fazenda> listFaz = fazendaService.findAllFaz();
        return ResponseEntity.ok().body(listFaz);
    }

    @GetMapping("/{idFaz}") //Usado para buscar os dados das Fazendas pelo seu ID
    public ResponseEntity<Fazenda> findByIdFaz(@PathVariable Long idFaz){
        Fazenda fazenda = fazendaService.findByIdFaz(idFaz);
        return ResponseEntity.ok().body(fazenda);
    }

    @GetMapping("/fazemplist/{idEmp}") //Usado para buscar a listagem das Fazendas de uma Empresa
    public ResponseEntity<List<Fazenda>> FindFazEmp(@PathVariable Long idEmp){
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(fazendaService.FindFazEmp(empresa));
    }

    @GetMapping("/proxcolfazemp/{idEmp}") //Usado para calcular e mostrar a data da proxima colheita dos Grãos das Fazendas de cada Empresa
    public ResponseEntity<List<FazendaDto2>> FindProxColFazEmp(@PathVariable Long idEmp) {
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        List<FazendaDto2> proxColFazEmp = new ArrayList<FazendaDto2>();

        for (Fazenda fazenda : fazendaService.FindFazEmp(empresa)) {
            FazendaDto2 listProxCol = new FazendaDto2();

            listProxCol.setIdFaz(fazenda.getIdFaz());
            listProxCol.setNomeFaz(fazenda.getNomeFaz());

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println(fazenda.getProximaColheita());

            LocalDate ultCol = LocalDate.parse(fazenda.getUltimaColheita(), format);
            Grao grao = fazenda.getGrao();

            LocalDate proximaColheita = ultCol.plusDays(grao.getTempoColheita());
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(proximaColheita);

            String dataColheita = proximaColheita.toString();
            listProxCol.setProximaColheita(dataColheita);
            proxColFazEmp.add(listProxCol);
        }
        return ResponseEntity.ok().body(proxColFazEmp);
    }

    @GetMapping("/listgremp/{idEmp}") //Usado para buscar a listagem de Fazendas de uma Empresa por ordem de quantidade de estoque
    public ResponseEntity<List<FazendaDto3>> listGrEmp(@PathVariable Long idEmp){
        List<Fazenda> listFaz = fazendaService.findByEmpresaIdd(idEmp);
        List<FazendaDto3> listFaz3 = listFaz.stream().map(objFaz -> new FazendaDto3(objFaz)).sorted(Comparator.comparing(FazendaDto3::getEstq).reversed()).collect(Collectors.toList());
        return ResponseEntity.ok().body(listFaz3);
    }

    @GetMapping("/fazempcount/{idEmp}") //Usado para buscar a quantidade de Fazendas de uma Empresa
    public ResponseEntity<Long> CountFazEmp(@PathVariable Long idEmp) {
        Empresa empresa = empresaService.findByIdEmp(idEmp);
        return ResponseEntity.ok().body(fazendaService.CountFazEmp(empresa));
    }

    @DeleteMapping("/{idFaz}") //Usado para deletar uma Fazenda
    public void deleteFaz(@PathVariable Long idFaz){
        fazendaService.deleteFaz(idFaz);
    }

    @PutMapping("/{idFaz}") //Usado para atualizar os dados da Fazenda
    public Fazenda updateFaz(@PathVariable Long idFaz, @RequestBody FazendaDto fazendaDto){
        return fazendaService.updateFaz(idFaz, fazendaDto.updateFaz());
    }

    @PutMapping("/fazColInsert/{idFaz}/{insertEstq}") //Adicionar Estoque - Inserir valor a ser inserido no lugar do {insertEstq}
    public ResponseEntity<Fazenda> registracolheita(@PathVariable @Valid Long idFaz, @PathVariable Double insertEstq){
        Fazenda fazenda = fazendaService.findByIdFaz(idFaz);
        Double novoEstqInsert = fazenda.getEstq();
        fazenda.setEstq(novoEstqInsert + insertEstq);
        Fazenda attEmpInsert = fazendaService.updateFaz(idFaz, fazenda);
        return ResponseEntity.ok().body(attEmpInsert);
    }

    @PutMapping("/fazColRemove/{idFaz}/{removeEstq}") //Remove Estoque - Inserir valor a ser retirado no lugar o {removeEstq}
    public ResponseEntity<Fazenda> registravenda(@PathVariable @Valid Long idFaz, @PathVariable Double removeEstq){
        Fazenda fazenda = fazendaService.findByIdFaz(idFaz);
        Double novoEstqRemove = fazenda.getEstq();
        fazenda.setEstq(novoEstqRemove - removeEstq);
        Fazenda attEmpRemove = fazendaService.updateFaz(idFaz, fazenda);
        return ResponseEntity.ok().body(attEmpRemove);
    }
}
