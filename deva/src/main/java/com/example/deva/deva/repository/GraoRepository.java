package com.example.deva.deva.repository;

import com.example.deva.deva.model.Empresa;
import com.example.deva.deva.model.Grao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraoRepository extends JpaRepository<Grao, Long> {

    List<Grao> findByEmpresa(Empresa empresa);
    Long countByEmpresa(Empresa empresa);
}
