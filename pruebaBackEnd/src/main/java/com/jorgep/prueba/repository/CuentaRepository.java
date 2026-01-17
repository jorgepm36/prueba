/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.repository;

import com.jorgep.prueba.entity.Cuenta;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jorge
 */
@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta, Long> {
    @Modifying
    @Query("DELETE FROM Cuenta c WHERE c.idCliente = :idCliente")
    int eliminarCuentasCliente(@Param("idCliente") Long idCliente);
}
