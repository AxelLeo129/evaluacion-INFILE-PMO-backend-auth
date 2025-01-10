package com.auth.services;

import com.auth.entities.User;
import com.auth.exceptions.AuthException;

import java.util.Map;

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
    String registerUser(String name, String email, String password, String repeatPassword) throws AuthException;

    /**
     * Solicita la recuperación de contraseña para un usuario.
     * <p>
     * Este método envía un correo electrónico al usuario con un enlace o instrucciones
     * para recuperar su contraseña. Si el correo no está registrado o ocurre un error,
     * lanza una excepción {@link AuthException}.
     * </p>
     *
     * @param email El correo electrónico del usuario.
     * @return Un código de estado HTTP:
     *         <ul>
     *             <li><code>202</code>: El correo fue enviado exitosamente.</li>
     *             <li><code>500</code>: Error al enviar el correo.</li>
     *         </ul>
     * @throws AuthException Si el correo no está registrado o ocurre un error durante el envío.
     */
    Integer recoveryPassword(String email) throws AuthException;

    /**
     * Restablece la contraseña de un usuario.
     * <p>
     * Este método valida las contraseñas proporcionadas y utiliza la información del token
     * de autenticación en los encabezados para identificar al usuario. Si las contraseñas no coinciden
     * o ocurre un error, lanza una excepción {@link AuthException}.
     * </p>
     *
     * @param headers        Encabezados de la solicitud que contienen el token de autenticación.
     * @param password       La nueva contraseña del usuario.
     * @param repeatPassword La confirmación de la nueva contraseña.
     * @return Un mensaje indicando si el restablecimiento fue exitoso.
     * @throws AuthException Si las contraseñas no coinciden o ocurre un error durante el restablecimiento.
     */
    String resetPassword(Map<String, String> headers, String password, String repeatPassword) throws AuthException;

    /**
     * Activa un usuario en el sistema.
     * <p>
     * Este método activa un usuario en el sistema utilizando el token de activación proporcionado.
     * Si el token es inválido o ha expirado, lanza una excepción {@link AuthException}.
     * </p>
     *
     * @param token El token de activación del usuario.
     * @throws AuthException Si el token es inválido o ha expirado.
     */
    void activateUser(String token) throws AuthException;

}
