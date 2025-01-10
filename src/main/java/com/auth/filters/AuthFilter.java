package com.auth.filters;

import com.auth.utilities.Constants;
import com.auth.utilities.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * Filtro de autenticación para solicitudes HTTP.
 * <p>
 * Este filtro verifica la presencia y validez de un token JWT en el encabezado <code>Authorization</code>
 * de cada solicitud. Si el token es válido, extrae el identificador del usuario (<code>userId</code>) y lo
 * agrega como atributo en la solicitud. Si el token es inválido o no se proporciona, se devuelve un error
 * HTTP <code>401 Unauthorized</code>.
 * </p>
 */
public class AuthFilter extends GenericFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Filtra las solicitudes HTTP para verificar la autenticación mediante un token JWT.
     * <p>
     * Este método:
     * <ul>
     *     <li>Valida que el encabezado <code>Authorization</code> esté presente y tenga un formato correcto.</li>
     *     <li>Valida la firma y los datos del token JWT.</li>
     *     <li>Extrae el atributo <code>userId</code> del token y lo agrega a la solicitud.</li>
     *     <li>Devuelve un error HTTP <code>401 Unauthorized</code> si el token es inválido, ha expirado,
     *     o si el encabezado <code>Authorization</code> no está presente.</li>
     * </ul>
     * </p>
     *
     * @param servletRequest  La solicitud entrante.
     * @param servletResponse La respuesta saliente.
     * @param filterChain     La cadena de filtros para procesar la solicitud.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error en el procesamiento del filtro.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader != null) {
            String[] authHeaderArr = authHeader.split("Bearer ");
            if(authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];
                try {
                    Claims claims = jwtUtil.getClaims(token);
                    httpServletRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));

                } catch (Exception e){
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid/expired token");
                }
            } else {
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization token must be Bearer [token]");
            }
        } else {
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization must be provided");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
