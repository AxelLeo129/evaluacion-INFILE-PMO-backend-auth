package com.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para errores de autenticación.
 * <p>
 * Esta excepción se lanza cuando ocurre un error relacionado con la autenticación o
 * el manejo de usuarios en el sistema. La respuesta HTTP asociada es un estado
 * <code>500 Internal Server Error</code>.
 * </p>
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AuthException extends RuntimeException {

    /**
     * Constructor de la excepción con un mensaje personalizado.
     *
     * @param message Mensaje descriptivo del error.
     */
    public AuthException(String message) {
        super(message);
    }

}
