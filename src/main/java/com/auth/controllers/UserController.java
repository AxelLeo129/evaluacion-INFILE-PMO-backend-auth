package com.auth.controllers;

import com.auth.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * tales como el nombre, correo electrónico y contraseña, para registrarlo en el sistema.
     * </p>
     *
     * @param userMap Un mapa con los datos del usuario, incluyendo "name", "email" y "password".
     * @return Una respuesta HTTP con un mensaje de confirmación en caso de éxito.
     *         El estado de la respuesta será <code>201 CREATED</code>.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String response = userService.registerUser(name, email, password);
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

}
