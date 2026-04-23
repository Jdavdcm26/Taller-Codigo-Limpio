package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {
    private static final CartaRestaurante carta = new CartaRestaurante();
    private static final Mesa mesa = new Mesa();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcionMenu = 0;
        boolean menuActivo = true;

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
                carta.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + carta.getCantidadProductos() + "): ");
                int numeroProducto = scanner.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                if (numeroProducto > 0 && numeroProducto <= carta.getCantidadProductos()) {
                    if (cantidad > 0) {
                        if (!mesa.isOcupada()) {
                            System.out.print("Ingrese numero de mesa: ");
                            int numeroMesa = scanner.nextInt();
                            mesa.ocupar(numeroMesa);
                        }
                        Producto producto = carta.getProducto(numeroProducto);
                        mesa.getPedido().agregarProducto(producto, cantidad);
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + producto.getNombre() + " x" + cantidad);
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
                        System.out.println("Producto no existe. La carta tiene " + carta.getCantidadProductos() + " productos.");
                    }
                }
                System.out.println();
            } else if (opcionMenu == 3) {
                System.out.println();
                if (mesa.getPedido().hayProductos()) {
                    Imprimir.mostrarPedido(mesa.getPedido());
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();
            } else if (opcionMenu == 4) {
                System.out.println();
                if (mesa.getPedido().hayProductos()) {
                    Factura factura = new Factura(mesa.getPedido());
                    Imprimir.imprimirFactura(factura);
                    System.out.println();
                    mesa.liberar();
                    System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }
            } else if (opcionMenu == 5) {
                System.out.println();
                mesa.liberar();
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                menuActivo = false;
                System.out.println("Hasta luego!");
            } else {
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
            }
        }
        scanner.close();
    }
}