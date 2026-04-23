package com.mycompany.restauranteelbuensabor;

public class Factura {
    private static int secuencial = 1;

    private final int numeroFactura;
    private final Pedido pedido;
    private final double subtotal;
    private final double descuento;
    private final double montoIVA;
    private final double montoPropina;
    private final double total;

    public Factura(Pedido pedido) {
        this.pedido = pedido;
        this.numeroFactura = secuencial++;

        double base = pedido.getSubtotal();
        int cantidadItems = pedido.getCantidadItems();

        if (ServicioFacturacion.aplicaDescuento(cantidadItems)) {
            this.descuento = base * Constantes.TASA_DESCUENTO;
            base = base - this.descuento;
        } else {
            this.descuento = 0;
        }

        this.subtotal = base;
        this.montoIVA = ServicioFacturacion.calcularIVA(base);
        double totalConIVA = base + this.montoIVA;
        this.montoPropina = ServicioFacturacion.calcularPropina(totalConIVA, base);
        this.total = totalConIVA + this.montoPropina;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getMontoIVA() {
        return montoIVA;
    }

    public double getMontoPropina() {
        return montoPropina;
    }

    public double getTotal() {
        return total;
    }
}