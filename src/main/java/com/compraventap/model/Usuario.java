package com.compraventap.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;




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
    @Column(unique = true, nullable = false)
    private String email;
    private String nombre;
    private String password; 


    
}