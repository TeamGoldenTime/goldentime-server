package com.api.goldentime.exception;

public class NaverLoginFailException extends RuntimeException {

  public NaverLoginFailException() {
    super("네이버 로그인 API 실패");
  }

  public NaverLoginFailException(String message) {
    super(message);
  }

  public NaverLoginFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public NaverLoginFailException(Throwable cause) {
    super(cause);
  }

  public NaverLoginFailException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
