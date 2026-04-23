package com.mycompany.restauranteelbuensabor.domain.model;

/**
 * Representa un producto del menú del restaurante.
 */
public class Producto {
    private final String nombre;
    private final double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}