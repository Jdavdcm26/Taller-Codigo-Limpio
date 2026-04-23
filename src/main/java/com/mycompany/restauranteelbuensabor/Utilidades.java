package com.mycompany.restauranteelbuensabor;

public class Utilidades {
    public static double calcularSubtotalPedido() {
        double subtotal = 0;
        int indice = 0;
        while (indice < Datos.cantidadesProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidadesProductos[indice];
            }
            indice++;
        }
        return subtotal;
    }

    public static int contarItemsPedido() {
        int contador = 0;
        int indice = 0;
        while (indice < Datos.cantidadesProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                contador = contador + 1;
            }
            indice++;
        }
        return contador;
    }

    public static boolean hayProductosEnPedido() {
        int contadorItems = contarItemsPedido();
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