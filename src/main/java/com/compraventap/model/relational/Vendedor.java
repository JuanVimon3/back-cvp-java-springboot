package com.compraventap.model.relational;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vendedor")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Propiedad> propiedades;

    // private String telefono;
    // private String calificacion;
    
}
