package com.auth.services.impl;

import com.auth.services.EmailService;
import com.auth.utilities.Constants;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación del servicio {@link EmailService} para el envío de correos electrónicos.
 * <p>
 * Utiliza la API de SendGrid para enviar correos electrónicos personalizados con plantillas dinámicas.
 * Permite incluir contenido dinámico que se inserta en las plantillas configuradas en SendGrid.
 * </p>
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public Integer sendEmail(String email, String subject, Map<String, Object> dynamicContent, String templateId) throws Exception {

        Map<String, Object> mutableContent = new HashMap<>(dynamicContent);

        mutableContent.put("subject", subject); // Agregar el asunto al contenido dinámico

        // Crear el correo
        Mail mail = new Mail();
        mail.setFrom(new Email(Constants.FROM_EMAIL));
        mail.setTemplateId(templateId);

        // Configurar destinatario y contenido dinámico
        Personalization personalization = new Personalization();
        personalization.addTo(new Email(email));
        mutableContent.forEach(personalization::addDynamicTemplateData);
        mail.addPersonalization(personalization);

        // Enviar correo mediante SendGrid
        SendGrid sg = new SendGrid(Constants.API_KEY);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            return response.getStatusCode(); // Devuelve el código de estado
        } catch (IOException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            return 500; // Indica que ocurrió un error
        }
    }
    
}
