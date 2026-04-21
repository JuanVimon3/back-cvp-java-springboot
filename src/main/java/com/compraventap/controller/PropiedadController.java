package com.compraventap.controller;


import com.compraventap.model.Propiedad;
import com.compraventap.repository.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/propiedades") // Define la ruta base para los endpoints de esta clase
@CrossOrigin(originPatterns = "*") // Permite solicitudes desde cualquier origen
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    // Endpoint para listar todas las propiedades
    @GetMapping
    public List <Propiedad> listarTodas() {
        return propiedadRepository.findAll();
    }

    //Endpoint para listar propiedad por ID
    @GetMapping("/{id}")
    public Propiedad obtenerAdministradorPorId(@PathVariable Integer id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    // Endpoint para agregar una nueva propiedad
    @PostMapping
    public Propiedad guardaPropiedad(@RequestBody Propiedad propiedad){
        return propiedadRepository.save(propiedad);
    }

    //Endpoint para actualizar una propiedad existente
   @PutMapping("/{id}")
    public Propiedad actualizarPropiedad(@PathVariable int id, @RequestBody Propiedad detallesPropiedad) {
        return propiedadRepository.findById(id)
                .map(propiedad -> {
                    propiedad.setUbicacion(detallesPropiedad.getUbicacion());
                    propiedad.setPrecio(detallesPropiedad.getPrecio());
                    // No olvides actualizar otros campos si los agregas
                    return propiedadRepository.save(propiedad);
                }).orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
    }

    // Endpoint para eliminar una propiedad
    @DeleteMapping("/{id}")
    public String eliminarPropiedad(@PathVariable int id){
        propiedadRepository.deleteById(id);
        return "Propiedad eliminada con id: " + id + "eliminada exitosamente"; 
    }
    
}
