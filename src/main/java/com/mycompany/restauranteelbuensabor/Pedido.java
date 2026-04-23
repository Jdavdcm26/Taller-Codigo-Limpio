package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

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
                sincronizarConLegacy(producto, item.getCantidad());
                return;
            }
        }
        items.add(new ItemPedido(producto, cantidad));
        sincronizarConLegacy(producto, cantidad);
    }

    private void sincronizarConLegacy(Producto producto, int cantidad) {
        int indice = encontrarIndiceProducto(producto);
        if (indice >= 0) {
            Datos.cantidadesProductos[indice] = cantidad;
        }
    }

    private int encontrarIndiceProducto(Producto producto) {
        CartaRestaurante carta = new CartaRestaurante();
        for (int i = 0; i < carta.getCantidadProductos(); i++) {
            if (carta.getProducto(i + 1) == producto) {
                return i;
            }
        }
        return -1;
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

    public boolean hayProductos() {
        return !items.isEmpty();
    }

    public void reiniciar() {
        items.clear();
        numeroMesa = 0;
        limpiarLegacy();
    }

    private void limpiarLegacy() {
        for (int i = 0; i < Datos.cantidadesProductos.length; i++) {
            Datos.cantidadesProductos[i] = 0;
        }
        Datos.totalActual = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<ItemPedido> getItems() {
        return items;
    }
}