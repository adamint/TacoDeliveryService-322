package com.adamratzman.delivery.data.dao;

import java.util.Collection;
import java.util.Optional;

public interface AbstractDao<T> {

  Optional<T> get(int id);

  Collection<T> getAll();

  int save(T t);

  void update(T t);

  void delete(T t);
}