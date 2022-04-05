package com.api.goldentime.exception;

import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.ResponseDto.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler({
      NaverLoginFailException.class
  })
  public ResponseEntity<Object> RuntimeException(final RuntimeException ex) {
    ResponseDto<Object> response = ResponseDto.builder()
        .status(ResponseStatus.FAILURE)
        .message(ex.getMessage())
        .build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
