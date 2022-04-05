package com.api.goldentime.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseDto<T> {
  public interface ResponseStatus {
    String SUCCESS = "success";
    String FAILURE = "failure";
  }

  private String status;
  private String message;
  private T data;
}
