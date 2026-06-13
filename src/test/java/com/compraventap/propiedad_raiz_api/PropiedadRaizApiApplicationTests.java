package com.compraventap.propiedad_raiz_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // <--- Obliga a usar application-test.properties
class PropiedadRaizApiApplicationTests {

    @Test
    void contextLoads() {
    }
}