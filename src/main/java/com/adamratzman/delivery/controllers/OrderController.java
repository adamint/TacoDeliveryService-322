package com.adamratzman.delivery.controllers;

import com.adamratzman.delivery.authentication.Role;
import com.adamratzman.delivery.authentication.User;
import com.adamratzman.delivery.authentication.UserDetailsWrapper;
import com.adamratzman.delivery.data.service.OrderService;
import com.adamratzman.delivery.models.Order;
import com.adamratzman.delivery.models.Taco;
import com.adamratzman.delivery.models.TacoIngredient;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/view/{orderId}")
  public String viewOrder(Authentication authentication, @PathVariable(name = "orderId") long orderId, Model model) {
    Optional<Order> orderOpt = orderService.findOrder(orderId);
    if (!orderOpt.isPresent()) return "redirect:/";

    Order order = orderOpt.get();

    User user = ((UserDetailsWrapper) authentication.getPrincipal()).getUser();

    if (!order.getUser().getPhoneNumber().equals(user.getPhoneNumber()) && user.getRoles().contains(Role.STAFF)) {
      return "redirect:/";
    }

    model.addAttribute("order", order);

    return "viewOrder";
  }

  @GetMapping("/new")
  public String newOrder(Model model) {
    List<Pair<TacoIngredient.IngredientType, List<TacoIngredient>>> tacoPairs = TacoIngredient.getIngredientsGroupedByType();

    model.addAttribute("tacoPairs", tacoPairs);
    model.addAttribute("pageName", "Order a taco");

    return "newOrder";
  }

  @PostMapping("/new")
  public String newOrder(Authentication authentication,
                         @RequestParam(name = "tacoName") String tacoName,
                         @RequestParam(name = "choose-WRAP") String wrapName,
                         @RequestParam(name = "choose-PROTEIN") String proteinName,
                         @RequestParam(name = "choose-VEGGIES") String veggiesName,
                         @RequestParam(name = "choose-CHEESE") String cheeseName,
                         @RequestParam(name = "choose-SAUCE") String sauceName) {
    if (wrapName == null || proteinName == null || veggiesName == null || cheeseName == null
            || sauceName == null || tacoName == null || tacoName.length() < 3)
      return "redirect:/orders/new";
    try {
      UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
      User user = userDetailsWrapper.getUser();

      TacoIngredient wrap = TacoIngredient.valueOf(wrapName);
      TacoIngredient protein = TacoIngredient.valueOf(proteinName);
      TacoIngredient veggies = TacoIngredient.valueOf(veggiesName);
      TacoIngredient cheese = TacoIngredient.valueOf(cheeseName);
      TacoIngredient sauce = TacoIngredient.valueOf(sauceName);

      Taco taco = new Taco(tacoName, Arrays.asList(wrap, protein, veggies, cheese, sauce));
      float price = 0;
      for (TacoIngredient ingredient : taco.getIngredients()) price += ingredient.getPrice();

      Order order = new Order(taco, price, user, false, false);

      orderService.addOrUpdateOrder(order);

      return "redirect:/orders/view/" + order.getId();
    } catch (IllegalArgumentException iae) {
      return "redirect:/orders/new";
    }
  }
}
