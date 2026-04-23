package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.config.Constantes;

/**
 * Interfaz para servicio de facturación.
 */
public interface IFacturacionService {
    double calcularIVA(double base);
    double calcularPropina(double totalConIVA, double base);
    boolean aplicaDescuento(int cantidadItems);
    double aplicarDescuento(double subtotal, boolean aplica);
}