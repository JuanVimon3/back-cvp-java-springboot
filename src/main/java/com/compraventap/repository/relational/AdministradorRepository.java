package com.compraventap.repository.relational;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraventap.model.relational.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    //Agregar búsquedas personalizadas 
}
