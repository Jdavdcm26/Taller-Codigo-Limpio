package com.mycompany.restauranteelbuensabor.controller;

import com.mycompany.restauranteelbuensabor.config.Constantes;

public class ServicioFacturacion {

    public static double calcularSubtotalConDescuento(double subtotal, int cantidadItems) {
        if (aplicaDescuento(cantidadItems)) {
            return subtotal - (subtotal * Constantes.TASA_DESCUENTO);
        }
        return subtotal;
    }

    public static double calcularIVA(double base) {
        return base * Constantes.TASA_IVA;
    }

    public static double calcularPropina(double totalConIVA, double base) {
        if (base > Constantes.UMBRAL_PROPINA) {
            return totalConIVA * Constantes.TASA_PROPINA;
        }
        return 0;
    }

    public static boolean aplicaDescuento(int cantidadItems) {
        return cantidadItems > Constantes.MIN_ITEMS_DESCUENTO;
    }

    public static double[] calcularTotales(double subtotal, int cantidadItems) {
        double base = calcularSubtotalConDescuento(subtotal, cantidadItems);
        double montoIVA = calcularIVA(base);
        double totalConIVA = base + montoIVA;
        double montoPropina = calcularPropina(totalConIVA, base);
        double total = totalConIVA + montoPropina;
        return new double[]{base, montoIVA, total, montoPropina};
    }
}