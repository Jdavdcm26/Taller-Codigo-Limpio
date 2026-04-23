package com.mycompany.restauranteelbuensabor.presentation;

import com.mycompany.restauranteelbuensabor.infrastructure.Imprimir;
import java.util.Scanner;

public class RestauranteElBuenSabor {
    private static final SistemaRestaurante sistema = new SistemaRestaurante();
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuHandler menuHandler = new MenuHandler(sistema, scanner);

    public static void main(String[] args) {
        menuHandler.mostrarEncabezado();

        OpcionMenu opcion = null;
        while (opcion != OpcionMenu.SALIR) {
            menuHandler.mostrarMenu();
            opcion = menuHandler.leerOpcion();

            if (opcion == null) {
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
            } else {
                menuHandler.procesar(opcion);
                if (opcion == OpcionMenu.SALIR) {
                    System.out.println("Hasta luego!");
                }
            }
        }
        scanner.close();
    }
}