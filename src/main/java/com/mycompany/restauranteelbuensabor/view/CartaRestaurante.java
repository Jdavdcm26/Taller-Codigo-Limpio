package com.mycompany.restauranteelbuensabor.view;

import com.mycompany.restauranteelbuensabor.config.Constantes;
import com.mycompany.restauranteelbuensabor.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class CartaRestaurante {
    private final List<Producto> productos;

    public CartaRestaurante() {
        this.productos = new ArrayList<>();
        productos.add(new Producto("Bandeja Paisa", 32000));
        productos.add(new Producto("Sancocho de Gallina", 28000));
        productos.add(new Producto("Arepa con Huevo", 8000));
        productos.add(new Producto("Jugo Natural", 7000));
        productos.add(new Producto("Gaseosa", 4500));
        productos.add(new Producto("Cerveza Poker", 6000));
        productos.add(new Producto("Agua Panela", 3500));
        productos.add(new Producto("Arroz con Pollo", 25000));
    }

    public void mostrarCarta() {
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(Constantes.SEPARADOR);
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.printf("%d. %-22s $%,.0f%n", i + 1, p.getNombre(), p.getPrecio());
        }
        System.out.println(Constantes.SEPARADOR);
    }

    public Producto getProducto(int indice) {
        return productos.get(indice - 1);
    }

    public int getCantidadProductos() {
        return productos.size();
    }
}