/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.repository;

import com.jorgep.prueba.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jorge
 */
@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Cliente findByClienteid(Long clienteid);
}
