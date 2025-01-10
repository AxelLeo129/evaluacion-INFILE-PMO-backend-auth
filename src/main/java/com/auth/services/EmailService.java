package com.auth.services;

import java.util.Map;

/**
 * Servicio para gestionar el envío de correos electrónicos.
 * <p>
 * Define un método para enviar correos electrónicos utilizando plantillas dinámicas.
 * Permite personalizar el contenido del correo según las variables proporcionadas y
 * utilizar plantillas predefinidas en el sistema.
 * </p>
 */
public interface EmailService {

    /**
     * Envía un correo electrónico utilizando una plantilla dinámica.
     * <p>
     * Este método utiliza una plantilla identificada por <code>emailId</code> y
     * completa las variables dinámicas con el contenido proporcionado en <code>dinamicContent</code>.
     * </p>
     *
     * @param email           Dirección de correo del destinatario.
     * @param subject         Asunto del correo electrónico.
     * @param dinamicContent  Mapa con el contenido dinámico para la plantilla.
     *                        Las claves del mapa deben coincidir con las variables definidas
     *                        en la plantilla.
     *                        <ul>
     *                          <li>Ejemplo: {"name": "John", "link": "https://example.com"}</li>
     *                        </ul>
     * @param emailId         Identificador único de la plantilla de correo.
     *                        Este ID debe corresponder a una plantilla válida configurada en
     *                        el sistema de correo.
     * @return Código de estado HTTP devuelto por el sistema de correo:
     *         <ul>
     *           <li><code>202</code>: El correo fue enviado exitosamente.</li>
     *           <li><code>500</code>: Ocurrió un error durante el envío del correo.</li>
     *         </ul>
     * @throws Exception Si ocurre un error no controlado al intentar enviar el correo.
     */
    Integer sendEmail(String email, String subject, Map<String, Object> dinamicContent, String emailId) throws Exception;

}
