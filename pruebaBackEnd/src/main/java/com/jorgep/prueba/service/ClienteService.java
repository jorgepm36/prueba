/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.service;

import com.jorgep.prueba.dto.ClienteDTO;
import com.jorgep.prueba.dto.ResponseDTO;
import com.jorgep.prueba.entity.Cliente;
import com.jorgep.prueba.entity.Cuenta;
import com.jorgep.prueba.exceptions.PersonalException;
import com.jorgep.prueba.repository.ClienteRepository;
import com.jorgep.prueba.repository.CuentaRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 *
 * @author jorge
 */
@Service
public class ClienteService {
    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());
    private final ClienteRepository repository;
    private final CuentaRepository cuentaRepository;

    public ClienteService(ClienteRepository repository, CuentaRepository cuentaRepository) {
        this.repository = repository;
        this.cuentaRepository = cuentaRepository;
    }

    public ResponseDTO guardarCliente(ClienteDTO clienteDto) throws PersonalException {
        try{
            ModelMapper modelMapper = new ModelMapper();
            Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
            cliente = repository.save(cliente);
            //clienteDto = modelMapper.map(cliente, ClienteDTO.class);
            ResponseDTO response = new ResponseDTO("OK", "CLIENTE CREADO");
            response.setIdEntidad(cliente.getId());
            return response;
        }catch(jakarta.validation.ConstraintViolationException exc){
            LOG.log(Level.SEVERE, null, exc);
            throw new PersonalException(exc.getMessage());
        }catch(DataIntegrityViolationException exc){
            LOG.log(Level.SEVERE, null, exc);
            throw new PersonalException("Error de integridad al guardar el cliente");
        }catch(Exception exc){
            LOG.log(Level.SEVERE, null, exc);
            throw new PersonalException("Error general al guardar el cliente");
        }
    }
    
    public List<ClienteDTO> getClientes() {
        List<Cliente> clientes = repository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        /*final List<ClienteDTO> clientesDTO = new ArrayList<>();
        clientes.forEach((cliente) -> {
            ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
            clientesDTO.add(dto);
        });*/
        List<ClienteDTO> clientesDTO = clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        //List<ClienteDTO> clientesDTO = modelMapper.map(clientes, new TypeToken<List<ClienteDTO>>(){}.getType());
        return clientesDTO;
    }
    
    public ClienteDTO getClientePorId(Long id) {
        Cliente cliente = repository.findById(id).orElse(new Cliente());
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
        return dto;
    }
    
    
    
    //Si todo tiene éxito, los cambios se confirman (commit).
    //Si ocurre un error, todos los cambios se deshacen (rollback), manteniendo la consistencia de los datos
    //solo hace rollback si una excepción no capturada escapa del método
    @Transactional
    public ResponseDTO actualizarCliente(Long id, ClienteDTO clienteActualizadoDto) throws PersonalException {
        try{
            LOG.log(Level.INFO, "id cliente: {0}", id);
            Cliente aux = repository.findById(id)
                .orElseThrow(() -> new PersonalException("Cliente no encontrado con el id ingresado"));
            
            ModelMapper modelMapper = new ModelMapper();
            Cliente cliente = modelMapper.map(clienteActualizadoDto, Cliente.class);
            cliente.setId(aux.getId());
            repository.save(cliente);
            
            //esto se usa solamente para mostrar la transaccionalidad
            //crear una cuenta nueva
            Cuenta cuenta = modelMapper.map(clienteActualizadoDto.getCuenta(), Cuenta.class);
            LOG.log(Level.INFO, "{0}", cuenta.getEstado());
            LOG.log(Level.INFO, "{0}", cuenta.getTipoCuenta());
            cuentaRepository.save(cuenta);
            
            ResponseDTO response = new ResponseDTO("OK", "CLIENTE ACTUALIZADO");
            response.setIdEntidad(cliente.getId());
            return response;
        }catch(PersonalException exc){
            LOG.log(Level.SEVERE, null, exc);
            throw new PersonalException(exc.getMessage());
        }catch(Exception exc){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.log(Level.SEVERE, null, exc);
            throw new PersonalException("Error general al actualizar el cliente");
        }
    }
    
    
    @Transactional
    public ResponseDTO eliminarCliente(Long id) throws PersonalException {
        try{
            if (!repository.existsById(id)) {
                throw new PersonalException("Cliente no encontrado con ID: " + id);
            }
            repository.deleteById(id);
            
            //eliminar las cuentas
            cuentaRepository.eliminarCuentasCliente(id);
            
            return new ResponseDTO("OK", "CLIENTE ELIMINADO");
        }catch(PersonalException exc){
            throw new PersonalException(exc.getMessage());
        }catch(Exception exc){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOG.log(Level.SEVERE, null, exc);
            throw new PersonalException("Error general al eliminar el cliente");
        }
    }
    
}
