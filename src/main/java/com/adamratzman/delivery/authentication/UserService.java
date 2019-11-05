package com.adamratzman.delivery.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;

    if (!userRepository.findById(0L).isPresent()) {
      registerNewUserAccount(new UserDto("C322", "Student", "password", "123456789"));
    }
  }

  @Transactional
  public User registerNewUserAccount(UserDto userDto) throws AuthenticationException {
    if (userRepository.findByPhoneNumber(userDto.getPhoneNumber()) != null) {
      throw new RegistrationException("An account with phone number " + userDto.getPhoneNumber() + " already exists");
    }

    User user = new User();

    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setRoles(Collections.singletonList(Role.USER));

    return userRepository.save(user);
  }
}
