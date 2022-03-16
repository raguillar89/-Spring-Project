package com.example.deva.deva.service;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Grao;
import com.example.deva.deva.repository.EmpresaRepository;
import com.example.deva.deva.repository.GraoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GraoService {

    @Autowired
    private GraoRepository graoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EmpresaService empresaService;

    public GraoService(GraoRepository graoRepository) {
        this.graoRepository = graoRepository;
    }

    @Transactional
    public Grao save(Grao grao) {
        return graoRepository.save(grao);
    }

    public List<Grao> findAllGr() {
        return graoRepository.findAll();
    }

    public Grao findByIdGr(Long idGr){
        Optional<Grao> grao = graoRepository.findById(idGr);
        return grao.get();
    }

    public List<Grao> FindGraoEmp (Empresa empresa){
        return graoRepository.findByEmpresa(empresa);
    }

    public Long CountGraoEmp (Empresa empresa){
        return graoRepository.countByEmpresa(empresa);
    }

    public Grao updateGr(Long idGr, Grao grao){
        Grao grAtt = graoRepository.findById(idGr).get();
        grAtt.setNomeGrao(grao.getNomeGrao());
        grAtt.setTempoColheita(grao.getTempoColheita());
        grAtt.setEmpresa(grao.getEmpresa());
        return graoRepository.save(grAtt);
    }

    @Transactional
    public void deleteGr(Long idGr) {
        graoRepository.deleteById(idGr);
    }

}
