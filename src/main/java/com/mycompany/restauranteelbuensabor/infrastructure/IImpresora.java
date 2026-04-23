package com.mycompany.restauranteelbuensabor.infrastructure;

import com.mycompany.restauranteelbuensabor.domain.model.Pedido;
import com.mycompany.restauranteelbuensabor.service.Factura;

/**
 * Interfaz para imprimir formatos de salida.
 */
public interface IImpresora {
    void mostrarCarta(CartaRestaurante carta);
    void mostrarPedido(Pedido pedido);
    void imprimirFactura(Factura factura);
}