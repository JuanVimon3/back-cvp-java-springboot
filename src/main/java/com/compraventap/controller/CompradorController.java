package com.compraventap.controller;


import com.compraventap.model.Comprador;
import com.compraventap.repository.CompradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compradores")
@CrossOrigin(origins = "*")
public class CompradorController {
    
    @Autowired
    private CompradorRepository compradorRepository;

    @GetMapping
    public List<Comprador> listarTodos(){
        return compradorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comprador obtenerAdministradorPorId(@PathVariable Integer id) {
        return compradorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Comprador guardarComprador(@RequestBody Comprador comprador){
        return compradorRepository.save(comprador);
    }

    //Endpoint para actualizar un comprador existente
     @PutMapping("/{id}")
    public Comprador actualizarComprador(@PathVariable int id, @RequestBody Comprador detalleComprador){
        return compradorRepository.findById(id)
        .map(comprador -> {
            comprador.setUsuario(detalleComprador.getUsuario());
            return compradorRepository.save(comprador);
        }).orElseThrow(() -> new RuntimeException("Comprador no encontrado con id: " + id));
    }

    //Endpoint paraq eliminar un comprador
    @DeleteMapping("/{id}")
    public String eliminarComprador(@PathVariable int id){
        compradorRepository.deleteById(id);
        return "Comprador con id: " + id + " eliminado exitosamente";
    }
}
