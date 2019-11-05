package com.adamratzman.delivery.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tacos")
public class Taco {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @ElementCollection(targetClass = TacoIngredient.class)
  @CollectionTable
  @Column(name = "ingredients")
  @Enumerated(EnumType.STRING)
  private List<TacoIngredient> ingredients;

  public Taco(String name, List<TacoIngredient> ingredients) {
    this.name = name;
    this.ingredients = ingredients;
  }

  public Taco() {
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Taco(name=" + name + ", ingredients=" + ingredients + ")";
  }

  public float getPrice() {
    float price = 0;
    for (TacoIngredient ingredient : ingredients) price += ingredient.getPrice();
    return price;
  }

  public List<TacoIngredient> getIngredients() {
    return ingredients;
  }
}