package com.es.mercadona.service;

import java.util.Optional;

import com.es.mercadona.entity.Product;

public interface IProductService {
    Product crear(Product auto);
    Optional<Product> obtenerPorCodigo(String codigo);
    Product actualizar(String codigo, String nombre);
    boolean eliminar(Long id);

}
