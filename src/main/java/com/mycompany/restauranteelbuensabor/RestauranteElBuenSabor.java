package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {
    private static final SistemaRestaurante sistema = new SistemaRestaurante();
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
                sistema.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + sistema.getCarta().getCantidadProductos() + "): ");
                int numeroProducto = scanner.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                if (numeroProducto > 0 && numeroProducto <= sistema.getCarta().getCantidadProductos()) {
                    if (cantidad > 0) {
                        if (!sistema.mesaEstaOcupada()) {
                            System.out.print("Ingrese numero de mesa: ");
                            int numeroMesa = scanner.nextInt();
                            sistema.ocuparMesa(numeroMesa);
                        }
                        sistema.agregarProducto(numeroProducto, cantidad);
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + sistema.getCarta().getProducto(numeroProducto).getNombre() + " x" + cantidad);
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
                        System.out.println("Producto no existe. La carta tiene " + sistema.getCarta().getCantidadProductos() + " productos.");
                    }
                }
                System.out.println();
            } else if (opcionMenu == 3) {
                System.out.println();
                sistema.mostrarPedido();
                System.out.println();
            } else if (opcionMenu == 4) {
                System.out.println();
                sistema.generarFactura();
                System.out.println();
                sistema.nuevaMesa();
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 5) {
                System.out.println();
                sistema.nuevaMesa();
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