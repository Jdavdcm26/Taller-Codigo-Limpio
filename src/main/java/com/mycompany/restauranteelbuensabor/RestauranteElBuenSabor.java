package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {
    private static final CartaRestaurante carta = new CartaRestaurante();
    private static final Pedido pedido = new Pedido();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcionMenu = 0;
        boolean menuActivo = true;
        int contadorIntentos = 0;
        int mesaTemporal = 0;
        double montoPedido = 0;

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
                        if (Datos.estadoMesa == 0) {
                            System.out.print("Ingrese numero de mesa: ");
                            Datos.numeroMesaActual = scanner.nextInt();
                            if (Datos.numeroMesaActual > 0) {
                                Datos.estadoMesa = 1;
                                mesaTemporal = Datos.numeroMesaActual;
                                contadorIntentos = mesaTemporal + 1;
                                pedido.setNumeroMesa(Datos.numeroMesaActual);
                            } else {
                                Datos.numeroMesaActual = 1;
                                Datos.estadoMesa = 1;
                                mesaTemporal = 1;
                                contadorIntentos = 2;
                                pedido.setNumeroMesa(1);
                            }
                        }
                        Producto producto = carta.getProducto(numeroProducto);
                        pedido.agregarProducto(producto, cantidad);
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + producto.getNombre() + " x" + cantidad);
                        montoPedido = producto.getPrecio() * cantidad;
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
                if (pedido.hayProductos()) {
                    Imprimir.mostrarPedido(pedido);
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();
            } else if (opcionMenu == 4) {
                System.out.println();
                if (pedido.hayProductos()) {
                    Factura factura = new Factura(pedido);
                    Imprimir.imprimirFactura(factura);
                    montoPedido = factura.getTotal();
                    System.out.println();
                    System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                    System.out.println();
                    pedido.reiniciar();
                    mesaTemporal = 0;
                    montoPedido = 0;
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                    mesaTemporal = 0;
                    montoPedido = 0;
                }
            } else if (opcionMenu == 5) {
                System.out.println();
                pedido.reiniciar();
                contadorIntentos = 0;
                mesaTemporal = 0;
                montoPedido = 0;
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