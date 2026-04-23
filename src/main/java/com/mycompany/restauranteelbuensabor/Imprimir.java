/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Imprimir {
    public static void mostrarCarta() {
        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println("========================================");
        int indice = 0;
        while (indice < Datos.nombresProductos.length) {
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), Datos.nombresProductos[indice], Datos.precios[indice]);
            indice++;
        }
        System.out.println("========================================");
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
        double subtotal = 0;
        double montoIVA = 0;
        double total = 0;
        double propina = 0;
        int contadorItems = 0;
        double subtotalConDescuento = 0;
        int indice = 0;
        while (indice < Datos.nombresProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidadesProductos[indice];
                contadorItems = contadorItems + 1;
            }
            indice++;
        }
        if (contadorItems > 3) {
            subtotalConDescuento = subtotal - (subtotal * 0.05);
        } else {
            subtotalConDescuento = subtotal;
        }
        if (subtotalConDescuento > 50000) {
            montoIVA = subtotalConDescuento * 0.19;
            total = subtotalConDescuento + montoIVA;
            propina = total * 0.10;
            total = total + propina;
        } else {
            montoIVA = subtotalConDescuento * 0.19;
            total = subtotalConDescuento + montoIVA;
            propina = 0;
        }
        String separador = "========================================";
        System.out.println(separador);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(separador);
        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println("----------------------------------------");
        int indiceProductos = 0;
        while (indiceProductos < Datos.nombresProductos.length) {
            if (Datos.cantidadesProductos[indiceProductos] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", Datos.nombresProductos[indiceProductos], Datos.cantidadesProductos[indiceProductos], (Datos.precios[indiceProductos] * Datos.cantidadesProductos[indiceProductos]));
            }
            indiceProductos++;
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", montoIVA);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(separador);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(separador);
        Datos.numeroFactura = Datos.numeroFactura + 1;
        Datos.estadoMesa = 0;
        Datos.totalActual = total;
    }

    public static void imprimirFacturaResumen() {
        double subtotal = 0;
        double montoIVA = 0;
        double total = 0;
        double propina = 0;
        int contadorItems = 0;
        double subtotalConDescuento = 0;
        int indice = 0;
        while (indice < Datos.nombresProductos.length) {
            if (Datos.cantidadesProductos[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidadesProductos[indice];
                contadorItems = contadorItems + 1;
            }
            indice++;
        }
        if (contadorItems > 3) {
            subtotalConDescuento = subtotal - (subtotal * 0.05);
        } else {
            subtotalConDescuento = subtotal;
        }
        if (subtotalConDescuento > 50000) {
            montoIVA = subtotalConDescuento * 0.19;
            total = subtotalConDescuento + montoIVA;
            propina = total * 0.10;
            total = total + propina;
        } else {
            montoIVA = subtotalConDescuento * 0.19;
            total = subtotalConDescuento + montoIVA;
            propina = 0;
        }
        String separador = "========================================";
        System.out.println(separador);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(separador);
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.numeroFactura);
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", montoIVA);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(separador);
    }
}