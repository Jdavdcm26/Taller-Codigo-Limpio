package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.model.Pedido;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa una factura del restaurante.
 */
public class Factura {
    private static final IFacturacionService SERVICIO = new FacturacionService();
    private static final AtomicInteger SECUENCIAL = new AtomicInteger(1);

    private final int numeroFactura;
    private final Pedido pedido;
    private final double subtotal;
    private final double descuento;
    private final double montoIVA;
    private final double montoPropina;
    private final double total;

    public Factura(Pedido pedido) {
        this.pedido = pedido;
        this.numeroFactura = SECUENCIAL.getAndIncrement();

        double base = pedido.getSubtotal();
        int cantidadItems = pedido.getCantidadItems();

        boolean aplicarDescuento = SERVICIO.aplicaDescuento(cantidadItems);
        if (aplicarDescuento) {
            this.descuento = base * 0.05;
            base = base - this.descuento;
        } else {
            this.descuento = 0;
        }

        this.subtotal = base;
        this.montoIVA = SERVICIO.calcularIVA(base);
        double totalConIVA = base + this.montoIVA;
        this.montoPropina = SERVICIO.calcularPropina(totalConIVA, base);
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