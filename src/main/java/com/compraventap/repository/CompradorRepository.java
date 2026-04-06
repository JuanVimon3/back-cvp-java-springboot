package com.compraventap.repository;

import com.compraventap.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompradorRepository extends JpaRepository <Comprador, Integer> {
    // Se pueden generar búsquedas personalizadas más adelante
} 
