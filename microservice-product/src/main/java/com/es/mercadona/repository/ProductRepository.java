package com.es.mercadona.repository;
import org.springframework.data.repository.CrudRepository;

import com.es.mercadona.entity.Product;

import java.util.Optional;


public interface ProductRepository extends CrudRepository<Product, Long>  {
	Optional<Product> findByCodigo(String codigo);
	void deleteByCodigo(String codigo);
}
