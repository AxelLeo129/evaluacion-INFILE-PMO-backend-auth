package com.auth.repositories;

import com.auth.entities.User;
import com.auth.exceptions.AuthException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad {@link User}.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en la tabla <code>users</code>,
 * así como consultas específicas, como buscar usuarios por correo electrónico.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Busca un usuario por su correo electrónico.
     * <p>
     * Este método devuelve un {@link Optional} que contiene el usuario encontrado,
     * o vacío si no existe un usuario con el correo especificado.
     * Si ocurre un error relacionado con la autenticación durante la consulta,
     * se lanza una excepción {@link AuthException}.
     * </p>
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return Un {@link Optional} que contiene el usuario si es encontrado.
     * @throws AuthException Si ocurre un error durante la consulta.
     */
    Optional<User> findByEmail(String email) throws AuthException;

}
