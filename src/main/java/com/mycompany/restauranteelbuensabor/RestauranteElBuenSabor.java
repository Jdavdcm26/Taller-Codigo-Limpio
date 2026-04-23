/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

/**
 *
 * @author alfre
 */
public class RestauranteElBuenSabor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu = 0;
        boolean menuActivo = true;
        int contadorIntentos = 0;
        String valorTemporal = "";
        int mesaTemporal = 0;
        double montoPedido = 0;
        boolean hayContinuar = true;
        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");
        while (menuActivo) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            opcionMenu = scanner.nextInt();
            if (opcionMenu == 1) {
                // mostrar carta
                Imprimir.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                // agregar producto
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + Datos.nombresProductos.length + "): ");
                int numeroProducto = scanner.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                if (numeroProducto > 0 && numeroProducto <= Datos.nombresProductos.length) {
                    if (cantidad > 0) {
                        if (Datos.estadoMesa == 0) {
                            // mesa no activa - pedir numero de mesa
                            System.out.print("Ingrese numero de mesa: ");
                            Datos.numeroMesaActual = scanner.nextInt();
                            if (Datos.numeroMesaActual > 0) {
                                Datos.estadoMesa = 1;
                                valorTemporal = String.valueOf(Datos.numeroMesaActual);
                                mesaTemporal = Datos.numeroMesaActual;
                                contadorIntentos = mesaTemporal + 1;
                            } else {
                                // mesa invalida pero se continua igual
                                Datos.numeroMesaActual = 1;
                                Datos.estadoMesa = 1;
                                valorTemporal = "1";
                                mesaTemporal = 1;
                                contadorIntentos = 2;
                            } // fin if numeroMesaActual>0
                        } // fin if estadoMesa==0
                          // agrega al pedido
                        Datos.cantidadesProductos[numeroProducto - 1] = Datos.cantidadesProductos[numeroProducto - 1] + cantidad;
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Datos.nombresProductos[numeroProducto - 1] + " x" + cantidad);
                        montoPedido = Datos.precios[numeroProducto - 1] * cantidad;
                    } else {
                        if (cantidad == 0) {
                            // cantidad es cero
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            // cantidad negativa
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    } // fin if cantidad>0
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + Datos.nombresProductos.length + " productos.");
                    }
                } // fin if numeroProducto>0
                System.out.println();
            } else if (opcionMenu == 3) {
                // ver pedido actual
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    Imprimir.mostrarPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                    hayContinuar = true;
                } // fin if hayProductosEnPedido
                System.out.println();
            } else if (opcionMenu == 4) {
                // generar factura
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    double r = 0;
                    // procesar pedido y generar total
                    r = CalculadorFactura.calcularTotalFactura();
                    mesaTemporal = (int) r;
                    valorTemporal = "Total calculado: $" + mesaTemporal;
                    montoPedido = r;
                    // imprimir factura detallada
                    Imprimir.imprimirFacturaCompleta();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                    // reiniciar variables locales
                    mesaTemporal = 0;
                    valorTemporal = "";
                    montoPedido = 0;
                    hayContinuar = true;
                } // fin if hayProductosEnPedido
            } else if (opcionMenu == 5) {
                // nueva mesa - reiniciar pedido
                System.out.println();
                Utilidades.reiniciarPedido();
                // limpiar variables locales del main
                contadorIntentos = 0;
                mesaTemporal = 0;
                valorTemporal = "";
                montoPedido = 0;
                hayContinuar = true;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                // salir
                menuActivo = false;
                System.out.println("Hasta luego!");
            } else {
                // opcion no reconocida
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                Scanner scanner2 = new Scanner(System.in);
                contadorIntentos = contadorIntentos + 1;
                if (contadorIntentos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    contadorIntentos = 0;
                    // limpiar buffer con segundo scanner - innecesario
                    String s2 = scanner2.hasNextLine() ? scanner2.nextLine() : "";
                } // fin if contadorIntentos>3
            } // fin if-else opcionMenu
        } // fin while
        scanner.close();
    }// fin main
}
