package com.compraventap.controller;

import com.compraventap.model.nosql.ChatMessage;
import com.compraventap.model.nosql.ChatRoom;
import com.compraventap.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")

public class ChatController {
    
    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    // POST http://localhost:8080/api/chats/sala?propiedadId=1&compradorId=2&vendedorId=3
    @PostMapping("/sala")
    public ResponseEntity<ChatRoom> abrirSala(
            @RequestParam Integer propiedadId,
            @RequestParam Integer compradorId,
            @RequestParam Integer vendedorId
    ){
        ChatRoom sala = chatService.obtenerOCrearSala(propiedadId, compradorId, vendedorId);
        return ResponseEntity.ok(sala);
    }
    
    // POST http://localhost:8080/api/chats/mensaje?chatRoomId=XYZ&senderId=1&content=Hola
    @PostMapping("/mensaje")
    public ResponseEntity<ChatMessage> enviarMensaje(
            @RequestParam String chatRoomId,
            @RequestParam Integer senderId,
            @RequestParam String content,
            @RequestParam(defaultValue = "false") Boolean isAiResponse
    ){
        // Corregido "guardarMensaje" con J
        ChatMessage mensajeGuardado = chatService.guardarMensaje(chatRoomId, senderId, content, isAiResponse);
        return ResponseEntity.ok(mensajeGuardado);
    }
    
    // GET http://localhost:8080/api/chats/historial/ID_DE_LA_SALA
    @GetMapping("/historial/{chatRoomId}") // Se agregó /{chatRoomId} para que el @PathVariable funcione
    public ResponseEntity<List<ChatMessage>> traerHistorial(@PathVariable String chatRoomId){
        List<ChatMessage> historial = chatService.obtenerHistorial(chatRoomId);
        return ResponseEntity.ok(historial);
    }

    // GET http://localhost:8080/api/chats/salas/ID_DEL_USUARIO
    @GetMapping("/salas/{idUsuario}")
    public ResponseEntity<List<ChatRoom>> misChats(@PathVariable Integer idUsuario){
        List<ChatRoom> salas = chatService.listarSalasDelUsuario(idUsuario);
        return ResponseEntity.ok(salas);
    }
}

// prueba