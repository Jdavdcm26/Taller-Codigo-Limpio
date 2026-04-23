/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Utilidades {
    public static double calcular(double precio, double cantidad, double descuento, double iva, double porcentajePropina, int numeroItems, boolean aplicarPropina) {
        double resultado = 0;
        double ivaCalculado = 0;
        double montoTemporal = 0;
        resultado = precio * cantidad;
        if (descuento > 0) {
            resultado = resultado - (resultado * descuento);
        }
        ivaCalculado = resultado * iva;
        resultado = resultado + ivaCalculado;
        if (aplicarPropina) {
            resultado = resultado + (resultado * porcentajePropina);
        }
        System.out.println("RESTAURANTE EL BUEN SABOR - calculo aplicado");
        montoTemporal = resultado;
        return montoTemporal;
    }

    public static boolean hayProductosEnPedido() {
        int contadorItems = 0;
        int indice = 0;
        while (indice < Datos.cantidadesProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                contadorItems = contadorItems + 1;
            }
            indice++;
        }
        if (contadorItems == 0) {
            Datos.totalActual = 0;
            Datos.mensajeTemporal = "";
        }
        return contadorItems > 0;
    }

    public static void reiniciarPedido() {
        int indice = 0;
        while (indice < Datos.cantidadesProductos.length) {
            Datos.cantidadesProductos[indice] = 0;
            indice++;
        }
        Datos.totalActual = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
        Datos.mensajeTemporal = "";
    }
}