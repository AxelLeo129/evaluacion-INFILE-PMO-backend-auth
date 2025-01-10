package com.auth.controllers;

import com.auth.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 * <p>
 * Este controlador expone endpoints para registrar y autenticar usuarios en la API.
 * </p>
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint para registrar un nuevo usuario.
     * <p>
     * Este endpoint procesa una solicitud POST que contiene los datos del usuario,
     * como nombre, correo electrónico y contraseñas (incluyendo confirmación),
     * para registrarlo en el sistema.
     * </p>
     *
     * @param userMap Un mapa con los datos del usuario, incluyendo "name", "email", "password" y "repeatPassword".
     * @return Una respuesta HTTP con un mensaje de confirmación en caso de éxito.
     *         El estado de la respuesta será <code>201 CREATED</code>.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String repeatPassword = (String) userMap.get("repeatPassword");
        String response = userService.registerUser(name, email, password, repeatPassword);
        return new ResponseEntity<>(Map.of("message", response), HttpStatus.CREATED);
    }

    /**
     * Endpoint para autenticar un usuario existente.
     * <p>
     * Este endpoint procesa una solicitud POST que contiene las credenciales del usuario,
     * incluyendo el correo electrónico y la contraseña, para generar un token de autenticación.
     * </p>
     *
     * @param loginMap Un mapa con las credenciales del usuario, incluyendo "email" y "password".
     * @return Una respuesta HTTP con un token de autenticación en caso de éxito.
     *         El estado de la respuesta será <code>200 OK</code>.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> loginMap) {
        String email = (String) loginMap.get("email");
        String password = (String) loginMap.get("password");
        String response = userService.login(email, password);
        return new ResponseEntity<>(Map.of("token", response), HttpStatus.OK);
    }

    /**
     * Endpoint para solicitar la recuperación de contraseña.
     * <p>
     * Este endpoint envía un correo electrónico al usuario con un enlace o instrucciones
     * para restablecer su contraseña.
     * </p>
     *
     * @param form Un mapa que contiene el correo electrónico del usuario ("email").
     * @return Una respuesta HTTP con un mensaje de éxito o error.
     *         - <code>200 OK</code>: Si el correo fue enviado correctamente.
     *         - <code>500 INTERNAL_SERVER_ERROR</code>: Si ocurre un error al enviar el correo.
     */
    @PostMapping("/recovery-password")
    public ResponseEntity<Map<String, String>> recoveryPassword(@RequestBody Map<String, Object> form) {
        String email = (String) form.get("email");
        Integer response = userService.recoveryPassword(email);
        if(response == 202)
            return new ResponseEntity<>(Map.of("message", "Email send successfully"), HttpStatus.OK);
        else
            return new ResponseEntity<>(Map.of("message", "Error al enviar correo"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Endpoint para restablecer la contraseña del usuario.
     * <p>
     * Este endpoint valida las contraseñas proporcionadas y utiliza información del token JWT
     * enviado en los encabezados para identificar al usuario que realiza la solicitud.
     * </p>
     *
     * @param headers Encabezados de la solicitud que contienen el token de autorización.
     * @param form    Un mapa que contiene las contraseñas ("password" y "repeatPassword").
     * @return Una respuesta HTTP con un mensaje de éxito o error.
     *         - <code>200 OK</code>: Si la contraseña se restableció correctamente.
     *         - <code>400 BAD_REQUEST</code>: Si las contraseñas no coinciden.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestHeader Map<String, String> headers, @RequestBody Map<String, Object> form) {
        String password = (String) form.get("password");
        String repeatPassword = (String) form.get("repeatPassword");
        String response = userService.resetPassword(headers, password, repeatPassword);
        return new ResponseEntity<>(Map.of("message", response), HttpStatus.OK);
    }

}
