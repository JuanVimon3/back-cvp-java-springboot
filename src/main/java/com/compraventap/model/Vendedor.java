package com.compraventap.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Vendedor")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVendedor;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    // private String telefono;
    // private String calificacion;
    
}
