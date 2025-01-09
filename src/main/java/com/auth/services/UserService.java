package com.auth.services;

import com.auth.entities.User;
import com.auth.exceptions.AuthException;

/**
 * Servicio para manejar operaciones relacionadas con usuarios.
 * <p>
 * Define los métodos para registrar usuarios y autenticar credenciales,
 * lanzando excepciones en caso de errores relacionados con la autenticación.
 * </p>
 */
public interface UserService {

    /**
     * Autentica a un usuario con las credenciales proporcionadas.
     * <p>
     * Este método valida las credenciales (correo electrónico y contraseña) de un usuario.
     * Si las credenciales son correctas, genera y devuelve un token de autenticación.
     * Si las credenciales son inválidas o ocurre un error, lanza una excepción {@link AuthException}.
     * </p>
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return Un token de autenticación si las credenciales son válidas.
     * @throws AuthException Si las credenciales son inválidas o ocurre un error durante la autenticación.
     */
    String login(String email, String password) throws AuthException;

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * Este método crea un nuevo usuario con el nombre, correo electrónico y contraseña proporcionados.
     * Si el correo electrónico ya está registrado o ocurre un error, lanza una excepción {@link AuthException}.
     * </p>
     *
     * @param name     El nombre del usuario.
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return Un mensaje de confirmación indicando que el registro fue exitoso.
     * @throws AuthException Si el correo electrónico ya está en uso o ocurre un error durante el registro.
     */
    String registerUser(String name, String email, String password) throws AuthException;

}
