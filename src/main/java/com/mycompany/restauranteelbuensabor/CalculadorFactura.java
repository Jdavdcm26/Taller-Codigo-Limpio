package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {
    public static double calcularTotalFactura() {
        double subtotal = Utilidades.calcularSubtotalPedido();
        int contadorItems = Utilidades.contarItemsPedido();
        double montoIVA = 0;
        double total = 0;
        double subtotalConDescuento = 0;
        if (contadorItems > Constantes.MIN_ITEMS_DESCUENTO) {
            if (subtotal > 0) {
                subtotalConDescuento = subtotal - (subtotal * Constantes.TASA_DESCUENTO);
                if (subtotalConDescuento > Constantes.UMBRAL_PROPINA) {
                    montoIVA = subtotalConDescuento * Constantes.TASA_IVA;
                    total = subtotalConDescuento + montoIVA;
                    total = total + (total * Constantes.TASA_PROPINA);
                } else {
                    montoIVA = subtotalConDescuento * Constantes.TASA_IVA;
                    total = subtotalConDescuento + montoIVA;
                }
            }
        } else {
            if (subtotal > Constantes.UMBRAL_PROPINA) {
                montoIVA = subtotal * Constantes.TASA_IVA;
                total = subtotal + montoIVA;
                total = total + (total * Constantes.TASA_PROPINA);
            } else {
                montoIVA = subtotal * Constantes.TASA_IVA;
                total = subtotal + montoIVA;
            }
        }
        Datos.estadoMesa = 1;
        Datos.totalActual = total;
        return total;
    }

    public static double procesar(double precio, double cantidad, double descuento, double iva, double porcentajePropina, int numeroItems, boolean aplicarPropina) {
        double resultado = 0;
        double montoIVA = 0;
        double montoTemporal = 0;
        resultado = precio * cantidad;
        if (descuento > 0) {
            resultado = resultado - (resultado * descuento);
        }
        montoIVA = resultado * iva;
        montoTemporal = montoIVA;
        resultado = resultado + montoTemporal;
        if (aplicarPropina) {
            cantidad = resultado * porcentajePropina;
            resultado = resultado + cantidad;
        }
        if (numeroItems > Constantes.MIN_ITEMS_DESCUENTO) {
            resultado = resultado - (resultado * 0.01);
        }
        return resultado;
    }
}