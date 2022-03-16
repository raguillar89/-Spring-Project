package com.example.deva.deva.service;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public boolean existsByNomeEmp(String nomeEmp) {
        return empresaRepository.existsByNomeEmp(nomeEmp);
    }
    public boolean existsByCnpj(String cnpj) {
        return empresaRepository.existsByCnpj(cnpj);
    }

    public List<Empresa> findAllEmp() {
        return empresaRepository.findAll();
    }

    public Empresa findByIdEmp(Long idEmp){
        Optional<Empresa> empresa = empresaRepository.findById(idEmp);
        return empresa.get();
    }

    public Empresa updateEmp(Long idEmp, Empresa empresa){
        Empresa empresaAtt = empresaRepository.findById(idEmp).get();
        empresaAtt.setNomeEmp(empresa.getNomeEmp());
        empresaAtt.setCnpj(empresa.getCnpj());
        empresaAtt.setFundacaoEmp(empresa.getFundacaoEmp());
        return empresaRepository.save(empresaAtt);
    }

    @Transactional
    public void deleteEmp(Long idEmp) {
        empresaRepository.deleteById(idEmp);
    }

}
