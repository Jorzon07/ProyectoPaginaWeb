package com.JorzonWeb.Proyecto.servlet;

import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.exception.ServiceException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

/**
 * Controlador de asesoramiento para manejar excepciones globales en la aplicación.
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    /**
     * Maneja las excepciones de violación de restricciones de validación.
     *
     * @param e      La excepción de ConstraintViolation.
     * @param locale El locale utilizado para la internacionalización.
     * @return Una respuesta con el mensaje de error y la lista de errores de validación.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Respuesta constraintsValidationHandler(ConstraintViolationException e, Locale locale) {

        List<String> errors = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage()).toList();

        return Respuesta.builder()
                .mensaje(this.messageSource.getMessage("global.error.found", null, locale))
                .errores(errors)
                .build();
    }

    /**
     * Maneja las excepciones de argumentos de método no válidos.
     *
     * @param e      La excepción de MethodArgumentNotValid.
     * @param locale El locale utilizado para la internacionalización.
     * @return Una respuesta con el mensaje de error y la lista de errores de validación.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Respuesta methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, Locale locale) {

        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage()).toList();

        return Respuesta.builder()
                .mensaje(this.messageSource.getMessage("global.error.found", null, locale))
                .errores(errors)
                .build();
    }

    /**
     * Maneja las excepciones de servicio personalizadas.
     *
     * @param e La excepción de ServiceException.
     * @return Una respuesta con el mensaje de error.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public Respuesta serviceExceptionHandler(ServiceException e) {
        return Respuesta.builder()
                .mensaje(e.getMessage())
                .build();
    }

    /**
     * Maneja las excepciones de autenticación.
     *
     * @param ex     La excepción de AuthenticationException.
     * @param locale El locale utilizado para la internacionalización.
     * @return Una respuesta con el mensaje de error de autenticación.
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Respuesta handleAuthenticationException(Exception ex, Locale locale) {
        return Respuesta.builder()
                .mensaje(this.messageSource.getMessage("login.error", null, locale))
                .build();
    }
}
