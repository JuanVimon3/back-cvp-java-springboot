package com.compraventap.service;

import com.compraventap.model.nosql.ChatMessage;
import com.compraventap.model.nosql.ChatRoom;
import com.compraventap.repository.nosql.ChatMessageRepository;
import com.compraventap.repository.nosql.ChatRoomRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// Importaciones estáticas explícitas para evitar problemas de indexación
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatService chatService;

    private ChatRoom salaExistente;
    private Integer propiedadId = 1;
    private Integer compradorId = 7;
    private Integer vendedorId = 2;

    @BeforeEach
    void setUp() {
        salaExistente = new ChatRoom(propiedadId, compradorId, vendedorId);
        salaExistente.setId("6a2d748fc44427ffe9a22fd5");
    }

    @Test
    @DisplayName("Debería retornar una sala existente si ya coincide con la propiedad")
    void obtenerOCrearSala_CuandoYaExiste_RetornaSalaExistente() {
        when(chatRoomRepository.findByCompradorIdOrVendedorId(compradorId, vendedorId))
                .thenReturn(Collections.singletonList(salaExistente));

        ChatRoom resultado = chatService.obtenerOCrearSala(propiedadId, compradorId, vendedorId);

        assertNotNull(resultado);
        assertEquals("6a2d748fc44427ffe9a22fd5", resultado.getId());
        assertEquals(propiedadId, resultado.getPropiedadId());
        verify(chatRoomRepository, never()).save(any(ChatRoom.class));
    }

    @Test
    @DisplayName("Debería crear y guardar una sala nueva si no existe una para la propiedad")
    void obtenerOCrearSala_CuandoNoExiste_CreaYGuardaSalaNueva() {
        when(chatRoomRepository.findByCompradorIdOrVendedorId(compradorId, vendedorId))
                .thenReturn(Collections.emptyList());
        
        ChatRoom nuevaSalaSimulada = new ChatRoom(propiedadId, compradorId, vendedorId);
        nuevaSalaSimulada.setId("nueva_sala_123");
        
        when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(nuevaSalaSimulada);

        ChatRoom resultado = chatService.obtenerOCrearSala(propiedadId, compradorId, vendedorId);

        assertNotNull(resultado);
        assertEquals("nueva_sala_123", resultado.getId());
        verify(chatRoomRepository, times(1)).save(any(ChatRoom.class));
    }

    @Test
    @DisplayName("Debería guardar un mensaje de forma exitosa")
    void guardarMensaje_Exitoso() {
        String chatRoomId = "6a2d748fc44427ffe9a22fd5";
        String contenido = "Hola, prueba JUnit";
        ChatMessage mensajeMock = new ChatMessage(chatRoomId, compradorId, contenido, false);
        mensajeMock.setId("msg_999");

        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(mensajeMock);

        ChatMessage resultado = chatService.guardarMensaje(chatRoomId, compradorId, contenido, false);

        assertNotNull(resultado);
        assertEquals("msg_999", resultado.getId());
        assertEquals(contenido, resultado.getContent());
        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

    @Test
    @DisplayName("Debería listar las salas donde participa el usuario")
    void listarSalasDelUsuario_Exitoso() {
        Integer idUsuario = 7;
        when(chatRoomRepository.findByCompradorIdOrVendedorId(idUsuario, idUsuario))
                .thenReturn(Arrays.asList(salaExistente));

        List<ChatRoom> resultado = chatService.listarSalasDelUsuario(idUsuario);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(compradorId, resultado.get(0).getCompradorId());
    }
}