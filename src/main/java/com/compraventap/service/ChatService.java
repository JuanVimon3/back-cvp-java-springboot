package com.compraventap.service;

import com.compraventap.model.nosql.ChatMessage;
import com.compraventap.model.nosql.ChatRoom;
import com.compraventap.repository.nosql.ChatMessageRepository;
import com.compraventap.repository.nosql.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatRoomRepository chatRoomRepository, ChatMessageRepository chatMessageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }
    
    //Generar una nueva sala de chat o recuperar una existente entre un comprador y un vendedor para una propiedad específica
    public ChatRoom obtenerOCrearSala(Integer propiedadId, Integer compradorId, Integer vendedorId){
        List<ChatRoom> chatRooms = chatRoomRepository.findByCompradorIdOrVendedorId(compradorId, vendedorId);
        Optional<ChatRoom> salaExistente = chatRooms.stream()
                .filter(room -> room.getPropiedadId().equals(propiedadId))
                .findFirst();
        
        if(salaExistente.isPresent()){
            return salaExistente.get();
        } else {
            ChatRoom nuevaSala = new ChatRoom(propiedadId, compradorId, vendedorId);
            return chatRoomRepository.save(nuevaSala);
        }
    }

    //Guardar un mensaje en una sala de chat específica
    public ChatMessage guardarMensaje(String chatRoomId, Integer senderId, String content, Boolean isAiResponse){
        ChatMessage mensaje = new ChatMessage(chatRoomId, senderId, content, isAiResponse);
        return chatMessageRepository.save(mensaje);
    }

    // Obtener el historial de mensajes de una sala ordenados por fecha
    public List<ChatMessage> obtenerHistorial(String chatRoomId){
        return chatMessageRepository.findByChatRoomIdOrderByTimestampAsc(chatRoomId);
    }

    // Buscar las salas de chat en las que participa un comprador o vendedor específico
    public List<ChatRoom> listarSalasDelUsuario(Integer idUsuario){
        return chatRoomRepository.findByCompradorIdOrVendedorId(idUsuario, idUsuario);
    }
}
