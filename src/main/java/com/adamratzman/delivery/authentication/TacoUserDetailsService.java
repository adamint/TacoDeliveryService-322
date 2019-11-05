package com.adamratzman.delivery.authentication;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TacoUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public TacoUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
    User user = userRepository.findByPhoneNumber(phoneNumber);
    if (user == null) throw new UsernameNotFoundException("No user with phone number " + phoneNumber);

    return new UserDetailsWrapper(
            user,
            user.getPhoneNumber(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            getAuthoritiesFor(user)
    );
  }

  List<SimpleGrantedAuthority> getAuthoritiesFor(User user) {
    return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
  }
}
