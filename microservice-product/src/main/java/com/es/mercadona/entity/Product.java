package com.es.mercadona.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    
    @NotBlank(message = "El codigo Producto no puede ser blanco")
    @Length(min = 13, max = 13, message = "El codigo producto debe tener 13 caracteres")
    private String codigo;
    
    private String proveedor;
    private String destino;
}