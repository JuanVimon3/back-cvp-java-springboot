package com.compraventap.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compraventap.model.relational.Pago;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // Este método te servirá para ver todos los abonos de un contrato específico
    List<Pago> findByContratoIdContrato(Integer idContrato);
}