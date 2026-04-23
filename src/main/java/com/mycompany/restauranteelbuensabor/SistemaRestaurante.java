package com.mycompany.restauranteelbuensabor;

public class SistemaRestaurante {
    private final CartaRestaurante carta;
    private final Mesa mesa;

    public SistemaRestaurante() {
        this.carta = new CartaRestaurante();
        this.mesa = new Mesa();
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
        if (mesa.getPedido().hayProductos()) {
            Imprimir.mostrarPedido(mesa.getPedido());
        } else {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        }
    }

    public void generarFactura() {
        if (!mesa.getPedido().hayProductos()) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }
        Factura factura = new Factura(mesa.getPedido());
        Imprimir.imprimirFactura(factura);
    }

    public void nuevaMesa() {
        mesa.liberar();
    }

    public boolean isMesaOcupada() {
        return mesa.isOcupada();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public CartaRestaurante getCarta() {
        return carta;
    }
}