/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Proceso {
    public static double calcularTotalFactura() {
        double subtotal = 0;
        double montoIVA = 0;
        double total = 0;
        double subtotalConDescuento = 0;
        int contadorItems = 0;
        int indice = 0;
        while (indice < Datos.nombresProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidadesProductos[indice];
                contadorItems = contadorItems + 1;
            }
            indice++;
        }
        if (contadorItems > 3) {
            if (subtotal > 0) {
                subtotalConDescuento = subtotal - (subtotal * 0.05);
                if (subtotalConDescuento > 50000) {
                    montoIVA = subtotalConDescuento * 0.19;
                    total = subtotalConDescuento + montoIVA;
                    total = total + (total * 0.10);
                } else {
                    montoIVA = subtotalConDescuento * 0.19;
                    total = subtotalConDescuento + montoIVA;
                }
            }
        } else {
            if (subtotal > 50000) {
                montoIVA = subtotal * 0.19;
                total = subtotal + montoIVA;
                total = total + (total * 0.10);
            } else {
                montoIVA = subtotal * 0.19;
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
        double propina = 0;
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
        if (numeroItems > 3) {
            resultado = resultado - (resultado * 0.01);
        }
        return resultado;
    }
}