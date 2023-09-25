package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.curso.ecommerce.model.Producto;

public interface ProductoService {
  public Producto save(Producto producto);
  public Optional< Producto	> get(long id)  ;
  public void update(Producto producto);
  public void delete(long id);
  public List<Producto> findAll();
}
