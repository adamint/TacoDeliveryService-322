package com.adamratzman.delivery.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsWrapper extends User {
  private com.adamratzman.delivery.authentication.User user;

  public UserDetailsWrapper(com.adamratzman.delivery.authentication.User user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.user = user;
  }

  public UserDetailsWrapper(com.adamratzman.delivery.authentication.User user, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.user = user;
  }

  public com.adamratzman.delivery.authentication.User getUser() {
    return user;
  }
}
