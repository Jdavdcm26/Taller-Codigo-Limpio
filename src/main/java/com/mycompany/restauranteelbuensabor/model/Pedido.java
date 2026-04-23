package com.mycompany.restauranteelbuensabor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa un pedido de una mesa.
 */
public class Pedido {
    private final List<ItemPedido> items;
    private int numeroMesa;

    public Pedido() {
        this.items = new ArrayList<>();
        this.numeroMesa = 0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemPedido item : items) {
            if (item.getProducto() == producto) {
                item.agregarCantidad(cantidad);
                return;
            }
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public int getCantidadItems() {
        return items.size();
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public void reiniciar() {
        items.clear();
        numeroMesa = 0;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }
}