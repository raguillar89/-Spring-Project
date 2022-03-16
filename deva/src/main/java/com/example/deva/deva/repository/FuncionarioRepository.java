package com.example.deva.deva.repository;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    boolean existsByEndFunc(String endFunc);
    boolean existsByCpfFunc(String cpfFunc);
    boolean existsByTelFunc(String telFunc);
    List<Funcionario> findByEmpresa(Empresa empresa);
    Long countByEmpresa(Empresa empresa);

}
