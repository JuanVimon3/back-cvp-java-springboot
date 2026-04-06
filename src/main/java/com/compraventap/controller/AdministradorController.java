package com.compraventap.controller;

import com.compraventap.model.Administrador;
import com.compraventap.repository.AdministradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping
    public List <Administrador> listarTodos(){
        return administradorRepository.findAll();
    }

    @PostMapping
    public Administrador guardarAdministrador(@RequestBody Administrador administrador){
        return administradorRepository.save(administrador);
    }

    //Endpoint para actualizar un administrador existente
    @PutMapping("/{id}")
    public Administrador actualizAdministrador(@PathVariable int id, @RequestBody Administrador detalleAdministrador){
        return administradorRepository.findById(id)
        .map(administrador -> {
            administrador.setNivelAcceso(detalleAdministrador.getNivelAcceso());
            administrador.setUsuario(detalleAdministrador.getUsuario());
            return administradorRepository.save(administrador);
        }).orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));
    }

    //Endpoint para eliminar un administrador
    @DeleteMapping("/{id}")
    public String eliminarAdministrador(@PathVariable int id){
         administradorRepository.deleteById(id);
         return "Administrador con id: " + id + " eliminado exitosamente";
    }
}
