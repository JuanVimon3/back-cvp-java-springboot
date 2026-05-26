package com.compraventap;

import static org.assertj.core.api.Assertions.assertThat;
import com.compraventap.repository.VendedorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VendedorRepositoryTest {
    
    @Autowired
    private VendedorRepository vendedorRepository;

    @Test
    public void contextLoads() {
        assertThat(vendedorRepository).isNotNull();
    }
    
}
