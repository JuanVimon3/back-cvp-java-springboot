package com.compraventap.controller;



import com.compraventap.model.Vendedor;
import com.compraventap.repository.VendedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@CrossOrigin(originPatterns = "*")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping
    public List<Vendedor> listarTodos(){
        return vendedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vendedor obtenerAdministradorPorId(@PathVariable Integer id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Vendedor guardarVendedor(@RequestBody Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

    //Endpoint para actualizar un vendedor existente
    @PutMapping("/{id}")
    public Vendedor actualizarVendedor(@PathVariable int id, @RequestBody Vendedor detalleVendedor){
        return vendedorRepository.findById(id)
        .map(vendedor -> {
            vendedor.setUsuario(detalleVendedor.getUsuario());
            return vendedorRepository.save(vendedor);
        }).orElseThrow(() -> new RuntimeException("Vendedor no encontrado con id: " + id));
    }

    //Endpoint para eliminar un vendedor
    @DeleteMapping("/{id}")
    public String eliminarVendedor(@PathVariable int id){
        vendedorRepository.deleteById(id);
        return "Vendedor con id: " + id + "eliminado exitosamente";
    }
    
}
