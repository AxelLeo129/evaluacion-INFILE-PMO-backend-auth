package com.auth.entities;

import jakarta.persistence.*;

/**
 * Entidad que representa a un usuario en el sistema.
 * <p>
 * Esta clase mapea la tabla <code>users</code> en la base de datos y almacena información básica del usuario
 * como su identificador, nombre, correo electrónico y contraseña.
 * </p>
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Identificador único del usuario.
     * <p>
     * Este campo es la clave primaria de la tabla <code>users</code> y su valor se genera automáticamente
     * utilizando una secuencia llamada <code>users_id_seq</code>.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_seq", allocationSize = 1)
    private Integer id;

    /**
     * Nombre completo del usuario.
     */
    @Column(name = "name")
    private String name;

    /**
     * Correo electrónico del usuario.
     * <p>
     * Este campo debe ser único en la tabla <code>users</code>.
     * </p>
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Contraseña del usuario.
     * <p>
     * Este campo debe almacenar la contraseña en formato encriptado por razones de seguridad.
     * </p>
     */
    @Column(name = "password")
    private String password;

    /**
     * Constructor con parámetros.
     *
     * @param id       Identificador único del usuario.
     * @param name     Nombre completo del usuario.
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña encriptada del usuario.
     */
    public User(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor vacío.
     * <p>
     * Necesario para el funcionamiento de JPA.
     * </p>
     */
    public User() { }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id Identificador único.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param name Nombre completo.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email Correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña encriptada del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password Contraseña encriptada.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
