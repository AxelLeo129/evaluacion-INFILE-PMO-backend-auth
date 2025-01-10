package com.auth.utilities;

/**
 * Clase de utilidades que define constantes globales para la aplicación.
 * <p>
 * Esta clase contiene valores constantes utilizados en diferentes partes de la aplicación,
 * como la clave secreta para generar y validar tokens JWT y la duración de validez de los tokens.
 * </p>
 */
public class Constants {

    /**
     * Clave secreta utilizada para firmar y validar tokens JWT.
     * <p>
     * Esta clave debe ser lo suficientemente larga y segura para cumplir con los requisitos
     * del algoritmo de firma HS256. Es fundamental proteger este valor y no exponerlo públicamente.
     * </p>
     */
    public static final String API_SECRET_KEY = "ThisIsASecretKeyThatIsAtLeast32CharactersLong!";
    
    /**
     * Validez del token JWT en milisegundos.
     * <p>
     * Este valor representa la duración durante la cual un token JWT es válido antes de expirar.
     * En este caso, la duración es de 2 horas (2 * 60 * 60 * 1000).
     * </p>
     */
    public static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000;

    /**
     * Dirección de correo electrónico utilizada como remitente en los correos electrónicos enviados.
     * <p>
     * Este correo electrónico se utiliza como el "from" en los correos enviados a través de la API de SendGrid.
     * </p>
     */
    public static final String FROM_EMAIL = "sdmanager@thinkotb.io";

    /**
     * Clave API de SendGrid.
     * <p>
     * Esta clave se utiliza para autenticar las solicitudes a la API de SendGrid para enviar correos electrónicos.
     * Es fundamental que este valor se mantenga seguro y no se exponga públicamente.
     * </p>
     */
    public static final String API_KEY = "";

}
