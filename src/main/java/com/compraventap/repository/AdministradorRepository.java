package com.compraventap.repository;


import com.compraventap.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    //Agregar búsquedas personalizadas 
}
