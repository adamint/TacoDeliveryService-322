package com.adamratzman.delivery.authentication;

import org.springframework.security.core.AuthenticationException;

public class RegistrationException extends AuthenticationException {
  public RegistrationException(String msg, Throwable t) {
    super(msg, t);
  }

  public RegistrationException(String msg) {
    super(msg);
  }
}
