package com.compraventap.model; 

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private Integer idPropiedad;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "precio")
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "idVendedor", nullable = false)
    @JsonBackReference
    private Vendedor vendedor;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "area")
    private String area;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "image")
    private String image;

    @Column(name = "type")
    private String type;
}