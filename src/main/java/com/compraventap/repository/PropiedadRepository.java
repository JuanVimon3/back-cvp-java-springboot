package com.compraventap.repository;

import com.compraventap.model.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Integer> {

    // Método para encontrar propiedades por el ID del vendedor
    List<Propiedad> findByVendedorUsuarioIdUsuario(Integer idUsuario);
}