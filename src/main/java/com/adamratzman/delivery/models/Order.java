package com.adamratzman.delivery.models;

import com.adamratzman.delivery.authentication.User;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "tacoId", referencedColumnName = "id")
  private Taco taco;
  private float price;

  @OneToOne
  @JoinColumn(name = "customerId", referencedColumnName = "id")
  private User user;

  private boolean delivered;
  private boolean startedDelivery;

  public Order(Taco taco, float price, User user, boolean delivered, boolean startedDelivery) {
    this.taco = taco;
    this.price = price;
    this.user = user;
    this.delivered = delivered;
    this.startedDelivery = startedDelivery;
  }

  public Order() {
  }

  public long getId() {
    return id;
  }

  public Taco getTaco() {
    return taco;
  }

  public float getPrice() {
    return price;
  }

  public User getUser() {
    return user;
  }

  public boolean isDelivered() {
    return delivered;
  }

  public boolean isStartedDelivery() {
    return startedDelivery;
  }

  public void setStartedDelivery(boolean startedDelivery) {
    this.startedDelivery = startedDelivery;
  }
}