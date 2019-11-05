package com.adamratzman.delivery.controllers;

import com.adamratzman.delivery.authentication.User;
import com.adamratzman.delivery.authentication.UserDetailsWrapper;
import com.adamratzman.delivery.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/me")
public class AccountController {
  private final OrderService orderService;

  public AccountController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/")
  public String accountHome(Authentication authentication, Model model) {
    User user = ((UserDetailsWrapper) authentication.getPrincipal()).getUser();

    model.addAttribute("user", user);
    model.addAttribute("orders", orderService.findAll().stream()
            .filter(order -> !order.isDelivered() &&
                    order.getUser().getPhoneNumber().equals(user.getPhoneNumber())).collect(Collectors.toList()));

    return "me";
  }
}
