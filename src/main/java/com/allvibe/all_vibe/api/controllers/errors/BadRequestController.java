package com.allvibe.all_vibe.api.controllers.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.allvibe.all_vibe.api.error_handler.BaseErrorResponse;
import com.allvibe.all_vibe.api.error_handler.ErrorResponse;
import com.allvibe.all_vibe.api.error_handler.ErrorsResponse;
import com.allvibe.all_vibe.util.exceptions.BadRequestException;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {
  @ExceptionHandler(BadRequestException.class)
  public BaseErrorResponse handleIdNotFound(BadRequestException exeption) {
    return ErrorResponse.builder()
        .message(exeption.getMessage())
        .status(HttpStatus.BAD_REQUEST.name())
        .code(HttpStatus.BAD_REQUEST.value())
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public BaseErrorResponse handleErrors(MethodArgumentNotValidException exception) {
    List<String> errors = new ArrayList<>();
    exception.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

    return ErrorsResponse.builder()
        .errors(errors)
        .status(HttpStatus.BAD_REQUEST.name())
        .code(HttpStatus.BAD_REQUEST.value())
        .build();
  }
}
