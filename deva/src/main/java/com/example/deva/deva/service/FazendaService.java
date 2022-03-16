package com.example.deva.deva.service;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Fazenda;
import com.example.deva.deva.repository.EmpresaRepository;
import com.example.deva.deva.repository.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FazendaService {

    @Autowired
    private FazendaRepository fazendaRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EmpresaService empresaService;

    public FazendaService(FazendaRepository fazendaRepository) {
        this.fazendaRepository = fazendaRepository;
    }

    @Transactional
    public Fazenda save(Fazenda fazenda) {
        return fazendaRepository.save(fazenda);
    }

    public boolean existsByEndFaz(String endFaz) {
        return fazendaRepository.existsByEndFaz(endFaz);
    }

    public List<Fazenda> findAllFaz() {
        return fazendaRepository.findAll();
    }

    public Fazenda findByIdFaz(Long idFaz){
        Optional<Fazenda> fazenda = fazendaRepository.findById(idFaz);
        return fazenda.get();
    }

    public List<Fazenda> findByEmpresaIdd(Long idEmp){
        return fazendaRepository.findByEmpresaIdEmp(idEmp);
    }

    public List<Fazenda> FindFazEmp (Empresa empresa){
        return fazendaRepository.findByEmpresa(empresa);
    }

    public List<Fazenda> FindProxColFazEmp (Empresa empresa){
        return fazendaRepository.findByEmpresa(empresa);
    }

    public Long CountFazEmp (Empresa empresa){
        return fazendaRepository.countByEmpresa(empresa);
    }

    public Fazenda updateFaz(Long idFaz, Fazenda fazenda){
        Fazenda fazendaAtt = fazendaRepository.findById(idFaz).get();
        fazendaAtt.setNomeFaz(fazenda.getNomeFaz());
        fazendaAtt.setEndFaz(fazenda.getEndFaz());
        fazendaAtt.setUltimaColheita(fazenda.getUltimaColheita());
        fazendaAtt.setEstq(fazenda.getEstq());
        fazendaAtt.setEmpresa(fazenda.getEmpresa());
        fazendaAtt.setGrao(fazenda.getGrao());
        return fazendaRepository.save(fazendaAtt);
    }

    @Transactional
    public void deleteFaz(Long idFaz) {
        fazendaRepository.deleteById(idFaz);
    }

}
