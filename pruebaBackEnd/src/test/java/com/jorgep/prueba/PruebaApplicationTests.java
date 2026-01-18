/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba;

import com.jorgep.prueba.dto.ClienteDTO;

import com.jorgep.prueba.service.ClienteService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;   
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PruebaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ClienteService clienteService;
    
	@Test
	void contextLoads() throws Exception {
            List<ClienteDTO> lista = Arrays.asList(
                    new ClienteDTO(2),
                    new ClienteDTO(3)
            );
            
            //se utiliza para configurar el comportamiento de un método en un objeto simulado (mock)
            Mockito.when(clienteService.getClientes()).thenReturn(lista);
            
            mockMvc.perform(get("/clientes/listar"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].clienteid").value(2))
                    .andExpect(jsonPath("$[1].clienteid").value(3));
	}

}
