package com.auth.services.impl;

import com.auth.entities.User;
import com.auth.exceptions.AuthException;
import com.auth.repositories.UserRepository;
import com.auth.services.UserService;
import com.auth.utilities.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Implementación del servicio {@link UserService} para gestionar usuarios.
 * <p>
 * Proporciona la lógica de negocio para las operaciones de registro y autenticación de usuarios,
 * utilizando JPA, bcrypt para encriptación de contraseñas, y JWT para generación de tokens de autenticación.
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String email, String password) throws AuthException {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isEmpty()) throw new AuthException("User not found");
            if(!BCrypt.checkpw(password, user.get().getPassword())) throw new AuthException("Invalid password");
            return generateJWTToken(user.get());
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public String registerUser(String name, String email, String password) throws AuthException {
        try {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
            Pattern pattern = Pattern.compile("^(.+)@(.+)$");
            if(email != null) email = email.toLowerCase();
            assert email != null;
            if(!pattern.matcher(email).matches()) throw new AuthException("Invalid email format");
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return "Registered successfully";
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    /**
     * Genera un token JWT para un usuario autenticado.
     * <p>
     * El token contiene claims con información del usuario, como su ID, correo electrónico y nombre.
     * También incluye una fecha de expiración basada en la configuración de la aplicación.
     * </p>
     *
     * @param user El usuario autenticado.
     * @return Un token JWT firmado con la clave secreta de la aplicación.
     */
    private String generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .compact();
    }

}
