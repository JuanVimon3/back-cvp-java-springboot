package com.compraventap.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraventap.model.relational.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>{}
