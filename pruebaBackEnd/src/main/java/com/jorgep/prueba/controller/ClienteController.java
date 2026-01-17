/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.controller;

import com.jorgep.prueba.dto.ClienteDTO;
import com.jorgep.prueba.dto.ResponseDTO;
import com.jorgep.prueba.exceptions.PersonalException;
import com.jorgep.prueba.service.ClienteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author jorge
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    //@Autowired tambien se puede utilizar
    private final ClienteService servicio;
    public ClienteController(ClienteService servicio){
        this.servicio = servicio;
    }
    
    @PostMapping("guardar")
    public ResponseEntity<ResponseDTO> guardarCliente(@Valid @RequestBody ClienteDTO dto) {
        try{
            ResponseDTO response = servicio.guardarCliente(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(PersonalException exc){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDTO("ERROR", exc.getMessage()));
        }
    }
    
    @GetMapping("listar")
    public List<ClienteDTO> getClientes() {
        return servicio.getClientes();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> eliminarCliente(@PathVariable Long id) {
        try{
            ResponseDTO response = servicio.eliminarCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(PersonalException exc){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDTO("ERROR", exc.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ClienteDTO getClientePorId(@PathVariable Long id) {
        return servicio.getClientePorId(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteActualizado) {
        try{
            ResponseDTO response = servicio.actualizarCliente(id, clienteActualizado);
            return ResponseEntity.ok(response);
        }catch(PersonalException exc){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDTO("ERROR", exc.getMessage()));
        }
    }
    
}
