package com.adamratzman.delivery.controllers;

import com.adamratzman.delivery.data.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final OrderService orderService;

  public HomeController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/")
  public String getHome(Model model) {
    model.addAttribute("pageName", "Home");
    model.addAttribute("orders", orderService.findAll().size());

    return "index";
  }

  @GetMapping("/status")
  public String getStatus() {
    return "status";
  }
}
