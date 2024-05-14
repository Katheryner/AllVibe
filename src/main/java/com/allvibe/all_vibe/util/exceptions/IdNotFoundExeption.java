package com.allvibe.all_vibe.util.exceptions;

public class IdNotFoundExeption extends RuntimeException {
  private static final String ERROR_MESSAGE = "No hay registros en la entidad %s con el id suministrado.";

  public IdNotFoundExeption(String nameEntity) {
    super(String.format(ERROR_MESSAGE, nameEntity));
  }
}
