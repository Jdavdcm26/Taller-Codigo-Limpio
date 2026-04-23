package com.mycompany.restauranteelbuensabor;

public class Imprimir {
    public static void mostrarCarta() {
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(Constantes.SEPARADOR);
        int indice = 0;
        while (indice < Datos.nombresProductos.length) {
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), Datos.nombresProductos[indice], Datos.precios[indice]);
            indice++;
        }
        System.out.println(Constantes.SEPARADOR);
    }

    public static void mostrarPedido() {
        double subtotal = 0;
        int indice = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        while (indice < Datos.nombresProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", Datos.nombresProductos[indice], Datos.cantidadesProductos[indice], (Datos.precios[indice] * Datos.cantidadesProductos[indice]));
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidadesProductos[indice];
            }
            indice++;
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        double subtotal = Utilidades.calcularSubtotalPedido();
        int contadorItems = Utilidades.contarItemsPedido();
        double montoIVA = 0;
        double total = 0;
        double propina = 0;
        double subtotalConDescuento = 0;
        if (contadorItems > Constantes.MIN_ITEMS_DESCUENTO) {
            subtotalConDescuento = subtotal - (subtotal * Constantes.TASA_DESCUENTO);
        } else {
            subtotalConDescuento = subtotal;
        }
        if (subtotalConDescuento > Constantes.UMBRAL_PROPINA) {
            montoIVA = subtotalConDescuento * Constantes.TASA_IVA;
            total = subtotalConDescuento + montoIVA;
            propina = total * Constantes.TASA_PROPINA;
            total = total + propina;
        } else {
            montoIVA = subtotalConDescuento * Constantes.TASA_IVA;
            total = subtotalConDescuento + montoIVA;
            propina = 0;
        }
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    " + Constantes.DIRECCION);
        System.out.println("    NIT: " + Constantes.NIT);
        System.out.println(Constantes.SEPARADOR);
        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println(Constantes.SEPARADOR_ITEM);
        int indiceProductos = 0;
        while (indiceProductos < Datos.nombresProductos.length) {
            if (Datos.cantidadesProductos[indiceProductos] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", Datos.nombresProductos[indiceProductos], Datos.cantidadesProductos[indiceProductos], (Datos.precios[indiceProductos] * Datos.cantidadesProductos[indiceProductos]));
            }
            indiceProductos++;
        }
        System.out.println(Constantes.SEPARADOR_ITEM);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", montoIVA);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println(Constantes.SEPARADOR_ITEM);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(Constantes.SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println(Constantes.NOMBRE_RESTAURANTE + " - " + Constantes.DIRECCION.split(",")[0]);
        System.out.println(Constantes.SEPARADOR);
        Datos.numeroFactura = Datos.numeroFactura + 1;
        Datos.estadoMesa = 0;
        Datos.totalActual = total;
    }

    public static void imprimirFacturaResumen() {
        double subtotal = Utilidades.calcularSubtotalPedido();
        int contadorItems = Utilidades.contarItemsPedido();
        double montoIVA = 0;
        double total = 0;
        double propina = 0;
        double subtotalConDescuento = 0;
        if (contadorItems > Constantes.MIN_ITEMS_DESCUENTO) {
            subtotalConDescuento = subtotal - (subtotal * Constantes.TASA_DESCUENTO);
        } else {
            subtotalConDescuento = subtotal;
        }
        if (subtotalConDescuento > Constantes.UMBRAL_PROPINA) {
            montoIVA = subtotalConDescuento * Constantes.TASA_IVA;
            total = subtotalConDescuento + montoIVA;
            propina = total * Constantes.TASA_PROPINA;
            total = total + propina;
        } else {
            montoIVA = subtotalConDescuento * Constantes.TASA_IVA;
            total = subtotalConDescuento + montoIVA;
            propina = 0;
        }
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    " + Constantes.DIRECCION);
        System.out.println("    NIT: " + Constantes.NIT);
        System.out.println(Constantes.SEPARADOR);
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.numeroFactura);
        System.out.println(Constantes.SEPARADOR_ITEM);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", montoIVA);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println(Constantes.SEPARADOR_ITEM);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(Constantes.SEPARADOR);
    }
}