/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.entity;

import com.jorgep.prueba.enums.EstadoEnum;
import com.jorgep.prueba.enums.TipoCuentaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El campo numeroCuenta no debe ser nulo")
    @Column(unique = true, nullable = false)
    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCuentaEnum tipoCuenta;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoInicial;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEnum estado;
    @Column(nullable = false)
    private Long idCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuentaEnum getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
}
