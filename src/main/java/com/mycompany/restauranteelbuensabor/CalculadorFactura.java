package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {
    public static double calcularTotalFactura() {
        double subtotal = Utilidades.calcularSubtotalPedido();
        int cantidadItems = Utilidades.contarItemsPedido();
        double base = calcularSubtotalConDescuento(subtotal, cantidadItems);
        double conIVA = calcularIVA(base);
        double total = calcularPropina(conIVA, base);
        Datos.estadoMesa = 1;
        Datos.totalActual = total;
        return total;
    }

    public static double calcularSubtotalConDescuento(double subtotal, int cantidadItems) {
        if (cantidadItems > Constantes.MIN_ITEMS_DESCUENTO) {
            return subtotal - (subtotal * Constantes.TASA_DESCUENTO);
        }
        return subtotal;
    }

    public static double calcularIVA(double base) {
        return base * Constantes.TASA_IVA;
    }

    public static double calcularPropina(double totalConIVA, double base) {
        if (base > Constantes.UMBRAL_PROPINA) {
            return totalConIVA + (totalConIVA * Constantes.TASA_PROPINA);
        }
        return totalConIVA;
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