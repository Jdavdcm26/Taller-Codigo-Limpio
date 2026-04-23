package com.mycompany.restauranteelbuensabor.view;

import com.mycompany.restauranteelbuensabor.config.Constantes;
import com.mycompany.restauranteelbuensabor.model.ItemPedido;
import com.mycompany.restauranteelbuensabor.model.Pedido;
import com.mycompany.restauranteelbuensabor.controller.Factura;

/**
 * Implementación de IImpresora para consola.
 */
public class ImpresoraConsola implements IImpresora {

    @Override
    public void mostrarCarta(CartaRestaurante carta) {
        carta.mostrarCarta();
    }

    @Override
    public void mostrarPedido(Pedido pedido) {
        double subtotal = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            String linea = formatearLineaItem(item);
            System.out.println(linea);
            subtotal += item.getSubtotal();
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    @Override
    public void imprimirFactura(Factura factura) {
        System.out.println(Constantes.SEPARADOR);
        System.out.println("    " + Constantes.NOMBRE_RESTAURANTE);
        System.out.println("    " + Constantes.DIRECCION);
        System.out.println("    NIT: " + Constantes.NIT);
        System.out.println(Constantes.SEPARADOR);
        System.out.printf("FACTURA No. %03d%n", factura.getNumeroFactura());
        System.out.println(Constantes.SEPARADOR_ITEM);

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.println(formatearLineaItem(item));
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

    private String formatearLineaItem(ItemPedido item) {
        return String.format("%-20s x%-6d $%,.0f",
            item.getProducto().getNombre(),
            item.getCantidad(),
            item.getSubtotal());
    }
}