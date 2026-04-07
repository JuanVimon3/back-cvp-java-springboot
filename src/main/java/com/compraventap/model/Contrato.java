package com.compraventap.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contrato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContrato;

    //Funciones propias del contrato
    private LocalDate fechaContrato;
    private Double montoTotal;
    private String estadoContrato;

    @ManyToOne
    @JoinColumn(name = "idVendedor", nullable = false)
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "idComprador", nullable = false)
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "idPropiedad", nullable = false)
    private Propiedad propiedad;


}
