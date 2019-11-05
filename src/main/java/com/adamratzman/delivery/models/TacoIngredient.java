package com.adamratzman.delivery.models;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TacoIngredient {
  FLOUR_TORTILLA("Flour Tortilla", 1f, IngredientType.WRAP),
  CORN_TORTILLA("Corn Tortilla", 1f, IngredientType.WRAP),
  GROUND_BEEF("Ground Beef", 2f, IngredientType.PROTEIN),
  CARNITAS("Carnitas", 1f, IngredientType.PROTEIN),
  DICED_TOMATOES("Diced Tomatoes", .5f, IngredientType.VEGGIES),
  LETTUCE("Lettuce", .3f, IngredientType.VEGGIES),
  CHEDDAR("Cheddar", .5f, IngredientType.CHEESE),
  MONTERREY_JACK("Monterrey Jack", 1f, IngredientType.CHEESE),
  SALSA("Salsa", 1f, IngredientType.SAUCE),
  SOUR_CREAM("Sour Cream", 1f, IngredientType.SAUCE);

  private String readable;
  private IngredientType type;
  private float price;

  TacoIngredient(String readable, float price, IngredientType type) {
    this.readable = readable;
    this.type = type;
    this.price = price;
  }

  public static List<Pair<IngredientType, List<TacoIngredient>>> getIngredientsGroupedByType() {
    return Stream.of(IngredientType.values())
            .map(ingredientType -> Pair.of(ingredientType, getIngredientsForType(ingredientType)))
            .collect(Collectors.toList());
  }

  public static List<TacoIngredient> getIngredientsForType(IngredientType type) {
    return Stream.of(TacoIngredient.values()).filter(tacoIngredient -> tacoIngredient.getType() == type)
            .collect(Collectors.toList());
  }

  String getName() {
    return name();
  }

  @Override
  public String toString() {
    return getName() + "(price=" + getPrice() + ", type=" + getType() + ")";
  }

  public IngredientType getType() {
    return type;
  }

  public String getReadable() {
    return readable;
  }

  public float getPrice() {
    return price;
  }

  public enum IngredientType {
    WRAP("Wrap"), PROTEIN("Protein"), VEGGIES("Veggies"),
    CHEESE("Cheese"), SAUCE("Sauce");

    private String readable;

    IngredientType(String readable) {
      this.readable = readable;
    }

    public String getReadable() {
      return readable;
    }
  }
}
