package com.example.deva.deva.repository;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FazendaRepository extends JpaRepository<Fazenda, Long> {

    boolean existsByEndFaz(String endFaz);
    List<Fazenda> findByEmpresa(Empresa empresa);
    Long countByEmpresa(Empresa empresa);
    List<Fazenda> findByEmpresaIdEmp(Long idEmp);
}
