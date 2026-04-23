package com.mycompany.restauranteelbuensabor.presentation;

import com.mycompany.restauranteelbuensabor.domain.model.Mesa;
import com.mycompany.restauranteelbuensabor.domain.model.Producto;
import com.mycompany.restauranteelbuensabor.infrastructure.CartaRestaurante;
import com.mycompany.restauranteelbuensabor.infrastructure.IImpresora;
import com.mycompany.restauranteelbuensabor.infrastructure.ImpresoraConsola;
import com.mycompany.restauranteelbuensabor.service.Factura;

public class SistemaRestaurante {
    private final CartaRestaurante carta;
    private final Mesa mesa;
    private final IImpresora impresora;

    public SistemaRestaurante() {
        this(new ImpresoraConsola());
    }

    public SistemaRestaurante(IImpresora impresora) {
        this.carta = new CartaRestaurante();
        this.mesa = new Mesa();
        this.impresora = impresora;
    }

    public void ocuparMesa(int numeroMesa) {
        mesa.ocupar(numeroMesa);
    }

    public void agregarProducto(int numeroProducto, int cantidad) {
        Producto producto = carta.getProducto(numeroProducto);
        mesa.getPedido().agregarProducto(producto, cantidad);
    }

    public void mostrarCarta() {
        carta.mostrarCarta();
    }

    public void mostrarPedido() {
        if (mesa.getPedido().tieneProductos()) {
            impresora.mostrarPedido(mesa.getPedido());
        } else {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        }
    }

    public void generarFactura() {
        if (!mesa.getPedido().tieneProductos()) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }
        Factura factura = new Factura(mesa.getPedido());
        impresora.imprimirFactura(factura);
    }

    public void nuevaMesa() {
        mesa.liberar();
    }

    public boolean mesaEstaOcupada() {
        return mesa.estaOcupada();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public CartaRestaurante getCarta() {
        return carta;
    }
}