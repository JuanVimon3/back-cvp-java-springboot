package com.compraventap.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    // Esto mapeará la propiedad spring.data.mongodb.uri de tu application.properties
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        // Ponemos "compra_venta_chats" por si no lo extrae bien de la URI
        return new MongoTemplate(mongoClient(), "compra_venta_chats");
    }
}