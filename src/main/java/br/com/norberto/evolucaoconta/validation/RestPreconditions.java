package br.com.norberto.evolucaoconta.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestPreconditions {

  public static <T> T checkFound(T resource) {
    if (resource == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Not Found");
    }
    return resource;
  }
}
