/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.dto;

/**
 *
 * @author jorge
 */
public class ResponseDTO {
    private long idEntidad;
    private String estado;
    private String mensaje;
    private Object dto;

    public ResponseDTO(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public long getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }
    
}
