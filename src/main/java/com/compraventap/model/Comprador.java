package com.compraventap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "`Comprador`") // Usar comillas para evitar problemas con mayúsculas
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComprador;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;
}
