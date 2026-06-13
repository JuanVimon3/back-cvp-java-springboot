package com.compraventap.model.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;



@Document(collection = "chatrooms")
public class ChatRoom {
    
    @Id
    private String id;

    private Integer propiedadId; // ID de la propiedad relacionada con la sala de chat
    private Integer compradorId; // ID del comprador que participa en la sala de chat
    private Integer vendedorId; // ID del vendedor que participa en la sala de chat
    private LocalDateTime createdAt; // Fecha y hora de creación de la sala de chat

    //Constructores

    public ChatRoom(){
        this.createdAt = LocalDateTime.now();
    }

    public ChatRoom(Integer propiedadId, Integer compradorId, Integer vendedorId){
        this.propiedadId = propiedadId;
        this.compradorId = compradorId;
        this.vendedorId = vendedorId;
        this.createdAt = LocalDateTime.now();
    }

    // Getters y Setters

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public Integer getPropiedadId(){
        return propiedadId;
    }
    public void setPropiedadId(Integer propiedadId){
        this.propiedadId = propiedadId;
    }
    public Integer getCompradorId(){
        return compradorId;
    }
    public void setCompradorId(Integer compradorId){
        this.compradorId = compradorId;
    }
    public Integer getVendedorId(){
        return vendedorId;
    }
    public void setVendedorId(Integer vendedorId){
        this.vendedorId = vendedorId;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

}
