package com.compraventap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;



@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private Integer cedula;
    private String email;
    private String nombre;
    private String password; 

    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) // Relación uno a muchos con Propiedad. Si se borra un usuario, todas sus propiedades también lo harán
    @JsonIgnore // Evita bucles infinitos al listar propiedades
    private List<Propiedad> propiedades;
    
}