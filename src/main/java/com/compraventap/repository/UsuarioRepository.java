package com.compraventap.repository;

import com.compraventap.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Esto genera automáticamente: SELECT * FROM usuarios WHERE email = ?
    Optional<Usuario> findByEmail(String email);
}