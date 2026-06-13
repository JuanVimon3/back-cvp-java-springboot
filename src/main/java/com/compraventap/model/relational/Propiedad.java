package com.compraventap.model.relational; 

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "propiedad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Integer idPropiedad;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "precio")
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false)
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

    // @Column(name = "image")
    // private String image;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "propiedad_images", joinColumns = @JoinColumn(name = "id_propiedad"))
    @Column(name = "image_url")
    private List<String> images;

    @Column(name = "type")
    private String type;
}