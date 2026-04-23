package com.mycompany.restauranteelbuensabor.domain.model;

public class ItemPedido {
    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarCantidad(int cantidadAdicional) {
        this.cantidad += cantidadAdicional;
    }
}