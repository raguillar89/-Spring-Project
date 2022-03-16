package com.example.deva.deva.service;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Funcionario;
import com.example.deva.deva.repository.EmpresaRepository;
import com.example.deva.deva.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EmpresaService empresaService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public boolean existsByEndFunc(String endFunc) {
        return funcionarioRepository.existsByEndFunc(endFunc);
    }
    public boolean existsByCpfFunc(String cpfFunc) {
        return funcionarioRepository.existsByCpfFunc(cpfFunc);
    }
    public boolean existsByTelFunc(String telFunc) {
        return funcionarioRepository.existsByTelFunc(telFunc);
    }

    public List<Funcionario> findAllFunc() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findByIdFunc(Long idFunc){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFunc);
        return funcionario.get();
    }

    public List<Funcionario> FindFuncEmp (Empresa empresa){
        return funcionarioRepository.findByEmpresa(empresa);
    }

    public Long CountFuncEmp (Empresa empresa){
        return funcionarioRepository.countByEmpresa(empresa);
    }

    public Funcionario updateFunc(Long idFunc, Funcionario funcionario){
        Funcionario funcionarioAtt = funcionarioRepository.findById(idFunc).get();
        funcionario.setNomeFunc(funcionario.getNomeFunc());
        funcionario.setSobrenomeFunc(funcionario.getSobrenomeFunc());
        funcionario.setCpfFunc(funcionario.getCpfFunc());
        funcionario.setEndFunc(funcionario.getEndFunc());
        funcionario.setTelFunc(funcionario.getTelFunc());
        funcionario.setSexoFunc(funcionario.getSexoFunc());
        funcionario.setDataContFunc(funcionario.getDataNascFunc());
        funcionario.setDataContFunc(funcionario.getDataContFunc());
        return funcionarioRepository.save(funcionarioAtt);
    }

    @Transactional
    public void deleteFunc(Long idFunc) {
        funcionarioRepository.deleteById(idFunc);
    }

}
