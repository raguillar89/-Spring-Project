package com.example.deva.deva.repository;

import com.example.deva.deva.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByNomeEmp(String nomeEmp);
    boolean existsByCnpj(String cnpj);

}
