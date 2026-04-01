package com.compraventap.model; 

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Propiedad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPropiedad")
    private int idPropiedad;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "precio")
    private int precio;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}