package com.compraventap.model.relational;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdministrador;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    private String nivelAcceso;
}
