package com.compraventap.controller;


import com.compraventap.model.Contrato;
import com.compraventap.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(originPatterns = "*")
public class ContratoController {
    
    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping
    public List<Contrato> listarTodos(){
        return contratoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contrato obtenerAdministradorPorId(@PathVariable Integer id) {
        return contratoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Contrato guardarContrato(@RequestBody Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    @PutMapping("/{id}")
        public Contrato actualizarContrato(@PathVariable int id, @RequestBody Contrato detalleContrato) {
            return contratoRepository.findById(id)
            .map(contrato -> {
                contrato.setFechaInicio(detalleContrato.getFechaInicio());
                contrato.setFechaFinal(detalleContrato.getFechaFinal());
                contrato.setMontoTotal(detalleContrato.getMontoTotal());
                contrato.setEstadoContrato(detalleContrato.getEstadoContrato());
                contrato.setVendedor(detalleContrato.getVendedor());
                contrato.setComprador(detalleContrato.getComprador());
                contrato.setPropiedad(detalleContrato.getPropiedad());
                return contratoRepository.save(contrato);
            }).orElseThrow(() -> new RuntimeException("Contrato no encontrado con id: " + id));
        }
    
    @DeleteMapping("/{id}")
    public String eliminarContrato(@PathVariable int id){
        contratoRepository.deleteById(id);
        return "Contrato con id: " + id + " eliminado exitosamente";
    }

}
