package com.backend.resources.exceptions;

import com.backend.service.exceptions.DataIntegrityViolationException;
import com.backend.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourcesExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException xObjNotF, HttpServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Object Not Found | Objeto não encontrado", xObjNotF.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // * --> DataIntegrityViolationException --> Para tratar a exceção de CPF já existente
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolation, HttpServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Breach | Violação de Dados", dataIntegrityViolation.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErros(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {
        ValidationError errorValidation = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "⚠️⚠️ Error validating Fields | Erro na validação dos Campos", "Error validating Fields | Erro na validação dos Campos",
                request.getRequestURI());

        for(FieldError xFieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorValidation.addErrors(xFieldError.getField(), xFieldError.getDefaultMessage());
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorValidation);
    }
}
