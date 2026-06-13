package com.compraventap.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraventap.model.relational.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Esto genera automáticamente: SELECT * FROM usuarios WHERE email = ?
    Optional<Usuario> findByEmail(String email);
}