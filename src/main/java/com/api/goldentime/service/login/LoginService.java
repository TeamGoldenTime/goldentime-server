package com.api.goldentime.service.login;

import com.api.goldentime.domain.user.User;

public interface LoginService<DTO, T> {
  T getProfile(DTO dto);
  User login(T profile);
}
