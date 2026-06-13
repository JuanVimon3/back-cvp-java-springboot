package com.compraventap.repository.relational;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraventap.model.relational.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    // Se pueden generar búsquedas personalizadas más adelante
    Optional<Vendedor> findByUsuarioIdUsuario(Integer idUsuario);
}