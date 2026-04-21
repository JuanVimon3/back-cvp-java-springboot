package com.compraventap.controller;


import com.compraventap.model.Pago;
import com.compraventap.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(originPatterns = "*")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pago obtenerPagoPorId(@PathVariable Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pago guardarPago(@RequestBody Pago pago) {
        return pagoRepository.save(pago);
    }

    @GetMapping("/contrato/{id}")
    public List<Pago> listarPorContrato(@PathVariable Integer id) {
        return pagoRepository.findByContratoIdContrato(id);
    }
} 
