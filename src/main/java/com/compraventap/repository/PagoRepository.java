package com.compraventap.repository;

import com.compraventap.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // Este método te servirá para ver todos los abonos de un contrato específico
    List<Pago> findByContratoIdContrato(Integer idContrato);
}