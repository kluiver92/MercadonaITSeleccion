package com.es.mercadona.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.es.mercadona.entity.Product;
import com.es.mercadona.repository.ProductRepository;
import com.es.mercadona.utils.Constans;

@Service
public class IProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product crear(Product product) {
		this.saveProvedor(product);
		this.saveDestino(product);
		return this.productRepository.save(product);
	}

	@Override
	public Optional<Product> obtenerPorCodigo(String codigo) {
		return this.productRepository.findByCodigo(codigo);
	}

	@Override
	public Product actualizar(String codigo, String nombre) {
		Optional<Product> product = this.productRepository.findByCodigo(codigo);
		if (product.isPresent()) {
			Product prod = product.get();
			prod.setNombre(nombre);
			return crear(prod);
		}
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		this.productRepository.deleteById(id);
		return true;
	}

	public void saveDestino(Product product) {
		Long digit = Math.abs(Long.parseLong(product.getCodigo()) % 10);
		switch (digit.toString()) {
		case "6":
			product.setDestino(Constans.MERCA_PORT);
			break;
		case "7":
			break;
		case "8":
			product.setDestino(Constans.ALMACENES);
			break;
		case "9":
			product.setDestino(Constans.OFI_MERCA);
			break;
		case "0":
			product.setDestino(Constans.COLMENAS);
			break;

		default:
			product.setDestino(Constans.MERCA_ESP);
			break;
		}
	}

	private void saveProvedor(Product product) {
		String firstDigit = String.valueOf(product.getCodigo()).substring(0, 7);

		if (firstDigit.equals(Constans.COD_HACENDADO)) {
			product.setProveedor(Constans.HACENDADO);
		} else {
			product.setProveedor(Constans.PROD_EXT);

		}
	}

}
