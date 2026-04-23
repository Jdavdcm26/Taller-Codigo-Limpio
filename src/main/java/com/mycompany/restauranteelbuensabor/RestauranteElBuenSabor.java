package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

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
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    " + Constantes.DIRECCION);
        System.out.println("    NIT: " + Constantes.NIT);
        System.out.println(Constantes.SEPARADOR);
        while (menuActivo) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println(Constantes.SEPARADOR);
            System.out.print("Seleccione una opcion: ");
            opcionMenu = scanner.nextInt();
            if (opcionMenu == 1) {
                Imprimir.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + Datos.nombresProductos.length + "): ");
                int numeroProducto = scanner.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                if (numeroProducto > 0 && numeroProducto <= Datos.nombresProductos.length) {
                    if (cantidad > 0) {
                        if (Datos.estadoMesa == 0) {
                            System.out.print("Ingrese numero de mesa: ");
                            Datos.numeroMesaActual = scanner.nextInt();
                            if (Datos.numeroMesaActual > 0) {
                                Datos.estadoMesa = 1;
                                valorTemporal = String.valueOf(Datos.numeroMesaActual);
                                mesaTemporal = Datos.numeroMesaActual;
                                contadorIntentos = mesaTemporal + 1;
                            } else {
                                Datos.numeroMesaActual = 1;
                                Datos.estadoMesa = 1;
                                valorTemporal = "1";
                                mesaTemporal = 1;
                                contadorIntentos = 2;
                            }
                        }
                        Datos.cantidadesProductos[numeroProducto - 1] = Datos.cantidadesProductos[numeroProducto - 1] + cantidad;
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Datos.nombresProductos[numeroProducto - 1] + " x" + cantidad);
                        montoPedido = Datos.precios[numeroProducto - 1] * cantidad;
                    } else {
                        if (cantidad == 0) {
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + Datos.nombresProductos.length + " productos.");
                    }
                }
                System.out.println();
            } else if (opcionMenu == 3) {
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    Imprimir.mostrarPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                    hayContinuar = true;
                }
                System.out.println();
            } else if (opcionMenu == 4) {
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    double r = 0;
                    r = CalculadorFactura.calcularTotalFactura();
                    mesaTemporal = (int) r;
                    valorTemporal = "Total calculado: $" + mesaTemporal;
                    montoPedido = r;
                    Imprimir.imprimirFacturaCompleta();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                    mesaTemporal = 0;
                    valorTemporal = "";
                    montoPedido = 0;
                    hayContinuar = true;
                }
            } else if (opcionMenu == 5) {
                System.out.println();
                Utilidades.reiniciarPedido();
                contadorIntentos = 0;
                mesaTemporal = 0;
                valorTemporal = "";
                montoPedido = 0;
                hayContinuar = true;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                menuActivo = false;
                System.out.println("Hasta luego!");
            } else {
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                contadorIntentos = contadorIntentos + 1;
                if (contadorIntentos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    contadorIntentos = 0;
                }
            }
        }
        scanner.close();
    }
}