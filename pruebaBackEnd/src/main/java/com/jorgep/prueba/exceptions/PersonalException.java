/**
 * Proyecto desarrollado por Jorge Ponce
 * Prohibido su uso o reproducción sin autorización del autor
 * Enero 2026
 */
package com.jorgep.prueba.exceptions;

/**
 *
 * @author jorge
 */
public class PersonalException  extends Exception {
    public PersonalException(String message) {
        super(message);
    }

    public PersonalException(String message, Throwable cause) {
        super(message, cause);
    }
}
