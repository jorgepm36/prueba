/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.dto;

import com.jorgep.prueba.enums.EstadoEnum;

/**
 *
 * @author jorge
 */
public class ClienteDTO extends PersonaDTO {
    private long clienteid;
    private String contrasenia;
    private EstadoEnum estado;

    //esto se utiliza solamente para demostrar la transacionalidad en el sevicio
    private CuentaDTO cuenta;
    
    public long getClienteid() {
        return clienteid;
    }

    public void setClienteid(long clienteid) {
        this.clienteid = clienteid;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
