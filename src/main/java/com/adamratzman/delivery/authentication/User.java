package com.adamratzman.delivery.authentication;

import com.adamratzman.delivery.models.TacoIngredient;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String firstName;
  private String lastName;

  private String phoneNumber;

  private String password;

  @ElementCollection(targetClass = Role.class)
  @CollectionTable
  @Column(name = "roles")
  @Enumerated(EnumType.STRING)
  private List<Role> roles;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public long getId() {
    return id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}