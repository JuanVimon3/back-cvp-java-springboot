package com.compraventap.model.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "messages")
public class ChatMessage {

    @Id
    private String id;

    private String chatRoomId; // ID de la sala de chat a la que pertenece el mensaje
    private Integer senderId; // ID del usuario que envió el mensaje
    private String content; // Contenido del mensaje
    private LocalDateTime timestamp; // Fecha y hora en que se envió el mensaje
    private Boolean isAiResponse; // Indica si el mensaje es una respuesta generada por la IA

    // Constructores

    public ChatMessage() {
        this.timestamp = LocalDateTime.now();
    }

    public ChatMessage(String chatRoomId, Integer senderId, String content, Boolean isAiResponse) {
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isAiResponse = isAiResponse;
    }

    // Getters y Setters

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId){
        this.chatRoomId = chatRoomId;
    }

    public Integer getSenderId(){
        return senderId;
    }

    public void setSenderId(Integer senderId){
        this.senderId = senderId;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    public Boolean getIsAiResponse() {
        return isAiResponse;
    }

    public void setIsAiResponse(Boolean isAiResponse) {
        this.isAiResponse = isAiResponse;
    }

}
