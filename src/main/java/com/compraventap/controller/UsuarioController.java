package com.compraventap.controller;

import com.compraventap.dto.LoginRequest;
import com.compraventap.model.Usuario;
import com.compraventap.repository.UsuarioRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(originPatterns = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario>listarTodos() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerAdministradorPorId(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario guardUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // Endpoint para actualizar un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario detallesUsuario){
        return usuarioRepository.findById(id)
        .map(usuario -> {
            usuario.setNombre(detallesUsuario.getNombre());
            usuario.setCedula(detallesUsuario.getCedula());
            usuario.setEmail(detallesUsuario.getEmail());
            usuario.setPassword(detallesUsuario.getPassword());

            return usuarioRepository.save(usuario);

        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

    }

    //Endpoint para eliminar un usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id){
        usuarioRepository.deleteById(id);
        return "Usuario con id: " + id + "eliminado exitosamente";
    }

    // Endpoint para login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    // Se usa el médoto findByEmail definido en UsuarioRepository
    return usuarioRepository.findByEmail(loginRequest.getEmail())
        .map(user -> {
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(user); // Login exitoso
            }
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        })
        .orElse(ResponseEntity.status(404).body("Usuario no encontrado"));
}
    
}
