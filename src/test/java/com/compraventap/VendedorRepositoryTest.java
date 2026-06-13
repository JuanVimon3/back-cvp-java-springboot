package com.compraventap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.compraventap.repository.relational.VendedorRepository;

@SpringBootTest
@ActiveProfiles("test") // <--- Obliga a usar application-test.properties
public class VendedorRepositoryTest {
    
    @Autowired
    private VendedorRepository vendedorRepository;

    @Test
    public void contextLoads() {
        assertThat(vendedorRepository).isNotNull();
    }
}