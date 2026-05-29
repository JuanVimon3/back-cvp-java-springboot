package com.compraventap.controller;

import com.compraventap.model.Propiedad;
import com.compraventap.model.Vendedor;
import com.compraventap.model.Usuario;
import com.compraventap.repository.PropiedadRepository;
import com.compraventap.repository.VendedorRepository;
import com.compraventap.repository.UsuarioRepository; // Asegúrate de tener este repo creado

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/api/propiedades") 
@CrossOrigin(
    origins = {
        "http://localhost:3000", 
        "https://compra-venta-propiedades.vercel.app"
    }, 
    allowCredentials = "true"
)
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint para listar todas las propiedades
    @GetMapping
    public List<Propiedad> listarTodas() {
        return propiedadRepository.findAll();
    }

    // Endpoint para listar propiedad por ID
    @GetMapping("/{id}")
    public Propiedad obtenerPropiedadPorId(@PathVariable Integer id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    // Endpoint para agregar una nueva propiedad (Manejo Automático de Vendedor)
    @PostMapping
    public ResponseEntity<?> guardaPropiedad(@RequestBody Propiedad propiedad) {
        try {
            // 1. Validar que el objeto vendedor y el ID del usuario vengan en la petición
            if (propiedad.getVendedor() == null || 
                propiedad.getVendedor().getUsuario() == null || 
                propiedad.getVendedor().getUsuario().getIdUsuario() == null) {
                // Si el front mandó 'idUsuario' dentro del objeto vendedor temporalmente
                return ResponseEntity.badRequest().body("Error: Debe proporcionar el idUsuario del vendedor.");
            }

            // Extraemos el idUsuario que viene desde el hook useAuth() de Next.js
            Integer idUsuarioRecibido = propiedad.getVendedor().getUsuario().getIdUsuario();

            Vendedor vendedorFinal;

            // 2. Buscar si este Usuario ya está registrado como Vendedor en la BD
            Optional<Vendedor> vendedorExistente = vendedorRepository.findByUsuarioIdUsuario(idUsuarioRecibido);

            if (vendedorExistente.isPresent()) {
                // Si ya existe en la tabla Vendedor, usamos ese registro existente
                vendedorFinal = vendedorExistente.get();
            } else {
                // Si NO existe, lo creamos de forma automática en este instante
                vendedorFinal = new Vendedor();
                
                // Buscamos la entidad Usuario completa para mantener la integridad referencial de la BD
                Usuario usuarioAsociado = usuarioRepository.findById(idUsuarioRecibido)
                        .orElseThrow(() -> new RuntimeException("El usuario con ID " + idUsuarioRecibido + " no existe."));
                
                vendedorFinal.setUsuario(usuarioAsociado);
                
                // Guardamos el nuevo vendedor en su respectiva tabla
                vendedorFinal = vendedorRepository.save(vendedorFinal);
            }

            // 3. Vincular el vendedor verificado/creado a la propiedad y salvar
            propiedad.setVendedor(vendedorFinal);
            Propiedad nuevaPropiedad = propiedadRepository.save(propiedad);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPropiedad);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno en el servidor: " + e.getMessage());
        }
    }

    // Endpoint para actualizar una propiedad existente
    @PutMapping("/{id}")
    public Propiedad actualizarPropiedad(@PathVariable int id, @RequestBody Propiedad detallesPropiedad) {
        return propiedadRepository.findById(id)
                .map(propiedad -> {
                    propiedad.setUbicacion(detallesPropiedad.getUbicacion());
                    propiedad.setPrecio(detallesPropiedad.getPrecio());
                    propiedad.setTitulo(detallesPropiedad.getTitulo());
                    propiedad.setDescripcion(detallesPropiedad.getDescripcion());
                    propiedad.setArea(detallesPropiedad.getArea());
                    propiedad.setBedrooms(detallesPropiedad.getBedrooms());
                    propiedad.setBathrooms(detallesPropiedad.getBathrooms());
                    return propiedadRepository.save(propiedad);
                }).orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
    }

    // Endpoint para eliminar una propiedad
    @DeleteMapping("/{id}")
    public String eliminarPropiedad(@PathVariable int id) {
        propiedadRepository.deleteById(id);
        return "Propiedad eliminada con id: " + id + " exitosamente"; 
    }
}