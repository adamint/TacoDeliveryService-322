package com.adamratzman.delivery.controllers;

import com.adamratzman.delivery.authentication.LoginDto;
import com.adamratzman.delivery.authentication.User;
import com.adamratzman.delivery.authentication.UserDto;
import com.adamratzman.delivery.authentication.UserService;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
  private final UserService userService;

  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("user", new UserDto());
    model.addAttribute("pageName", "Register");

    return "register";
  }

  @PostMapping("/register")
  public ModelAndView registerAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult result,
                                      WebRequest request,
                                      Errors errors) {
    User registered = new User();
    if (!result.hasErrors()) {
      registered = createUserAccount(userDto);
    }

    if (registered == null) {
      result.rejectValue("phoneNumber", "message.regError");
    }

    if (errors.hasErrors()) {
      return new ModelAndView("register", "user", userDto);
    } else {
      return new ModelAndView("registrationSuccess", "user", userDto);
    }
  }

  private User createUserAccount(UserDto accountDto) {
    User registered;
    try {
      registered = userService.registerNewUserAccount(accountDto);
    } catch (AuthenticationException e) {
      registered = null;
    }
    return registered;
  }

  @GetMapping("/login")
  public String login(@Nullable @RequestParam("failure") String failure, Model model) {
    model.addAttribute("login", new LoginDto());
    model.addAttribute("pageName", "Login");
    if (failure != null) model.addAttribute("failure", true);

    return "login";
  }

}
