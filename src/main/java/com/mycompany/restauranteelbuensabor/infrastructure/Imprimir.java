package com.mycompany.restauranteelbuensabor.infrastructure;

import com.mycompany.restauranteelbuensabor.domain.model.Pedido;
import com.mycompany.restauranteelbuensabor.service.Factura;

/**
 * Delegado para compatibilidad hacia atrás.
 * @deprecated Usar ImpresoraConsola que implementa IImpresora
 */
@Deprecated
public class Imprimir {
    private static final IImpresora DELEGADO = new ImpresoraConsola();

    public static void mostrarCarta() {
        CartaRestaurante carta = new CartaRestaurante();
        DELEGADO.mostrarCarta(carta);
    }

    public static void mostrarPedido(Pedido pedido) {
        DELEGADO.mostrarPedido(pedido);
    }

    public static void imprimirFactura(Factura factura) {
        DELEGADO.imprimirFactura(factura);
    }
}