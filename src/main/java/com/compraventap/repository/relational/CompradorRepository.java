package com.compraventap.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraventap.model.relational.Comprador;

@Repository
public interface CompradorRepository extends JpaRepository <Comprador, Integer> {
    // Se pueden generar búsquedas personalizadas más adelante
} 
