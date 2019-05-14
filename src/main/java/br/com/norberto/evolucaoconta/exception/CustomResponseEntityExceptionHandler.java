package br.com.norberto.evolucaoconta.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

    ResponseException responseException =
        new ResponseException(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
