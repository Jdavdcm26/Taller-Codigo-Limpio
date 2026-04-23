package com.mycompany.restauranteelbuensabor.infrastructure;

import com.mycompany.restauranteelbuensabor.config.Constantes;
import com.mycompany.restauranteelbuensabor.domain.model.ItemPedido;
import com.mycompany.restauranteelbuensabor.domain.model.Pedido;
import com.mycompany.restauranteelbuensabor.service.Factura;

public class Imprimir {

    public static void mostrarCarta() {
        CartaRestaurante carta = new CartaRestaurante();
        carta.mostrarCarta();
    }

    public static void mostrarPedido(Pedido pedido) {
        double subtotal = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                item.getProducto().getNombre(),
                item.getCantidad(),
                item.getSubtotal());
            subtotal += item.getSubtotal();
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFactura(Factura factura) {
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    " + Constantes.DIRECCION);
        System.out.println("    NIT: " + Constantes.NIT);
        System.out.println(Constantes.SEPARADOR);
        System.out.printf("FACTURA No. %03d%n", factura.getNumeroFactura());
        System.out.println(Constantes.SEPARADOR_ITEM);

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                item.getProducto().getNombre(),
                item.getCantidad(),
                item.getSubtotal());
        }

        System.out.println(Constantes.SEPARADOR_ITEM);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.getSubtotal());
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.getMontoIVA());
        if (factura.getMontoPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.getMontoPropina());
        }
        System.out.println(Constantes.SEPARADOR_ITEM);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.getTotal());
        System.out.println(Constantes.SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println(Constantes.NOMBRE_RESTAURANTE + " - " + Constantes.DIRECCION.split(",")[0]);
        System.out.println(Constantes.SEPARADOR);
    }
}