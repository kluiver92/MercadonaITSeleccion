package com.es.mercadona.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.es.mercadona.entity.Product;
import com.es.mercadona.service.IProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductResource {

	@Autowired
	private IProductService productService;

	@PostMapping("products")
	public ResponseEntity<Product> crearProducto(@RequestBody @Valid Product product) {
		return new ResponseEntity<Product>(this.productService.crear(product), HttpStatus.CREATED);
	}

	@GetMapping("/products/{codigo}")
	public ResponseEntity<Product> obtenerProducto(@PathVariable("codigo") String codigo) {
		Optional<Product> product = this.productService.obtenerPorCodigo(codigo);
		if (product.isPresent())
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/products/{codigo}/nuevoNombre/{nombre}")
	public ResponseEntity<Product> actualizarProducto(@PathVariable("codigo") String codigo,
			@PathVariable("nombre") String nombre) {
		Product autoActualizado = this.productService.actualizar(codigo, nombre);
		if (autoActualizado != null) {
			return new ResponseEntity<Product>(autoActualizado, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/products/{codigo}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable("codigo") String codigo) {
		Optional<Product> product = this.productService.obtenerPorCodigo(codigo);
		if (product.isPresent())
			this.productService.eliminar(product.get().getId());
		return ResponseEntity.noContent().build();
	}
}
