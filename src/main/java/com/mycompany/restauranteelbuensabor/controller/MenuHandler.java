package com.mycompany.restauranteelbuensabor.controller;

import com.mycompany.restauranteelbuensabor.config.Constantes;
import java.util.Scanner;

public class MenuHandler {
    private final SistemaRestaurante sistema;
    private final Scanner scanner;

    public MenuHandler(SistemaRestaurante sistema, Scanner scanner) {
        this.sistema = sistema;
        this.scanner = scanner;
    }

    public void procesar(OpcionMenu opcion) {
        switch (opcion) {
            case VER_CARTA -> procesarVerCarta();
            case AGREGAR_PRODUCTO -> procesarAgregarProducto();
            case VER_PEDIDO -> procesarVerPedido();
            case GENERAR_FACTURA -> procesarGenerarFactura();
            case NUEVA_MESA -> procesarNuevaMesa();
            case SALIR -> { }
        }
    }

    private void procesarVerCarta() {
        sistema.mostrarCarta();
        System.out.println();
    }

    private void procesarAgregarProducto() {
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
    }

    private void procesarVerPedido() {
        System.out.println();
        sistema.mostrarPedido();
        System.out.println();
    }

    private void procesarGenerarFactura() {
        System.out.println();
        sistema.generarFactura();
        System.out.println();
        sistema.nuevaMesa();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
        System.out.println();
    }

    private void procesarNuevaMesa() {
        System.out.println();
        sistema.nuevaMesa();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
        System.out.println();
    }

    public void mostrarMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
    }

    public OpcionMenu leerOpcion() {
        System.out.print("Seleccione una opcion: ");
        int codigo = scanner.nextInt();
        return OpcionMenu.fromCodigo(codigo);
    }

    public void mostrarEncabezado() {
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    " + Constantes.DIRECCION);
        System.out.println("    NIT: " + Constantes.NIT);
        System.out.println(Constantes.SEPARADOR);
    }
}