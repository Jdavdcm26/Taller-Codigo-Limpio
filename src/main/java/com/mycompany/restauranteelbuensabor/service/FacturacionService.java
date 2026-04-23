package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.config.Constantes;

/**
 * Implementación del servicio de facturación.
 */
public class FacturacionService implements IFacturacionService {

    @Override
    public double calcularIVA(double base) {
        return base * Constantes.TASA_IVA;
    }

    @Override
    public double calcularPropina(double totalConIVA, double base) {
        if (base > Constantes.UMBRAL_PROPINA) {
            return totalConIVA * Constantes.TASA_PROPINA;
        }
        return 0;
    }

    @Override
    public boolean aplicaDescuento(int cantidadItems) {
        return cantidadItems > Constantes.MIN_ITEMS_DESCUENTO;
    }

    @Override
    public double aplicarDescuento(double subtotal, boolean aplica) {
        if (aplica) {
            return subtotal - (subtotal * Constantes.TASA_DESCUENTO);
        }
        return subtotal;
    }
}