package com.compraventap.model.relational;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @Column(name = "monto")
    private Integer monto;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "estado_pago")
    private String estadoPago;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

}
